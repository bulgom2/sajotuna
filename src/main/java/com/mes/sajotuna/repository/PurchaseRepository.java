package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAll();

    List<Purchase> findByOrdersNo(String ordersNo);
}
