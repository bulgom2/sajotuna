package com.mes.sajotuna.process;


import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.dto.PurchaseDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Measurement {
    ManufactureDTO thisManufactureDTO = new ManufactureDTO();

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 00,00); // 오전 9시
    LocalTime end   = LocalTime.of(17, 10,00); // 오후 5시 10분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 10,0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0,0); // 1시

    public ManufactureDTO measurementByMaterial(ManufactureDTO manufactureDTO, PurchaseDTO purchaseDTO){

        //원료 계량 계산 시점
        LocalDateTime now = purchaseDTO.getShipDate();

        thisManufactureDTO.setManufacture_item(purchaseDTO.getItem());
        thisManufactureDTO.setManufacture_qtt(purchaseDTO.getQtt());
        thisManufactureDTO.setOrders_no(purchaseDTO.getOrdersNo());

        LocalDateTime processLastTime = manufactureDTO.getManufacture_outtime();

        if(now.isAfter(processLastTime)){  // 원료계량기 작업 계획이 없을때
            checkTime(now);


        }else { // 원료계량기 작업 계획이 있을때
            checkTime(processLastTime);
        }

        return thisManufactureDTO;
    }


    public void measureSetTime(LocalDateTime now){

        thisManufactureDTO.setManufacture_intime(now.plusMinutes(20));
        thisManufactureDTO.setManufacture_outtime(now.plusMinutes(50));
        thisManufactureDTO.setProcess_id("MS");
    }

    public void checkTime(LocalDateTime time){
        if(time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)){ // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                measureSetTime(time.withHour(13).withMinute(0).withSecond(0));

            }else {
                measureSetTime(time);

            }
        }else{ // 현재 시간이 5시 10분 이 후 일 경우
            if(time.getDayOfWeek().getValue() != 5){
                measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0));
            }else{
                measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0));
            }
        }
    }
}
