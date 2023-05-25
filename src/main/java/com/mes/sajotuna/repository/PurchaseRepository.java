package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAll();
}
