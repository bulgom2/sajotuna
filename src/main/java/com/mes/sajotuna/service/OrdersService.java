package com.mes.sajotuna.service;

import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public List<Orders> getOrders() {
        List<Orders> list = ordersRepository.findAll();

        return list;
    }



}
