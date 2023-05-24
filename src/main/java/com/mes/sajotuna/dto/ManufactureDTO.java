package com.mes.sajotuna.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ManufactureDTO {

    int manufacture_id;
    String manufacture_item;
    String process_id;
    LocalDateTime manufacture_intime;
    LocalDateTime manufacture_outtime;
    Long manufacture_qtt;
    String orders_id;


}
