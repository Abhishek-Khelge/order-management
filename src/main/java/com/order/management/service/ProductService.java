package com.order.management.service;


import com.order.management.entity.Inventory;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import com.order.management.mapper.ProductMapper;
import com.order.management.model.inventory.*;
import com.order.management.repository.InventoryRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryRepository inventoryRepository;

    public AddProductResponseModel addProduct(AddProductRequestModel productInputModel) {
        Inventory inventory = ProductMapper.toEntity(productInputModel);
        inventory = inventoryService.addProduct(inventory);
        return ProductMapper.fromEntityAddModel(inventory);
    }

    @SneakyThrows
    public UpdateProductResponseModel updateProduct(String serialNumber, UpdateProductRequestModel updateProductRequestModel) {
        Inventory inventory = inventoryService.getProductBySerialNumber(serialNumber);
        if (inventory == null) {
            throwNotExistsException(serialNumber);
        }
        updateInventoryFields(serialNumber, updateProductRequestModel, inventory);
        inventory = inventoryService.updateProduct(inventory.getId(), inventory);
        return ProductMapper.fromEntityUpdateModel(inventory);
    }

    public UpdateProductResponseModel removeProduct(String serialNumber) {
        Inventory inventory = inventoryService.getProductBySerialNumber(serialNumber);
        if (inventory == null) {
            throwNotExistsException(serialNumber);
        }
        inventory.setDeleted(true);
        inventory = inventoryService.updateProduct(inventory.getId(), inventory);
        return ProductMapper.fromEntityUpdateModel(inventory);
    }

    public Page<ProductResponseModel> searchBySearchQuery(String decodedSearchQuery, Pageable pageable) {
        Page<Inventory> toReturn = inventoryRepository.findAllByProductNameIgnoreCaseContaining
                (decodedSearchQuery, pageable);
        if (toReturn == null || toReturn.isEmpty()) {
            return Page.empty();
        }
        return new PageImpl<>(
                ProductMapper.fromProductResponse(toReturn.stream().toList()),
                toReturn.getPageable(),
                toReturn.getTotalElements()
        );
    }

    public ProductResponseModel getProductDetails(String serialNumber) {
        Inventory inventory = inventoryService.getProductBySerialNumber(serialNumber);
        if (inventory == null) {
            throwNotExistsException(serialNumber);
        }
        return ProductMapper.fromProductResponse(inventory);
    }

    @SneakyThrows
    private static void throwNotExistsException(String serialNumber) {
        throw new OrderManagementException(OrderManagementError.PRODUCT_DOES_NOT_EXISTS,
                new String[]{serialNumber});
    }

    private void updateInventoryFields(String serialNumber, UpdateProductRequestModel updateProductRequestModel, Inventory inventory) throws OrderManagementException {
        if (updateProductRequestModel.getProductName() != null) {
            inventory.setProductName(updateProductRequestModel.getProductName());
        }
        if (updateProductRequestModel.getPrice() != null) {
            inventory.setPrice(updateProductRequestModel.getPrice());
        }
        if (updateProductRequestModel.getDescription() != null) {
            inventory.setDescription(updateProductRequestModel.getDescription());
        }
        if (updateProductRequestModel.getIncreaseQuantityBy() != 0) {
            inventory.setQuantity(inventory.getQuantity() + updateProductRequestModel.getIncreaseQuantityBy());
        }
        if (updateProductRequestModel.getDecreaseQuantityBy() != 0) {
            if (inventory.getQuantity() - updateProductRequestModel.getDecreaseQuantityBy() < 0) {
                throw new OrderManagementException(OrderManagementError.PRODUCT_QUANTITY_DECREASE_LESS_THAN_ZERO,
                        new String[]{"serialNumber", serialNumber});
            }
            inventory.setQuantity(inventory.getQuantity() - updateProductRequestModel.getDecreaseQuantityBy());
        }
    }

}


