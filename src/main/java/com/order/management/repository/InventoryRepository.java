package com.order.management.repository;

import com.order.management.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends BaseJpaRepository<Inventory, String> {
    Optional<Inventory> findBySerialNumber(String serialNumber);

    Page<Inventory> findAllByProductNameIgnoreCaseContaining(String decodedSearchQuery, Pageable pageable);

}
