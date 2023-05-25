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

    public List<ManufactureDTO> fill(List<ManufactureDTO> MIXList, LocalDateTime FI1, LocalDateTime FI2){

        List<ManufactureDTO> MIXListClone = new ArrayList<>();
        for(int i = 0; i < MIXList.size(); i++){
            MIXListClone.add(new ManufactureDTO(MIXList.get(i)));
        }

        List<ManufactureDTO> temporalList = new ArrayList<>();

        for(int i = 0; i < MIXListClone.size(); i++){
            Long amount = MIXListClone.get(i).getManufacture_qtt();
            if(MIXListClone.get(0).getManufacture_item().equals("YBC01")){
                MIXListClone.get(i).setManufacture_qtt((long) ((amount*2*0.8)/2));
            }else if(MIXListClone.get(0).getManufacture_item().equals("HMN01")){
                MIXListClone.get(i).setManufacture_qtt((long) (amount*2*0.6));
            }else{
                MIXListClone.get(i).setManufacture_qtt(amount/2);
            }

            temporalList.add(MIXListClone.get(i));
            temporalList.add(MIXListClone.get(i));
        }

        MIXListClone = temporalList;

        if(MIXListClone.get(0).getManufacture_item().equals("YBC01") || MIXListClone.get(0).getManufacture_item().equals("HMN01")){

        }else {

        }




        return FIList = MIXListClone;
    }
}
