package com.mes.sajotuna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

//    @Test
//    void ordersTest() {
//        List<Orders> list = OrderS.findAll();
//
//        System.out.println("list : "+list);
//
//        for(Orders orders : list){
//            System.out.println("주소 : " + orders);
//            System.out.println("값 : " + orders.toString());
//        }
//    }
}
