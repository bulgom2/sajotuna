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

    List<ManufactureDTO> MIXList = new ArrayList<>();


    public List<ManufactureDTO> mix(List<ManufactureDTO> ccList){


        // ccList 복제
        List<ManufactureDTO> ccListClone = new ArrayList<>();
        for(int i = 0; i < ccList.size(); i++){
            ccListClone.add(new ManufactureDTO(ccList.get(i)));
        }

        for(int i = 0; i < ccListClone.size(); i++){

            LocalDateTime outTime = ccListClone.get(i).getManufacture_outTime();

            ccListClone.get(i).setManufacture_inTime(outTime);
            if(ccListClone.get(i).getManufacture_item().equals("YBC02")){
                ccListClone.get(i).setManufacture_outTime(outTime.plusSeconds((long) (ccListClone.get(i).getManufacture_qtt()*172.8)));
                ccListClone.get(i).setManufacture_item("YBC01");
            }else {
                ccListClone.get(i).setManufacture_outTime(outTime.plusSeconds((long) (ccListClone.get(i).getManufacture_qtt()*172.8*2)));
                ccListClone.get(i).setManufacture_item("HMN01");
            }
            ccListClone.get(i).setProcess_id("MIX");

            MIXList.add(ccListClone.get(i));

        }

        return MIXList;
    }

    public List<ManufactureDTO> mix( LocalDateTime MIX1,LocalDateTime MIX2, ManufactureDTO resultMS){

        ManufactureDTO mixDTO = new ManufactureDTO(resultMS);

        LocalDateTime workTime = resultMS.getManufacture_outTime();

        if(workTime.isAfter(MIX1) && workTime.isAfter(MIX2)){
            mixDTO = checkTime(workTime, mixDTO);
        }else {
            if(MIX1.isBefore(MIX2) || MIX1.isEqual(MIX2)){
                LocalDateTime targetTime = (workTime.isBefore(MIX1)) ? MIX1 : workTime;
                mixDTO = checkTime(targetTime, mixDTO);

            }else {
                if(workTime.isAfter(MIX2)){
                    mixDTO = checkTime(workTime, mixDTO);
                }else {
                    mixDTO = checkTime(MIX2, mixDTO);
                }
            }
        }


        MIXList.add(mixDTO);
        return MIXList;
    }

    public void measureSetTime(LocalDateTime now, ManufactureDTO mixDTO){

        mixDTO.setManufacture_inTime(now.plusMinutes(20));
        mixDTO.setManufacture_outTime(now.plusMinutes(500));
        mixDTO.setProcess_id("MIX");
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
