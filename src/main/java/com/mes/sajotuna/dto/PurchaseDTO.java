package com.mes.sajotuna.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseDTO {

    public Long id;                 //id
    public String ordersNo;         //수주번호
    public String item;             //제품 명
    public Long qtt;                //제품 수량
    public LocalDateTime shipDate;  //발주 입고일
}
