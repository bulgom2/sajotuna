package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Orders;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAll();

}
