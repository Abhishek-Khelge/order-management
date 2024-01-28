package com.order.management.repository;

import com.order.management.entity.AuditBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * BaseJpaRepository provides utils methods that can utilized by other Repository extending
 * it
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseJpaRepository<T extends AuditBaseEntity, ID> extends JpaRepository<T, ID> {

}
