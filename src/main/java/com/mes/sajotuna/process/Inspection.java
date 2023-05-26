package com.mes.sajotuna.process;

import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Inspection {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시

    List<ManufactureDTO> isList = new ArrayList<>();

    public List<ManufactureDTO> inspection(List<ManufactureDTO> fiList, ManufactureDTO getIS){

        //검사 마지막 공정 시간
        LocalDateTime processLastTime = getIS.getManufacture_outTime();

        List<ManufactureDTO> fiListClone = new ArrayList<>();
        for(int i = 0; i < fiList.size(); i++){
            fiListClone.add(new ManufactureDTO(fiList.get(i)));
        }

        for(int i = 0; i < fiListClone.size(); i++){
            if(fiListClone.get(0).getManufacture_item().equals("YBC01")){
                fiListClone.get(i).setManufacture_qtt(fiListClone.get(i).getManufacture_qtt()*1000/80);
            }else if(fiListClone.get(0).getManufacture_item().equals("HMN01")){
                fiListClone.get(i).setManufacture_qtt(fiListClone.get(i).getManufacture_qtt()*1000/20);
            }else {
                fiListClone.get(i).setManufacture_qtt(fiListClone.get(i).getManufacture_qtt()*1000/15);
            }
        }

        for(int i = 0; i < fiListClone.size(); i++){
            isList.add(result(fiListClone.get(i), fiListClone.get(i).getManufacture_outTime(), processLastTime));
        }




        return  isList;
    }

    public ManufactureDTO result(ManufactureDTO manufactureDTO, LocalDateTime workTime , LocalDateTime processLastTime){

        if(processLastTime == null){
            checkTime(workTime, manufactureDTO);
        }else if (workTime.isAfter(processLastTime)) {  // 원료계량기 작업 계획이 없을때
            checkTime(workTime, manufactureDTO);


        } else { // 원료계량기 작업 계획이 있을때
            checkTime(processLastTime, manufactureDTO);


        }

        return manufactureDTO;
    }



    public ManufactureDTO measureSetTime(LocalDateTime now, ManufactureDTO manufactureDTO) {

        manufactureDTO.setManufacture_inTime(now.plusMinutes(20));
        manufactureDTO.setManufacture_outTime(now.plusSeconds((long) (1200+manufactureDTO.getManufacture_qtt()*0.72)));
        manufactureDTO.setProcess_id("IS");

        return manufactureDTO;
    }

    public ManufactureDTO checkTime(LocalDateTime time, ManufactureDTO manufactureDTO) {

        if (time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)) { // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                manufactureDTO = measureSetTime(time.withHour(13).withMinute(0).withSecond(0),manufactureDTO);

            } else {
                manufactureDTO = measureSetTime(time,manufactureDTO);

            }
        } else { // 현재 시간이 5시 10분 이 후 일 경우
            if (time.getDayOfWeek().getValue() != 5) {
                manufactureDTO = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            } else {
                manufactureDTO = measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            }

        }

        return manufactureDTO;
    }


}
