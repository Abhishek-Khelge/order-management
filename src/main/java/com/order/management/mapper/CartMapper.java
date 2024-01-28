package com.order.management.mapper;

import com.order.management.entity.Cart;
import com.order.management.entity.Inventory;
import com.order.management.model.cart.CartEntryRequestModel;
import com.order.management.model.cart.CartProductsResponseModel;

public class CartMapper {

    public static Cart getCartByEntryRequest(String userId, CartEntryRequestModel cartInputModel, Inventory inventory) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(inventory.getId());
        cart.setQuantity(cartInputModel.getQuantity());
        return cart;
    }

    public static CartProductsResponseModel getCartProductsResponseModel(Cart cart, Inventory inventory) {
        return new CartProductsResponseModel(inventory.getSerialNumber(), inventory.getProductName(), cart.getQuantity());
    }
}
