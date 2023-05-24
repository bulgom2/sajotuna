package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class ordersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test1() {
        Orders orders = new Orders();

        orders.setNo("123");
        orders.setQtt(12L);
        orders.setDate(LocalDateTime.now());
//        orders.setCode("123");
        orders.setItem("12315");
        orders.setCompany("2131");
        orders.setShipDate(String.valueOf(LocalDate.from(LocalDateTime.now())));
        orders.setStatus("132");

        ordersRepository.save(orders);

        System.out.println(orders);
    }

    @Test
    @Transactional
    void test2() {
//        Orders orders = ordersRepository.findById(1L).orElseThrow();


        String no = "SJ230519-01";
        String status = "Confirmed";

//        Orders orders1 = ordersRepository.findById(4L).orElseThrow();
//
//        System.out.println("여기11 : " + orders1);
//
//        orders1.setStatus("Confirmed");
//
//        System.out.println("여기11 : " + orders1);
//
//        ordersRepository.save(orders1);

        ordersRepository.updateStatusByNo(no,status);

//        Orders orders = ordersRepository.findById(1L);

//        List<Orders> ordersList = ordersRepository.findAll();
//
//        for (Orders orders : ordersList){
//            System.out.println(orders);
////            Orders orders1 = ordersRepository.findById(orders.getId()).orElseThrow();
////            System.out.println(orders1);
//            ordersRepository.save(orders);
////            orders.setStatus(status);
////            entityManager.flush();
//        }


    }
}
