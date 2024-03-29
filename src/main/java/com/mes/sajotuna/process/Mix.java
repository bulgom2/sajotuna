package com.mes.sajotuna.process;


import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mix {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시
    Map<String, Object> map = new HashMap<>();

    List<ManufactureDTO> MXList = new ArrayList<>();


    public List<ManufactureDTO> mix(List<ManufactureDTO> ccList){


        // ccList 복제
        List<ManufactureDTO> ccListClone = new ArrayList<>();
        for(int i = 0; i < ccList.size(); i++){
            ccListClone.add(new ManufactureDTO(ccList.get(i)));
        }

        for(int i = 0; i < ccListClone.size(); i++){

            LocalDateTime outTime = ccListClone.get(i).getManufacture_outTime();

            ccListClone.get(i).setManufacture_inTime(outTime);

            ccListClone.get(i).setManufacture_qtt(ccListClone.get(i).getOutPut());

            if(ccListClone.get(i).getManufacture_item().equals("YBC02")){
                ccListClone.get(i).setManufacture_outTime(outTime.plusHours(24));
                ccListClone.get(i).setManufacture_item("YBC01");
            }else {
                ccListClone.get(i).setManufacture_outTime(outTime.plusHours(24));
                ccListClone.get(i).setManufacture_item("HMN01");
            }
            ccListClone.get(i).setProcess_id("MX");

            if(ccListClone.get(i).getFacility_id().equals("CCO1")){
                ccListClone.get(i).setFacility_id("MX01");
            }else {
                ccListClone.get(i).setFacility_id("MX02");
            }

            ccListClone.get(i).setBeforeLot(ccListClone.get(i).getThisLot());
            ccListClone.get(i).setThisLot(ccListClone.get(i).getFacility_id()+"-"+ccListClone.get(i).getManufacture_inTime());
            MXList.add(ccListClone.get(i));

        }

        return MXList;
    }

    public List<ManufactureDTO> mix( LocalDateTime MX1,LocalDateTime MX2, ManufactureDTO resultMS){

        ManufactureDTO mixDTO = new ManufactureDTO(resultMS);

        LocalDateTime workTime = resultMS.getManufacture_outTime();

        if(workTime.isAfter(MX1) && workTime.isAfter(MX2)){
            mixDTO = checkTime(workTime, mixDTO);
            mixDTO.setFacility_id("MX01");
        }else {
            if(MX1.isBefore(MX2) || MX1.isEqual(MX2)){
                LocalDateTime targetTime = (workTime.isBefore(MX1)) ? MX1 : workTime;
                mixDTO = checkTime(targetTime, mixDTO);

            }else {
                if(workTime.isAfter(MX2)){
                    mixDTO = checkTime(workTime, mixDTO);
                }else {
                    mixDTO = checkTime(MX2, mixDTO);
                }
            }
        }


        MXList.add(mixDTO);
        return MXList;
    }

    public void measureSetTime(LocalDateTime now, ManufactureDTO mixDTO){

        mixDTO.setManufacture_inTime(now.plusMinutes(20));
        mixDTO.setManufacture_outTime(now.plusMinutes(500));
        mixDTO.setProcess_id("MX");
        mixDTO.setOutPut(mixDTO.getManufacture_qtt()*3);

    }

    public ManufactureDTO checkTime(LocalDateTime time, ManufactureDTO mixDTO){
        if(time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)){ // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                measureSetTime(time.withHour(13).withMinute(0).withSecond(0), mixDTO);

            }else {
                measureSetTime(time, mixDTO);

            }
        }else{ // 현재 시간이 5시 10분 이 후 일 경우
            if(time.getDayOfWeek().getValue() != 5){
                measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), mixDTO);
            }else{
                measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0), mixDTO);
            }
        }
        return mixDTO;
    }
}
