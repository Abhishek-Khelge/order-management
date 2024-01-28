package com.order.management.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item",
        indexes = {@Index(name = "ix_order_id", columnList = "order_id")})
@Where(clause = "is_deleted = false")
public class OrderItem extends AuditBaseEntity {

    @Column(name = "order_id")
    @Getter
    @Setter
    private String orderId;

    @Column(name = "product_id")
    @Getter
    @Setter
    private String productId;

    @Column(name = "order_price")
    @Getter
    @Setter
    private BigDecimal orderPrice;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private OrderStatus orderStatus;

}
