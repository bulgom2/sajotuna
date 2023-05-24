package com.mes.sajotuna.controller;

import com.mes.sajotuna.dto.OrdersDTO;

import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import com.mes.sajotuna.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    // html 불러오기(수주 등록 페이지)
    @GetMapping("/orders")
    public String orderWrite(){
        return "ordersinput";
    }

    // 수주 등록 페이지에서 수주 list 페이지로 값 전달하기
    @PostMapping("/orders")
    public String orderWritePost(OrdersDTO ordersDTO) {

        System.out.println("시작ㄱㄱㄱㄱㄱㄱㄱㄱㄱ");

        ordersDTO.setDate(LocalDateTime.now());

        ordersDTO.setStatus("proceeding");

        System.out.println("OrdersDto " + ordersDTO.toString());

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

        ordersDTO.setNo(code);

        ordersDTO.setStatus("waiting");

//        String code = "SJ" + ordersDto.getDate().toLocalDate();


//        ordersDto.setCode();

//        ordersDto.setShipDate("132456");

        Orders orders = ordersDTO.createOrders();

        System.out.println("orders " + orders);

        ordersRepository.save(orders);

        System.out.println("orders " + orders);

        return "redirect:/";
    }


    // orders 테이블에 있는 값들 표로 출력하기
    // main 페이지
    @GetMapping("/")
    public String orderList(Model model){
        System.out.println("종룍ㄱㄱㄱㄱㄱㄱㄱㄱㄱ");
        List<Orders> ordersList = ordersRepository.findAll();

        model.addAttribute("orderList", ordersList);

        return "orders";
    }


    // 수주id를 선택하면 상세 페이지 출력하기
    @GetMapping("/orders/{id}")
    public String orderDetail(Model model, @PathVariable("id") Long id){

        System.out.println("123 : " + id);

        OrdersDTO ordersDTO = ordersService.ordersDetail(id);

        System.out.println("123 : " + ordersDTO);

        model.addAttribute("ordersDto", ordersDTO);

        return "ordersdetail";
    }


    // 삭제 버튼을 누르면 해당 값 삭제하기
    @GetMapping("/orders/delete/{id}")
    public String orderDelete(@PathVariable("id") Long id){

        ordersService.ordersDelete(id);

        return "redirect:/";
    }

    // 수주 확정 버튼을 누르면 셀 값 변경
    @PostMapping("/confirm")
    @ResponseBody
    public String confirmSuju(@RequestBody OrdersDTO ordersDTO) {

        System.out.println("여기1 : " + ordersDTO);

        // 선택된 수주번호와 변경할 상태 값을 가져옴
        String selectedNo = ordersDTO.getOrdersNo();
        String newStatus = "확정"; // 변경할 상태 값

        // Orders 테이블 조회
        Orders existingOrder = ordersRepository.findByNo(selectedNo);

        System.out.println("여기1 : " + existingOrder);

        // 기존의 값을 유지하면서 진행 상태 변경
        existingOrder.setStatus(newStatus);

        // Orders 테이블 업데이트
//        ordersRepository.updateStatusByNo(selectedNo, newStatus);

        // Orders 테이블 업데이트
        ordersRepository.save(existingOrder);

        System.out.println("여기1 : " + existingOrder);

        return "success";
    }
}