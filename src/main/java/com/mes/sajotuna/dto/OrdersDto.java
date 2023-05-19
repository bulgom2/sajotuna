package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Orders;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

// 수주관리
@Getter
@Setter
public class OrdersDto {

    private Long id;    // 수주id

    private String no;    // 수주번호

    private String date;    // 수주일자

    private String status;    // 진행상태

    private String company;    // 거래처

    private String code;    // 제품코드

    private String item;    // 제품명

    private String qtt;    // 제품수량

    private String shipDate;    // 예상 납품일

//    public enum OrdersStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }

    public static ModelMapper modelMapper = new ModelMapper();

    public Orders createOrders(){
        return modelMapper.map(this, Orders.class);
    }

    public static OrdersDto of(Orders orders){
        return modelMapper.map(orders, OrdersDto.class);
    }
}
