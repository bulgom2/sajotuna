package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Precord;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

// 제품별 이력관리
@Getter @Setter
public class PrecordDTO {

    private Long id;  // id

    private String ordersId;  // 수주 번호

    private String lot1;  // 로트1

    private String lot2;  // 로트2

    private String lot3;  // 로트3

    private String lot4;  // 로트4

    private String lot5;  // 로트5

    private String lot6;  // 로트6

    private String lot7;  // 로트7

    private String lot8;  // 로트8

    private String lot9;  // 로트9

    private String lot10;  // 로트10

    private String lot11;  // 로트11

    public static ModelMapper modelMapper = new ModelMapper();

    public Precord createPrecord(){
        return modelMapper.map(this, Precord.class);
    }

    public static PrecordDTO of(Precord precord){
        return modelMapper.map(precord, PrecordDTO.class);
    }


}
