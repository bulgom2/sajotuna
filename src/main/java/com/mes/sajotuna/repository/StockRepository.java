package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
