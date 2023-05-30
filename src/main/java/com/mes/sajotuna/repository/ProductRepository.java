package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String item);
}
