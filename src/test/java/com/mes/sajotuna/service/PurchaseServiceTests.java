package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

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

        System.out.println("수주 entity : " + orders);

        OrdersDTO ordersDto = OrdersDTO.of(orders);

        System.out.println("수주 dto : " + ordersDto);

        Orders orders1 = ordersDto.createOrders();

        ordersRepository.save(orders);

        if(ordersDto.getDate().getDayOfWeek().getValue() <= 5){
            PurchaseDTO purchaseDTO = purchaseService.purchaseMain(ordersDto);
            System.out.println("발주 완료 시간 : " + purchaseDTO.getShipDate());
        } else{
            System.out.println("발주가 진행되지 않았습니다.");

        }

    }
}
