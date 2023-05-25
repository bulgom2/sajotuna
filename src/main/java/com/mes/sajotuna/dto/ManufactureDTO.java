package com.mes.sajotuna.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ManufactureDTO implements Cloneable{

    int manufacture_id;
    String manufacture_item;
    String process_id;
    String orders_no;
    String facility_id;
    long manufacture_qtt;
    LocalDateTime manufacture_inTime;
    LocalDateTime manufacture_outTime;


    public ManufactureDTO (){

    }

    public ManufactureDTO(ManufactureDTO m){
        this.manufacture_id = m.getManufacture_id();
        this.manufacture_item = m.getManufacture_item();
        this.process_id = m.getProcess_id();
        this.orders_no = m.getOrders_no();
        this.facility_id = m.getFacility_id();
        this.manufacture_qtt = m.getManufacture_qtt();
        this.manufacture_inTime = m.getManufacture_inTime();
        this.manufacture_outTime = m.getManufacture_outTime();
    }

    @Override
    public ManufactureDTO clone() {
        try {
            return (ManufactureDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


}
