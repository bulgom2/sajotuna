package com.mes.sajotuna.process;


import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class PreProcessing {

    ManufactureDTO thisManufactureDTO = new ManufactureDTO();

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시

    public ManufactureDTO preProcessing(ManufactureDTO manufactureDTO, LocalDateTime referenceTime, LocalDateTime workTime ) {

        //전처리 계산 시점
        LocalDateTime now = workTime;


        LocalDateTime processLastTime = referenceTime;

        if (now.isAfter(processLastTime)) {  // 원료계량기 작업 계획이 없을때
            checkTime(now, manufactureDTO);


        } else { // 원료계량기 작업 계획이 있을때
            checkTime(processLastTime, manufactureDTO);


        }
        thisManufactureDTO.setManufacture_qtt(manufactureDTO.getManufacture_qtt());
        return thisManufactureDTO;

    }

    public void measureSetTime(LocalDateTime now, ManufactureDTO manufactureDTO) {

        thisManufactureDTO.setManufacture_intime(now.plusMinutes(20));
        thisManufactureDTO.setManufacture_outtime(now.plusSeconds((long) (1200+manufactureDTO.getManufacture_qtt()*3.6)));
        thisManufactureDTO.setProcess_id("PP");

    }

    public void checkTime(LocalDateTime time, ManufactureDTO manufactureDTO) {
        if (time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)) { // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                measureSetTime(time.withHour(13).withMinute(0).withSecond(0),manufactureDTO);

            } else {
                measureSetTime(time,manufactureDTO);

            }
        } else { // 현재 시간이 5시 10분 이 후 일 경우
            if (time.getDayOfWeek().getValue() != 5) {
                measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            } else {
                measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            }

        }
    }
}