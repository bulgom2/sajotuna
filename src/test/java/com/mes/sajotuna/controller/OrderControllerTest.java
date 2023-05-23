package com.mes.sajotuna.controller;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@SpringBootTest
public class OrderControllerTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void test4() {

        Orders orders = ordersRepository.findById(5L)
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("엔티티 : " + orders);

        OrdersDTO ordersDTO = OrdersDTO.of(orders);

        System.out.println("수주 dto : " + ordersDTO);

        LocalDateTime orderDay = ordersDTO.getDate();

        String dateTime[] = {orderDay.getMonthValue()+"", orderDay.getDayOfMonth()+"", orderDay.getHour()+"", orderDay.getMinute()+""};

        String code = "SJ" + orderDay.getYear();

        for(int i=0; i<dateTime.length; i++){
            if(dateTime[i].length() < 2){
                dateTime[i] = "0" + dateTime[i];
            }
            code += dateTime[i];
        }

        System.out.println("수주번호 : " + code);

    }
}
