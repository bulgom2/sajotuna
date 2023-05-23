package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.entity.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class PurchaseRepositoryTest {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    void Test10 () {

        Purchase purchase = new Purchase();

        Orders orders = new Orders();

        purchase.setCode("dd");
        purchase.setNo("sdfdf");
        purchase.setItem("dfdfd");
        purchase.setQtt(500L);
        purchase.setDate(LocalDateTime.now());
        purchase.setCompany("sfdfsd");
        purchase.setOrders(orders);
        purchase.setShipDate(LocalDateTime.now());
        purchase.setStatus(30L);

//        purchaseRepository.save(purchase);

        System.out.println(purchase);

    }
}
