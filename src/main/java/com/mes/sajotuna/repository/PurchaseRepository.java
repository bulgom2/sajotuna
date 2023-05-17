package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
