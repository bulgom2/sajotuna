package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Orders;
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
