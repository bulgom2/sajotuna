package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.OrdersEntity;
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
}
