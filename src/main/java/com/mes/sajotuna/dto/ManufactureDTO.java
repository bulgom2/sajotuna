package com.mes.sajotuna.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ManufactureDTO {

    int manufacture_id;
    String manufacture_item;
    String process_id;
    String orders_no;
    String facility_id;
    long manufacture_qtt;
    LocalDateTime manufacture_intime;
    LocalDateTime manufacture_outtime;


    public ManufactureDTO (){

    }

    public ManufactureDTO(ManufactureDTO m){
        this.manufacture_id = m.getManufacture_id();
        this.manufacture_item = m.getManufacture_item();
        this.process_id = m.getProcess_id();
        this.orders_no = m.getOrders_no();
        this.facility_id = m.getFacility_id();
        this.manufacture_qtt = m.getManufacture_qtt();
        this.manufacture_intime = m.getManufacture_intime();
        this.manufacture_outtime = m.getManufacture_outtime();
    }


}
