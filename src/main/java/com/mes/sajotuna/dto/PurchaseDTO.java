package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Purchase;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

// 수주관리
@Getter
@Setter
public class PurchaseDto {

    private Long id;    //

    private String no;    // 발주번호

    private String date;    // 발주일자

    private String status;    // 진행상태

    private String company;    // 거래처

    private String code;    // 자재코드

    private String item;    // 자재명

    private Long qtt;    // 발주 수량

    private String shipDate;    // 입고일



//    public enum OrdersStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }

//    Orders.setId(OrdersDto.getId);
//    OrdersDto.setId(Orders.getId);

    public static ModelMapper modelMapper = new ModelMapper();

    public Purchase createPurchase(){
        return modelMapper.map(this, Purchase.class);
    }

    public static PurchaseDto of(Purchase purchase){
        return modelMapper.map(purchase, PurchaseDto.class);
    }
}
