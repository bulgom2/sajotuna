package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
