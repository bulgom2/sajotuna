package com.mes.sajotuna.DTO;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OrderDTOTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void test4() {

        List<Orders> ordersList = ordersRepository.findAll();

        List<OrdersDTO> ordersDTOList = OrdersDTO.of(ordersList);

        System.out.println("ordersList : " + ordersList);
        System.out.println("ordersDTOList : " + ordersDTOList);

        ordersList = OrdersDTO.toOrders(ordersDTOList);

        List<Orders> ordersList2 = OrdersDTO.toOrders(ordersDTOList);

        System.out.println("ordersList : " + ordersList2);

    }
}
