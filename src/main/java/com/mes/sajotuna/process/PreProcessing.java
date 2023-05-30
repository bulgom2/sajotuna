package com.mes.sajotuna.process;


import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PreProcessing {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시

    public List<ManufactureDTO> preProcessing(ManufactureDTO getPP, ManufactureDTO resultMS) {

        // 원료 계량 양
        long amount = resultMS.getManufacture_qtt();
        // 최대 생산 가능 양
        long availableMax = 1000;
        // 작업 횟수 계산
        long numOfWorks = (int)Math.ceil((double) amount/availableMax);
        System.out.println("작업 횟수 계산 : "+numOfWorks);

        List<ManufactureDTO> ppList = new ArrayList<>();

        //전처리 계산 시점
        LocalDateTime workTime = resultMS.getManufacture_outTime();
        //전처리 마지막 공정 시간
        LocalDateTime processLastTime = getPP.getManufacture_outTime();

        for (int i = 0; i < numOfWorks; i++) {
            long amountPerWork = Math.min(amount, availableMax); // 각 작업당 처리할 양
            amount -= amountPerWork;

            ManufactureDTO amountWork = new ManufactureDTO(); // 새로운 객체 생성

            amountWork.setManufacture_qtt(amountPerWork);
            amountWork.setOrders_no(resultMS.getOrders_no());
            amountWork.setManufacture_item(resultMS.getManufacture_item());
            amountWork.setBeforeLot(resultMS.getThisLot());
            amountWork.setOutPut(amountPerWork);

            ppList.add(result(amountWork, workTime, processLastTime));

            processLastTime = ppList.get(i).getManufacture_outTime();
            workTime = ppList.get(i).getManufacture_outTime();
        }


        return ppList;
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
        manufactureDTO.setManufacture_outTime(now.plusSeconds((long) (1200+manufactureDTO.getManufacture_qtt()*3.6)));
        manufactureDTO.setProcess_id("PP");
        manufactureDTO.setThisLot("PP00-"+manufactureDTO.getManufacture_inTime());


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