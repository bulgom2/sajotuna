package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void test1() {


        String no = "SJ230519-01";
        String status = "Confirmed";

        ordersRepository.updateStatusByNo(no,status);



    }

    @Test
    public void test2(){
//        Orders orders = ordersRepository.findById(1L).orElseThrow();
//
//        orders.orderStateChangeTrue();
//        ordersRepository.save(orders);

        String no = "SJ230519-02";
        Orders orders = ordersRepository.findByNo(no);

        System.out.println("여기 : " + orders);

        orders.setStatus("complete");

        ordersRepository.save(orders);

        System.out.println("여기 : " + orders);



    }


}