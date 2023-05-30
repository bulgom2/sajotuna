package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Stock;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

// 재고관리
@Getter @Setter
public class StockDTO {

    private Long id;    // id

    private String productName;    // 품목 정보 id

    private Long qtt;    // 입출고 수량

    private String unit;    // 단위

    private String no;    // Lot 번호

    private LocalDateTime time;    // 시간

    private String status;    // 진행상태

    public static ModelMapper modelMapper = new ModelMapper();

    public Stock createStock(){
        return modelMapper.map(this, Stock.class);
    }

    public static StockDTO of(Stock stock){
        return modelMapper.map(stock, StockDTO.class);
    }
}
