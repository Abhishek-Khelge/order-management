package com.order.management.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order",
        indexes = {@Index(name = "idx_user_id", columnList = "user_id")})
@Where(clause = "is_deleted = false")
public class Order extends AuditBaseEntity {

    @Column(name = "user_id")
    @Getter
    @Setter
    private String userId;

    @Column(name = "total_price")
    @Getter
    @Setter
    private BigDecimal totalAmount;

}
