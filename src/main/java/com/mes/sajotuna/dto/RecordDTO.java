package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Record;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

// 이력관리
@Getter @Setter
public class RecordDTO {

    private Long id;    // id

    private String no;    // Lot.no

    private String processNo;    // 공정 id

    private String beId;    // 이전 Lot.no

    private Long qtt;    // 투입량

    private Long status;    // 진행 상태

    private LocalDateTime startTime;    // 시작 시간

    private LocalDateTime endTime;    // 종료 시간

    public static ModelMapper modelMapper = new ModelMapper();

    public Record createRecord(){
        return modelMapper.map(this, Record.class);
    }

    public static RecordDTO of(Record record){
        return modelMapper.map(record, RecordDTO.class);
    }

}
