package com.mes.sajotuna.calculation;





import com.example.domain.OrdersDTO;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class main {

    public static void main(String[] args) {

        OrdersDTO orders = new OrdersDTO();

        // 수주 일
        LocalDateTime now = LocalDateTime.now();
        // 한국 시간으로 설정
        ZonedDateTime koreaDateTime = ZonedDateTime.of(now, ZoneId.of("Asia/Seoul"));
        // 년 월 일 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        orders.setOrders_item("ybc");           //제품 입력
        orders.setOrders_qtt(60);               //제품 수량 입력
        orders.setOrders_date(now);             //현재 시간 입력


        System.out.println(now);
        // 수주일 요일
        DayOfWeek dayOfWeek = koreaDateTime.getDayOfWeek();
        System.out.println("요일: " + dayOfWeek);

        // 수주일 요일(숫자)
        int dayOfWeekValue = dayOfWeek.getValue();
        System.out.println("요일(숫자): " + dayOfWeekValue);

        /*StorageDTO storage = new StorageDTO();

        if(orders.getOrders_qtt() <= storage.getYbc_box()){
            String formattedDate = now.format(formatter);
            System.out.println("충분해요 바로 납품할께요");
            System.out.println("납품 예정일 : " + formattedDate );
        }else {
            int need = orders.getOrders_qtt()  - storage.getYbc_box();
            System.out.println("부족해요 "+need+"box의 "+"양배추"+" 생산이 필요해요");
            OrderCalculation orderCalculation = new OrderCalculation();
            int needAmount = orderCalculation.ybcOrder(need);
            System.out.println(needAmount);
        }*/


    }
}
