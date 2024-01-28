package com.order.management.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditBaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "dateBasedIdGenerator",
            strategy = "com.order.management.config.persistenceConfig.DateBasedIdGenerator")
    @GeneratedValue(generator = "dateBasedIdGenerator")
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private String id;

    @Getter
    @Setter
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private ZonedDateTime modifiedAt;

    @Getter
    @Setter
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

}
