package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@SpringBootTest
class PurchaseServiceTests {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void purchaseTest(){
        Orders orders = ordersRepository.findById(1L)
                .orElseThrow(EntityNotFoundException::new);

        OrdersDTO ordersDto = OrdersDTO.of(orders);

        LocalDateTime purchaseTime = purchaseService.purchaseMain(ordersDto);

        System.out.println("발주 완료 시간 : " + purchaseTime);
    }

}
