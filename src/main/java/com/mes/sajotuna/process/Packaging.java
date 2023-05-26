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

    public void packaging(List<ManufactureDTO> coList, ManufactureDTO getPK){

        LocalDateTime processLastTime = getPK.getManufacture_outTime();

        List<ManufactureDTO> coListClone = new ArrayList<>();
        for(int i = 0; i < coList.size(); i++){
            coListClone.add(new ManufactureDTO(coList.get(i)));
        }

        



    }
}
