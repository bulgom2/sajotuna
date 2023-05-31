package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Orders;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 수주관리
@Data
public class OrdersDTO {

//    private Long id;    // 수주id

    private String no;    // 수주번호

    // 23/05/22 LocalDateTime으로 타입 변경
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime date;    // 수주일자

    private String status;    // 진행상태

    private String company;    // 거래처

//    private String code;    // 제품코드

    private String item;    // 제품명

    // 23/05/22 Integer로 타입 변경
    private Integer qtt;    // 제품수량

    private LocalDateTime shipDate;    // 예상 납품일

    private String ordersNo;

//    private String

//    public enum OrdersStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }

//    Orders.setId(OrdersDto.getId);
//    OrdersDto.setId(Orders.getId);

    public static ModelMapper modelMapper = new ModelMapper();

    public Orders createOrders(){
        return modelMapper.map(this, Orders.class);
    }

    public static OrdersDTO of(Orders orders){
        return modelMapper.map(orders, OrdersDTO.class);
    }

    public static List<OrdersDTO> of(List<Orders> orders){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(orders, new TypeToken<List<OrdersDTO>>() {}.getType());
    }

    public static List<Orders> toOrders(List<OrdersDTO> ordersDTOList){
        return ordersDTOList.stream()
                .map(OrdersDTO::createOrders)
                .collect(Collectors.toList());
    }

}