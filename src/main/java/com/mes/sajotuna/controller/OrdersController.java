package com.mes.sajotuna.controller;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.entity.Purchase;
import com.mes.sajotuna.repository.OrdersRepository;
import com.mes.sajotuna.repository.PurchaseRepository;
import com.mes.sajotuna.service.OrdersService;
import com.mes.sajotuna.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseRepository purchaseRepository;


    // html 불러오기(수주 등록 페이지)
    @GetMapping("/orders/submit")
    public String orderWrite(){
        return "ordersinput";
    }

    // 수주 등록 페이지에서 수주 list 페이지로 값 전달하기
    @PostMapping("/orders/submit")
    public String orderWritePost(OrdersDTO ordersDTO) {

        // 수주 코드 생성 후 저장
        ordersDTO = ordersService.ordersMakeCode(ordersDTO);

        if(ordersDTO.getDate().getDayOfWeek().getValue() <= 5){
            PurchaseDTO purchaseDTO = purchaseService.purchaseTime(ordersDTO);
            System.out.println("발주 완료 시간 : " + purchaseDTO.getShipDate());
            System.out.println(purchaseDTO.getOrdersNo());
            System.out.println(purchaseDTO);
        } else{
            System.out.println("발주가 진행되지 않았습니다.");
        }

        return "redirect:/orders";
    }


    // orders 테이블에 있는 값들 표로 출력하기
    // main 페이지
    @GetMapping("/orders")
    public String orderList(@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                            Model model) {

        List<Orders> ordersList;

        if (startDate != null && endDate != null) {
            LocalDateTime start = startDate.atStartOfDay().withHour(0).withMinute(0).withSecond(0);
            LocalDateTime end = endDate.atTime(LocalTime.MAX);

            // 날짜 범위가 지정된 경우 해당 기간 동안의 주문 검색
            ordersList = ordersService.getOrdersByDateRange(start, end);
        } else {
            // 날짜 범위가 지정되지 않은 경우 모든 주문 검색
            ordersList = ordersRepository.findAll();
        }

        model.addAttribute("ordersList", ordersList);

        return "orders";
    }


    // 수주id를 선택하면 상세 페이지 출력하기
    @GetMapping("/orders/{id}")
    public String orderDetail(Model model, @PathVariable("id") Long id){

        OrdersDTO ordersDTO = ordersService.ordersDetail(id);

        model.addAttribute("ordersDto", ordersDTO);

        return "ordersdetail";
    }

//    // 삭제 버튼을 누르면 해당 값 삭제하기
//    @GetMapping("/orders/delete/{id}")
//    public String orderDelete(@PathVariable("id") Long id){
//
//        ordersService.ordersDelete(id);
//
//        return "redirect:/";
//    }

    // 삭제 버튼을 누르면 해당 값 삭제하기
    @PostMapping("/orders/delete")
    @ResponseBody
    public String orderDelete(@RequestBody OrdersDTO ordersDTO) {
        String ordersNo = ordersDTO.getOrdersNo();

        Orders existingOrder = ordersRepository.findByNo(ordersNo);

        if (existingOrder == null) {
            // 삭제할 수주가 없는 경우 처리 로직 추가
        } else {
            ordersRepository.delete(existingOrder);
            List<Purchase> purchase = purchaseRepository.findByOrdersNo(ordersNo);
            purchaseRepository.deleteAll(purchase);
        }

        return "success";
    }

    // 수주 확정 버튼을 누르면 셀 값 변경
    @PostMapping("/confirm")
    @ResponseBody
    public String confirmSuju(@RequestBody OrdersDTO ordersDTO) {

        // 선택된 수주번호와 변경할 상태 값을 가져옴
        String selectedNo = ordersDTO.getOrdersNo();
        String newStatus = "확정"; // 변경할 상태 값

        // Orders 테이블 조회
        Orders existingOrder = ordersRepository.findByNo(selectedNo);

        // 기존의 값을 유지하면서 진행 상태 변경
        existingOrder.setStatus(newStatus);

        // Orders 테이블 업데이트
        ordersRepository.save(existingOrder);

        return "success";
    }




}