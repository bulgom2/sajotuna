package com.mes.sajotuna.controller;

import com.mes.sajotuna.dto.OrdersDto;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import com.mes.sajotuna.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;


    @GetMapping("/order")
    public String getTest(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "ordersinput";
    }

    @PostMapping("/orders")
    public String createTests(OrdersDto ordersDto) {

        System.out.println("OrdersDto " + ordersDto.toString());

//        ordersDto.setShipDate("132456");

        Orders orders = ordersDto.createOrders();

        System.out.println("orders " + orders);

        ordersRepository.save(orders);

        System.out.println("orders " + orders);

        return "redirect:/order/list";
    }

    @GetMapping("/order/list")
    public String getTest2(Model model){
        List<Orders> ordersList = ordersRepository.findAll();

        model.addAttribute("orderList", ordersList);

        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String getTest3(Model model, @PathVariable("id") Long id){

        System.out.println("123 : " + id);


        OrdersDto ordersDto = ordersService.ordersDetail(id);

        System.out.println("123 : " + ordersDto);

        model.addAttribute("ordersDto", ordersDto);

        return "ordersdetail";
    }

}
