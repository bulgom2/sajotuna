package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
