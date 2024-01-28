package com.order.management.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory",
        indexes = {@Index(name = "idx_serial_number", columnList = "serial_number")},
        uniqueConstraints = {@UniqueConstraint(name = "uk_serial_number", columnNames = {"serial_number"})})
@Where(clause = "is_deleted = false")
public class Inventory extends AuditBaseEntity {

    @Column(name = "serial_number")
    @Getter
    @Setter
    private String serialNumber;

    @Column(name = "name")
    @Getter
    @Setter
    private String productName;

    @Column(name = "price")
    @Getter
    @Setter
    private BigDecimal price;

    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;


}
