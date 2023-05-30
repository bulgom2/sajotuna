package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Purchase;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

// 수주관리
@Getter
@Setter
public class PurchaseDTO {

    private Long id;    //

    private String no;    // 발주번호

    private LocalDateTime date;    // 발주일자

    private String company;    // 거래처

    private String item;    // 자재명

    private Long qtt;    // 발주 수량

    private LocalDateTime shipDate;    // 입고일

    private String ordersNo;

//    public enum OrdersStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }

//    Orders.setId(OrdersDto.getId);
//    OrdersDto.setId(Orders.getId);

    public static ModelMapper modelMapper = new ModelMapper();

    public Purchase createPurchase(){
        return modelMapper.map(this, Purchase.class);
    }

    public static PurchaseDTO of(Purchase purchase){
        return modelMapper.map(purchase, PurchaseDTO.class);
    }
}
