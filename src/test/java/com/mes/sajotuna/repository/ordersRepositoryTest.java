package com.mes.sajotuna.repository;


import com.mes.sajotuna.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ordersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void test1() {
        Orders orders = new Orders();

        orders.setNo("123");
        orders.setQtt(12);
        orders.setDate(LocalDateTime.now());
        orders.setCode("123");
        orders.setItem("12315");
        orders.setCompany("2131");
        orders.setShipDate(LocalDate.from(LocalDateTime.now()));
        orders.setStatus("132");

        ordersRepository.save(orders);

        System.out.println(orders);
    }
}
