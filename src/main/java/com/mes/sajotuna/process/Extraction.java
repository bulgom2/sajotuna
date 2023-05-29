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
    LocalTime start = LocalTime.of(9, 00, 00); // 오전 9시
    LocalTime end = LocalTime.of(17, 00, 00); // 오후 5시 10분

    //점심 시간
    LocalTime startLaunchTime = LocalTime.of(11, 00, 0); // 11시 10분
    LocalTime endLaunchTime = LocalTime.of(13, 0, 0); // 1시
    Map<String, Object> map = new HashMap<>();

    List<ManufactureDTO> ccList = new ArrayList<>();

    public List<ManufactureDTO> extraction(List<ManufactureDTO> ppList, LocalDateTime EA1, LocalDateTime EA2) {

        List<ManufactureDTO> ppListClone = new ArrayList<>();
        for(int i = 0; i < ppList.size(); i++){
            ppListClone.add(new ManufactureDTO(ppList.get(i)));
        }


        Map<String, Object> resultMap;

        List<ManufactureDTO> temporalList = new ArrayList<>();

        if (ppListClone.get(0).getManufacture_item().equals("HMN02")) {
            for (int i = 0; i < ppListClone.size(); i++) {
                ManufactureDTO dto = ppListClone.get(i);
                Long amount = dto.getManufacture_qtt();

                if (amount > 500L) {
                    ManufactureDTO clone1 = dto.clone();
                    clone1.setManufacture_qtt(500);
                    temporalList.add(clone1);

                    ManufactureDTO clone2 = dto.clone();
                    clone2.setManufacture_qtt(amount - 500);
                    temporalList.add(clone2);
                } else {
                    temporalList.add(dto);
                }
            }
        }else {
            temporalList = ppListClone;
        }

        for (int i = 0; i < temporalList.size(); i++) {

            System.out.println("EA1 : " + EA1);
            System.out.println("EA2 : " + EA2);

            resultMap = result(temporalList.get(i), EA1, EA2);
            ccList.add((ManufactureDTO) resultMap.get("manufactureDTO"));
            System.out.println((i + 1) + "번째 추출 시작 시간 : " + ccList.get(i).getManufacture_inTime() + "시작 요일 : " + ccList.get(i).getManufacture_inTime().getDayOfWeek().getValue());
            System.out.println((i + 1) + "번째 추출 종료 시간 : " + ccList.get(i).getManufacture_outTime() + "종료 요일 : " + ccList.get(i).getManufacture_outTime().getDayOfWeek().getValue());
            EA1 = (LocalDateTime) resultMap.get("EA1");
            EA2 = (LocalDateTime) resultMap.get("EA2");
        }
        return ccList;
    }

    public Map<String, Object> result(ManufactureDTO manufactureDTO, LocalDateTime EA1, LocalDateTime EA2) {

        LocalDateTime workTime = manufactureDTO.getManufacture_outTime();


        if (workTime.isAfter(EA1) && workTime.isAfter(EA2)) { //두 설비 모두 가동중이지 않았을때
            manufactureDTO = checkTime(workTime, manufactureDTO);
            EA1 = manufactureDTO.getManufacture_outTime();
            map.put("manufactureDTO", manufactureDTO);
            map.put("EA1", EA1);
            map.put("EA2", EA2);
            System.out.println("map : "+map);
            System.out.println("설비1 작업 (두작업 모두 없을 경우)");

        } else {  // 설비가 하나라도 가동중일 경우
            if (EA1.isBefore(EA2) || EA1.isEqual(EA2)) {
                LocalDateTime targetTime = (workTime.isBefore(EA1)) ? EA1 : workTime;
                manufactureDTO = checkTime(targetTime, manufactureDTO);
                EA1 = manufactureDTO.getManufacture_outTime();
                map.put("manufactureDTO", manufactureDTO);
                map.put("EA1", EA1);
                map.put("EA2", EA2);
                if (EA1.isBefore(EA2)) {
                    System.out.println("설비1 작업 (설비1 작업이 빨리 끝나는 경우)");
                } else {
                    System.out.println("설비1 작업 (설비1 작업과 설비2 작업이 같이 끝나는 경우)");
                }
            } else { // 설비2이 먼저 끝날 경우
                if (workTime.isAfter(EA2)) { // 설비 2가 가동중이지 않을 경우
                    manufactureDTO = checkTime(workTime, manufactureDTO);
                    EA2 = manufactureDTO.getManufacture_outTime();
                    map.put("manufactureDTO", manufactureDTO);
                    map.put("EA1", EA1);
                    map.put("EA2", EA2);
                    System.out.println("설비2 작업 (설비2 작업이 가동중이지 않을 경우)");

                } else { // 설비 2가 가동중일 경우
                    manufactureDTO = checkTime(EA2, manufactureDTO);
                    EA2 = manufactureDTO.getManufacture_outTime();
                    map.put("manufactureDTO", manufactureDTO);
                    map.put("EA1", EA1);
                    map.put("EA2", EA2);
                    System.out.println("설비2 작업 (설비2 작업이 가동중인 경우)");
                }

            }
        }

        return map;
    }

    // 생산 계획 값 입력 메소드
    public ManufactureDTO measureSetTime(LocalDateTime now, ManufactureDTO manufactureDTO) {

        ManufactureDTO DTOClone = new ManufactureDTO(manufactureDTO);

        DTOClone.setManufacture_inTime(now.plusMinutes(60));
        if (DTOClone.getManufacture_item().equals("YBC02") ) {
            DTOClone.setManufacture_outTime(now.plusSeconds((long) (3600 + DTOClone.getManufacture_qtt() * 172.8)));
        } else {
            DTOClone.setManufacture_outTime(now.plusSeconds((long) (3600 + DTOClone.getManufacture_qtt() * 172.8 * 2)));
        }
        DTOClone.setProcess_id("CC");
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

        } else { // 현재 시간이 근로시간이 아닌 경우
            if (time.toLocalTime().isAfter(LocalTime.of(0, 0, 0)) && time.toLocalTime().isBefore(start)) {
                if (time.getDayOfWeek().getValue() != 6 && time.getDayOfWeek().getValue() != 7) {
                    DTOpost = measureSetTime(time.withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 6) {
                    DTOpost = measureSetTime(time.plusDays(2).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else if (time.getDayOfWeek().getValue() == 7) {
                    DTOpost = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                }
            } else if (time.toLocalTime().isAfter(end) && time.toLocalTime().isBefore(LocalTime.of(0, 0, 0))) {

                if (time.getDayOfWeek().getValue() != 5) {
                    DTOpost = measureSetTime(time.plusDays(1).withHour(9).withMinute(0).withSecond(0), manufactureDTO);
                } else {
                    DTOpost = measureSetTime(time.plusDays(3).withHour(9).withMinute(0).withSecond(0), manufactureDTO);

                }
            }
        }



        return DTOpost;
    }
}
