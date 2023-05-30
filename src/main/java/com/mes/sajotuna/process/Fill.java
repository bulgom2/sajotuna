package com.mes.sajotuna.process;

import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fill {

    //하루 일과 시간
    LocalTime start = LocalTime.of(9, 0, 0); // 오전 9시
    LocalTime end = LocalTime.of(17, 40, 00); // 오후 5시 40분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 40, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시
    Map<String, Object> map = new HashMap<>();
    List<ManufactureDTO> FIList = new ArrayList<>();
    Map<String,Object> resultMap = new HashMap<>();

    public List<ManufactureDTO> fill(List<ManufactureDTO> MXList, LocalDateTime FI1, LocalDateTime FI2){



        List<ManufactureDTO> MXListClone = new ArrayList<>();
        for(int i = 0; i < MXList.size(); i++){
            MXListClone.add(new ManufactureDTO(MXList.get(i)));
        }

        /*// 임시 리스트 (충진기 2개에 나눠담기 위해 이전 리스트들을 2개로 나눔)
        List<ManufactureDTO> temporalList = new ArrayList<>();

        for(int i = 0; i < MXListClone.size(); i++){
            Long amount = MXListClone.get(i).getManufacture_qtt();
            if(MXListClone.get(0).getManufacture_item().equals("YBC01")){

            }else if(MXListClone.get(0).getManufacture_item().equals("HMN01")){

            }else{

            }

            temporalList.add(MXListClone.get(i));
        }*/

        //MXListClone = temporalList;

        for(int i = 0; i < MXListClone.size(); i++){
            System.out.println(MXListClone.get(i));
        }



        for (int i = 0; i < MXListClone.size(); i++) {

            System.out.println("FI1 : " + FI1);
            System.out.println("FI2 : " + FI2);
            MXListClone.get(i).setManufacture_qtt(MXListClone.get(i).getOutPut());
            resultMap = result(MXListClone.get(i), FI1, FI2);
            FIList.add((ManufactureDTO) resultMap.get("manufactureDTO"));
            FI1 = (LocalDateTime) resultMap.get("FI1");
            FI2 = (LocalDateTime) resultMap.get("FI2");
        }
        return FIList;


    }


    public Map<String, Object> result(ManufactureDTO manufactureDTO, LocalDateTime FI1, LocalDateTime FI2) {

        LocalDateTime workTime = manufactureDTO.getManufacture_outTime();


        if (workTime.isAfter(FI1) && workTime.isAfter(FI2)) { //두 설비 모두 가동중이지 않았을때
            manufactureDTO = checkTime(workTime, manufactureDTO);
            if(manufactureDTO.getManufacture_item().equals("YBC01") || manufactureDTO.getManufacture_item().equals("HMN01")){
                manufactureDTO.setFacility_id("FI01");
            }else {
                manufactureDTO.setFacility_id("FI03");
            }
            manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
            manufactureDTO.setThisLot(manufactureDTO.getFacility_id()+"-"+manufactureDTO.getManufacture_inTime());
            FI1 = manufactureDTO.getManufacture_outTime();
            map.put("manufactureDTO", manufactureDTO);
            map.put("FI1", FI1);
            map.put("FI2", FI2);
            System.out.println("map : "+map);
            System.out.println("설비1 작업 (두작업 모두 없을 경우)");

        } else {  // 설비가 하나라도 가동중일 경우
            if (FI1.isBefore(FI2) || FI1.isEqual(FI2)) {

                LocalDateTime targetTime = (workTime.isBefore(FI1)) ? FI1 : workTime;
                manufactureDTO = checkTime(targetTime, manufactureDTO);
                if(manufactureDTO.getManufacture_item().equals("YBC01") || manufactureDTO.getManufacture_item().equals("HMN01")){
                    manufactureDTO.setFacility_id("FI01");
                }else {
                    manufactureDTO.setFacility_id("FI03");
                }
                manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
                manufactureDTO.setThisLot(manufactureDTO.getFacility_id()+"-"+manufactureDTO.getManufacture_inTime());
                FI1 = manufactureDTO.getManufacture_outTime();
                map.put("manufactureDTO", manufactureDTO);
                map.put("FI1", FI1);
                map.put("FI2", FI2);
                if (FI1.isBefore(FI2)) {
                    System.out.println("설비1 작업 (설비1 작업이 빨리 끝나는 경우)");
                } else {
                    System.out.println("설비1 작업 (설비1 작업과 설비2 작업이 같이 끝나는 경우)");
                }
            } else { // 설비2이 먼저 끝날 경우
                if (workTime.isAfter(FI2)) { // 설비 2가 가동중이지 않을 경우
                    manufactureDTO = checkTime(workTime, manufactureDTO);
                    if(manufactureDTO.getManufacture_item().equals("YBC01") || manufactureDTO.getManufacture_item().equals("HMN01")){
                        manufactureDTO.setFacility_id("FI02");
                    }else {
                        manufactureDTO.setFacility_id("FI04");
                    }
                    manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
                    manufactureDTO.setThisLot(manufactureDTO.getFacility_id()+"-"+manufactureDTO.getManufacture_inTime());
                    FI2 = manufactureDTO.getManufacture_outTime();
                    map.put("manufactureDTO", manufactureDTO);
                    map.put("FI1", FI1);
                    map.put("FI2", FI2);
                    System.out.println("설비2 작업 (설비2 작업이 가동중이지 않을 경우)");

                } else { // 설비 2가 가동중일 경우
                    manufactureDTO = checkTime(FI2, manufactureDTO);
                    if(manufactureDTO.getManufacture_item().equals("YBC01") || manufactureDTO.getManufacture_item().equals("HMN01")){
                        manufactureDTO.setFacility_id("FI02");
                    }else {
                        manufactureDTO.setFacility_id("FI04");
                    }
                    manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
                    manufactureDTO.setThisLot(manufactureDTO.getFacility_id()+"-"+manufactureDTO.getManufacture_inTime());
                    FI2 = manufactureDTO.getManufacture_outTime();
                    map.put("manufactureDTO", manufactureDTO);
                    map.put("FI1", FI1);
                    map.put("FI2", FI2);
                    System.out.println("설비2 작업 (설비2 작업이 가동중인 경우)");
                }

            }
        }

        return map;
    }


    // 생산 계획 값 입력 메소드
    public ManufactureDTO measureSetTime(LocalDateTime now, ManufactureDTO manufactureDTO) {

        ManufactureDTO DTOClone = new ManufactureDTO(manufactureDTO);

        DTOClone.setManufacture_inTime(now.plusMinutes(20));
        if (DTOClone.getManufacture_item().equals("YBC01") ) {
            DTOClone.setManufacture_outTime(now.plusSeconds((long) (1200 + DTOClone.getManufacture_qtt() * 30)));
            DTOClone.setOutPut(DTOClone.getManufacture_qtt()*1000/80);
        } else if(DTOClone.getManufacture_item().equals("HMN01")){
            DTOClone.setManufacture_outTime(now.plusSeconds((long) (1200 + DTOClone.getManufacture_qtt() * 120)));
            DTOClone.setOutPut(DTOClone.getManufacture_qtt()*1000/20);
        } else {
            DTOClone.setManufacture_outTime(now.plusSeconds((long) (1200 + DTOClone.getManufacture_qtt() * (3600.0/26.25))));
            DTOClone.setOutPut(DTOClone.getManufacture_qtt()*1000/15);

        }
        DTOClone.setProcess_id("FI");
        System.out.println("작업량 : " + DTOClone.getManufacture_qtt());
        System.out.println("여긴데 : " + DTOClone);
        return DTOClone;
    }

    // 생산 계획 시간 체크
    public ManufactureDTO checkTime(LocalDateTime time, ManufactureDTO manufactureDTO) {

        ManufactureDTO DTOpost = new ManufactureDTO(manufactureDTO);
        if (time.toLocalTime().isAfter(start) && time.toLocalTime().isBefore(end)) { // 현재 시간이 9시에서  5시 10분 전 일 경우
            if (time.getDayOfWeek().getValue() != 6 && time.getDayOfWeek().getValue() != 7) {
                if (time.toLocalTime().isAfter(startLaunchTime) && time.toLocalTime().isBefore(endLaunchTime)) { //점심 시간 고려
                    DTOpost = measureSetTime(time.withHour(13).withMinute(0).withSecond(0), manufactureDTO);
                } else {
                    DTOpost = measureSetTime(time, manufactureDTO);
                }
            } else if (time.getDayOfWeek().getValue() == 6) {
                DTOpost = measureSetTime(time.plusDays(2).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
            } else if (time.getDayOfWeek().getValue() == 7) {
                DTOpost = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
            }

        } else { // 현재 시간이 5시 10분 이 후 일 경우
            if (time.toLocalTime().isAfter(LocalTime.of(0, 0, 0)) && time.toLocalTime().isBefore(start)) {
                if (time.getDayOfWeek().getValue() != 6 && time.getDayOfWeek().getValue() != 7) {
                    DTOpost = measureSetTime(time.withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 6) {
                    DTOpost = measureSetTime(time.plusDays(2).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 7) {
                    DTOpost = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                }
            } else if (time.toLocalTime().isAfter(end) || time.toLocalTime().isBefore(LocalTime.of(0, 0, 0))) {
                if (time.getDayOfWeek().getValue() != 5) {
                    DTOpost = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else {
                    DTOpost = measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                }
            }else {
                System.out.println("클남");
            }
        }



        return DTOpost;
    }

}



