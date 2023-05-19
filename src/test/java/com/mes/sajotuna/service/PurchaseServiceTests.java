package com.mes.sajotuna.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class PurchaseServiceTests {

    @Autowired
    private PurchaseService purchaseService;


    @Test
    void purchaseTest(){
        LocalDateTime purchaseTime = purchaseService.purchaseMain();

        System.out.println("발주 완료 시간 : " + purchaseTime);
    }

}
