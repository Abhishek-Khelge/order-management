package com.order.management.service;

import com.order.management.entity.Cart;
import com.order.management.entity.Inventory;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import com.order.management.mapper.CartMapper;
import com.order.management.model.cart.CartEntryRequestModel;
import com.order.management.model.cart.CartProductsResponseModel;
import com.order.management.model.cart.UpdateCartRequestModel;
import com.order.management.repository.CartRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartProductService {

    private @Autowired CartService cartService;
    private @Autowired CartRepository cartRepository;
    private @Autowired InventoryService inventoryService;

    public Page<CartProductsResponseModel> getCartProducts(String userId, Pageable pageable) {
        Page<Cart> cartProductsList = cartRepository.findAllByUserId(userId, pageable);
        List<CartProductsResponseModel> cartProducts = new ArrayList<>();
        cartProductsList.forEach(currCartProduct -> {
                    Inventory inventory = inventoryService.getProductByInventoryId(
                            currCartProduct.getProductId()
                    );
                    cartProducts.add(new CartProductsResponseModel(inventory.getSerialNumber(),
                            inventory.getProductName(), currCartProduct.getQuantity()));
                }
        );
        return new PageImpl<>(
                cartProducts,
                cartProductsList.getPageable(),
                cartProductsList.getTotalElements()
        );
    }

    @SneakyThrows
    public CartProductsResponseModel addProductToCart(String userId, CartEntryRequestModel cartInputModel) {
        Inventory inventory = inventoryService.getProductBySerialNumber(cartInputModel.getSerialNumber());
        Cart cart = cartService.getCartByUserIdAndProductId(userId, inventory.getId());
        if (cart != null) {
            throw new OrderManagementException(OrderManagementError.PRODUCT_ALREADY_EXISTS_IN_CART, new String[]{userId});
        }
        cart = CartMapper.getCartByEntryRequest(userId, cartInputModel, inventory);
        cartService.addCart(cart);
        return CartMapper.getCartProductsResponseModel(cart, inventory);
    }

    @SneakyThrows
    public CartProductsResponseModel updateCart(String userId, String serialNumber, UpdateCartRequestModel updateCartRequestModel) {
        Inventory inventory = inventoryService.getProductBySerialNumber(serialNumber);
        Cart cart = cartService.getCartByUserIdAndProductId(userId, inventory.getId());
        if (cart == null) {
            throw new OrderManagementException(OrderManagementError.PRODUCT_DOES_NOT_EXISTS_IN_CART, new String[]{serialNumber});
        }
        updateCartDetails(cart, updateCartRequestModel);
        return CartMapper.getCartProductsResponseModel(cart, inventory);
    }

    @SneakyThrows
    private void updateCartDetails(Cart cart, UpdateCartRequestModel updateCartRequestModel) {
        if (updateCartRequestModel.getIncreaseQuantityBy() != 0) {
            cart.setQuantity(cart.getQuantity() + updateCartRequestModel.getIncreaseQuantityBy());
        }
        if (updateCartRequestModel.getDecreaseQuantityBy() != 0) {
            if (cart.getQuantity() - updateCartRequestModel.getDecreaseQuantityBy() < 0) {
                throw new OrderManagementException(OrderManagementError.PRODUCT_QUANTITY_DECREASE_LESS_THAN_ZERO, null);
            }
            cart.setQuantity(cart.getQuantity() - updateCartRequestModel.getDecreaseQuantityBy());
        }
    }

    @SneakyThrows
    public CartProductsResponseModel removeProduct(String userId, String serialNumber) {
        Inventory inventory = inventoryService.getProductBySerialNumber(serialNumber);
        Cart cart = cartService.getCartByUserIdAndProductId(userId, inventory.getId());
        if (cart == null) {
            throw new OrderManagementException(OrderManagementError.PRODUCT_DOES_NOT_EXISTS_IN_CART, new String[]{serialNumber});
        }
        cartService.deleteCart(cart.getId());
        cart.setQuantity(0);
        return CartMapper.getCartProductsResponseModel(cart, inventory);
    }
}
