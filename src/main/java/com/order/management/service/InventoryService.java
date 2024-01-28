package com.order.management.service;

import com.order.management.entity.Inventory;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import com.order.management.repository.InventoryRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;


    public Inventory getProductByInventoryId(String inventoryId) {
        Optional<Inventory> inventoryEntityOptional = inventoryRepository.findById(inventoryId);
        return inventoryEntityOptional.orElse(null);
    }

    public Inventory getProductBySerialNumber(String serialNumber) {
        Optional<Inventory> inventoryEntityOptional = inventoryRepository.findBySerialNumber(serialNumber);
        return inventoryEntityOptional.orElse(null);
    }

    @SneakyThrows
    public Inventory addProduct(Inventory inventory) {
        try {
            inventoryRepository.save(inventory);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException constraintViolationException) {
                if ("inventory_serial_number_key".equals(constraintViolationException.getConstraintName())) {
                    throw new OrderManagementException(OrderManagementError.PRODUCT_ALREADY_EXISTS, new String[]{inventory.getSerialNumber()});
                }
            }
        }
        return inventory;
    }

    @SneakyThrows(OrderManagementException.class)
    public Inventory updateProduct(String inventoryId, Inventory inventory) {
        Optional<Inventory> inventoryEntityOptional = inventoryRepository.findById(inventoryId);
        if (inventoryEntityOptional.isPresent()) {
            return inventoryRepository.save(inventory);
        }
        throw new OrderManagementException(OrderManagementError.ENTITY_NOT_FOUND, new String[]{"Inventory", inventoryId});
    }


}
