package com.mes.sajotuna;


import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SajotunaApplicationTests {

    @Test
    void contextLoads() {
    }

//    @Test
//    void test1() {
//        Orders orders = new Orders();
//
//        orders.setNo("123");
//        orders.setQtt(12);
//
//        ordersRepository.save(orders);
//    }

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void test1() {
        Orders orders = new Orders();

        orders.setNo("123");
        orders.setQtt(234L);

        ordersRepository.save(orders);
    }

}