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


    // html 불러오기(수주 동록 페이지)
    @GetMapping("/orders")
    public String orderWrite(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "ordersinput";
    }

    // 수주 등록 페이지에서 수주 list 페이지로 값 전달하기
    @PostMapping("/orders")
    public String orderWritePost(OrdersDto ordersDto) {

        System.out.println("OrdersDto " + ordersDto.toString());

//        ordersDto.setShipDate("132456");

        Orders orders = ordersDto.createOrders();

        System.out.println("orders " + orders);

        ordersRepository.save(orders);

        System.out.println("orders " + orders);

        return "redirect:/orders/list";
    }


    // orders 테이블에 있는 값들 표로 출력하기
    @GetMapping("/orders/list")
    public String orderList(Model model){
        List<Orders> ordersList = ordersRepository.findAll();

        model.addAttribute("orderList", ordersList);

        return "orders";
    }


    // 수주id를 선택하면 상세 페이지 출력하기
    @GetMapping("/orders/{id}")
    public String orderDetail(Model model, @PathVariable("id") Long id){

        System.out.println("123 : " + id);


        OrdersDto ordersDto = ordersService.ordersDetail(id);

        System.out.println("123 : " + ordersDto);

        model.addAttribute("ordersDto", ordersDto);

        return "ordersdetail";
    }


    // 삭제 버튼을 누르면 해당 값 삭제하기
    @GetMapping("/orders/delete/{id}")
    public String orderDelete(@PathVariable("id") Long id){

        ordersService.ordersDelete(id);

        return "redirect:/orders/list";
    }

}