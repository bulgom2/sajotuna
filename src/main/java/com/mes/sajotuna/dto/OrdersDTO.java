package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Orders;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
// 수주관리
@Getter
@Setter
@ToString
public class OrdersDTO {

    private Long id;    // 수주id

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
}