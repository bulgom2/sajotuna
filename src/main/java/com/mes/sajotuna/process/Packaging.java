package com.mes.sajotuna.process;

import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packaging {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시
    Map<String, Object> map = new HashMap<>();
    List<ManufactureDTO> pkList = new ArrayList<>();

    LocalDateTime processLastTime;



    public List<ManufactureDTO> packaging(List<ManufactureDTO> coList, ManufactureDTO getPK) {

        processLastTime = getPK.getManufacture_outTime();

        List<ManufactureDTO> coListClone = new ArrayList<>();
        for (int i = 0; i < coList.size(); i++) {
            coListClone.add(new ManufactureDTO(coList.get(i)));
        }


        //200박스 1시간  양배추 흑마늘 30 , 석류 매실 25 포 당 1박스

        if(coListClone.get(0).getManufacture_item().equals("YBC01") || coListClone.get(0).getManufacture_item().equals("HMN01")){

            for (int i = 0; i < coListClone.size(); i++){

                Long boxCount = coListClone.get(i).getManufacture_qtt()/30;
                System.out.println("boxing : "+boxCount);
                coListClone.set(i, result(coListClone.get(i), coListClone.get(i).getManufacture_outTime(), processLastTime)) ;
                coListClone.get(i).setManufacture_outTime(coListClone.get(i).getManufacture_inTime());
                for(int j = 0 ; j < boxCount; j++){

                    coListClone.set(i, check(coListClone.get(i).getManufacture_outTime(),coListClone.get(i)));

                }
                coListClone.get(i).setOutPut(boxCount);
                pkList.add(coListClone.get(i));
                processLastTime = coListClone.get(i).getManufacture_outTime();
            }
        }else if(coListClone.get(0).getManufacture_item().equals("SRJ00") || coListClone.get(0).getManufacture_item().equals("MSJ00")){
            for (int i = 0; i < coListClone.size(); i++){

                Long boxCount = coListClone.get(i).getManufacture_qtt()/25;
                System.out.println("boxing : "+boxCount);
                coListClone.set(i, result(coListClone.get(i), coListClone.get(i).getManufacture_outTime(), processLastTime)) ;
                coListClone.get(i).setManufacture_outTime(coListClone.get(i).getManufacture_inTime());
                for(int j = 0 ; j < boxCount; j++){

                    coListClone.set(i, check(coListClone.get(i).getManufacture_outTime(),coListClone.get(i)));

                }
                coListClone.get(i).setOutPut(boxCount);
                pkList.add(coListClone.get(i));
                processLastTime = coListClone.get(i).getManufacture_outTime();
            }
        }
        return pkList;
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
        manufactureDTO.setProcess_id("PK");
        manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
        manufactureDTO.setThisLot("PK-"+manufactureDTO.getManufacture_inTime());
        manufactureDTO.setFacility_id(null);


        return manufactureDTO;
    }

    public ManufactureDTO checkTime(LocalDateTime time, ManufactureDTO manufactureDTO) {

        if (time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)) { // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                manufactureDTO = measureSetTime(time.withHour(13).withMinute(0).withSecond(0),manufactureDTO);
                System.out.println("time : "+time);
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

    public ManufactureDTO measureSet(LocalDateTime now, ManufactureDTO manufactureDTO) {

        manufactureDTO.setManufacture_outTime(now.plusSeconds(18));

        return manufactureDTO;
    }

    public ManufactureDTO check(LocalDateTime time, ManufactureDTO manufactureDTO) {

        ManufactureDTO DTOpost = new ManufactureDTO(manufactureDTO);

        if (time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)) { // 현재 시간이 9시에서  5시 10분 전 일 경우
            if (time.getDayOfWeek().getValue() != 6 && time.getDayOfWeek().getValue() != 7) {
                if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                    DTOpost = measureSet(time.withHour(13), manufactureDTO);
                } else {
                    DTOpost = measureSet(time, manufactureDTO);
                }
            } else if (time.getDayOfWeek().getValue() == 6) {
                DTOpost = measureSet(time.plusDays(2).withHour(9), manufactureDTO);
            } else if (time.getDayOfWeek().getValue() == 7) {
                DTOpost = measureSet(time.plusDays(1).withHour(9), manufactureDTO);
            }

        } else { // 현재 시간이 근로시간이 아닌 경우
            if (time.toLocalTime().isAfter(LocalTime.of(0, 0, 0)) && time.toLocalTime().isBefore(start)) {
                if (time.getDayOfWeek().getValue() != 6 && time.getDayOfWeek().getValue() != 7) {
                    DTOpost = measureSet(time.withHour(9), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 6) {
                    DTOpost = measureSet(time.plusDays(2).withHour(9), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 7) {
                    DTOpost = measureSet(time.plusDays(1).withHour(9), manufactureDTO);
                }
            } else if (time.toLocalTime().isAfter(end) && time.toLocalTime().isBefore(LocalTime.of(0, 0, 0))) {

                if (time.getDayOfWeek().getValue() != 5) {
                    DTOpost = measureSet(time.plusDays(1).withHour(9), manufactureDTO);
                } else {
                    DTOpost = measureSet(time.plusDays(3).withHour(9), manufactureDTO);

                }
            }
        }

        return DTOpost;
    }







}
