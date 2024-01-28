package com.order.management.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "cart",
        indexes = {@Index(name = "ix_user_id", columnList = "user_id")},
        uniqueConstraints = {@UniqueConstraint(name = "uk_user_product", columnNames = {"user_id", "product_id"})}
)
@Check(constraints = "quantity > 0")
@Where(clause = "is_deleted = false")
public class Cart extends AuditBaseEntity {

    @Column(name = "user_id")
    @Getter
    @Setter
    private String userId;

    @Column(name = "product_id")
    @Getter
    @Setter
    private String productId;

    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;

}
