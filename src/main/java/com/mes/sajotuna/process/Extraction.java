package com.mes.sajotuna.process;

import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Extraction {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 00,00); // 오전 9시
    LocalTime end   = LocalTime.of(17, 00,00); // 오후 5시 10분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 00,0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0,0); // 1시
    Map<Object, Object> map = new HashMap<>();


    public List<ManufactureDTO> extraction(List<ManufactureDTO> ppList, LocalDateTime EA1, LocalDateTime EA2){

        List<ManufactureDTO> ccList = new ArrayList<>();
        Map<Object, Object> resultMap;

        for(int i=0; i < ppList.size(); i++){

            System.out.println("EA1 : "+EA1);
            System.out.println("EA2 : "+EA2);

            resultMap = result(ppList.get(i),EA1, EA2);
            ccList.add((ManufactureDTO) resultMap.get("manufactureDTO"));
            System.out.println((i+1)+"번째 추출 시작 시간 : "+ccList.get(i).getManufacture_intime());
            System.out.println((i+1)+"번째 추출 종료 시간 : "+ccList.get(i).getManufacture_outtime());
            EA1 = (LocalDateTime) resultMap.get("EA1");
            EA2 = (LocalDateTime) resultMap.get("EA2");
        }





        return ccList;
    }
    public Map<Object, Object> result(ManufactureDTO manufactureDTO, LocalDateTime EA1, LocalDateTime EA2){

        LocalDateTime workTime = manufactureDTO.getManufacture_outtime();


        if(workTime.isAfter(EA1) && workTime.isAfter(EA2)){ //두 설비 모두 가동중이지 않았을때
            manufactureDTO = checkTime(workTime, manufactureDTO);
            EA1 = manufactureDTO.getManufacture_outtime();
            map.put("manufactureDTO",manufactureDTO);
            map.put("EA1",EA1);
            map.put("EA2",EA2);
            System.out.println("설비1 작업 (두작업 모두 없을 경우)");

        }else{  // 설비가 하나라도 가동중일 경우
            if(EA1.isBefore(EA2)){   // 설비1이 먼저 끝날 경우
                manufactureDTO = checkTime(EA1, manufactureDTO);
                EA1 = manufactureDTO.getManufacture_outtime();
                map.put("manufactureDTO",manufactureDTO);
                map.put("EA1",EA1);
                map.put("EA2",EA2);
                System.out.println("설비1 작업 (설비1 작업이 빨리 끝나는 경우)");
            }else { // 설비2이 먼저 끝날 경우
                if(workTime.isAfter(EA2)){ // 설비 2가 가동중이지 않을 경우
                    manufactureDTO = checkTime(workTime, manufactureDTO);
                    EA2 = manufactureDTO.getManufacture_outtime();
                    map.put("manufactureDTO",manufactureDTO);
                    map.put("EA1",EA1);
                    map.put("EA2",EA2);
                    System.out.println("설비2 작업 (설비2 작업이 빨리 끝나는 경우)");

                }else { // 설비 2가 가동중일 경우

                    manufactureDTO = checkTime(EA2, manufactureDTO);
                    EA2 = manufactureDTO.getManufacture_outtime();
                    map.put("manufactureDTO",manufactureDTO);
                    map.put("EA1",EA1);
                    map.put("EA2",EA2);
                    System.out.println("설비2 작업 (설비2 작업이 빨리 끝나는 경우)");
                }

            }
        }

        return map;
    }

    // 생산 계획 값 입력 메소드
    public ManufactureDTO measureSetTime(LocalDateTime now,ManufactureDTO manufactureDTO){

        manufactureDTO.setManufacture_intime(now.plusMinutes(60));
        manufactureDTO.setManufacture_outtime(now.plusSeconds((long) (3600+manufactureDTO.getManufacture_qtt()*172.8)));
        manufactureDTO.setProcess_id("CC");

        return manufactureDTO;
    }
    // 생산 계획 시간 체크
    public ManufactureDTO checkTime(LocalDateTime time, ManufactureDTO manufactureDTO){
        if(time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)){ // 현재 시간이 9시에서  5시 10분 전 일 경우

            if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                measureSetTime(time.withHour(13).withMinute(0).withSecond(0), manufactureDTO);

            }else {
                measureSetTime(time, manufactureDTO);

            }
        }else{ // 현재 시간이 5시 10분 이 후 일 경우
            if(time.getDayOfWeek().getValue() != 5){
                measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            }else{
                measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0),manufactureDTO);
            }
        }

        return manufactureDTO;
    }
}