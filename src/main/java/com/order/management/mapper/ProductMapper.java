package com.order.management.mapper;

import com.order.management.entity.Inventory;
import com.order.management.model.inventory.AddProductRequestModel;
import com.order.management.model.inventory.AddProductResponseModel;
import com.order.management.model.inventory.ProductResponseModel;
import com.order.management.model.inventory.UpdateProductResponseModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static List<ProductResponseModel> fromProductResponse(List<Inventory> inventoryList) {
        List<ProductResponseModel> result = new ArrayList<>();
        for (Inventory inventory : inventoryList) {
            result.add(fromProductResponse(inventory));
        }
        return result;
    }

    public static ProductResponseModel fromProductResponse(Inventory inventory) {
        return new ProductResponseModel(inventory.getProductName(), inventory.getPrice(), inventory.getDescription());
    }

    public static Inventory toEntity(AddProductRequestModel productInputModel) {
        Inventory inventory = new Inventory();
        inventory.setSerialNumber(productInputModel.getSerialNumber());
        inventory.setProductName(productInputModel.getProductName());
        inventory.setPrice(productInputModel.getPrice());
        inventory.setQuantity(productInputModel.getQuantity());
        inventory.setDescription(productInputModel.getDescription());
        return inventory;
    }

    public static AddProductResponseModel fromEntityAddModel(Inventory inventory) {
        return new AddProductResponseModel(inventory.getSerialNumber(), inventory.getProductName(),
                inventory.getQuantity());
    }

    public static UpdateProductResponseModel fromEntityUpdateModel(Inventory inventory) {
        return new UpdateProductResponseModel(inventory.getSerialNumber(), inventory.getProductName(),
                inventory.getPrice(), inventory.getQuantity(), inventory.getDescription());
    }
}
