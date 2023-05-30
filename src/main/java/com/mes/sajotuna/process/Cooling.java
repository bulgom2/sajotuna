package com.mes.sajotuna.process;

import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cooling {

    List<ManufactureDTO> coList = new ArrayList<>();
    public List<ManufactureDTO> cooling(List<ManufactureDTO> isList){

        List<ManufactureDTO> isListClone = new ArrayList<>();
        for(int i = 0; i < isList.size(); i++){
            isListClone.add(new ManufactureDTO(isList.get(i)));
        }

        for(int i = 0; i < isListClone.size(); i++){
            coList.add(setTime(isListClone.get(i)));
        }


        return coList;
    }

    public ManufactureDTO setTime(ManufactureDTO manufactureDTO){

        LocalDateTime workTime = manufactureDTO.getManufacture_outTime();
        manufactureDTO.setManufacture_inTime(workTime);
        manufactureDTO.setManufacture_outTime(workTime.plusDays(1).withHour(9).withMinute(0).withSecond(0));
        manufactureDTO.setProcess_id("CO");
        manufactureDTO.setFacility_id(null);
        manufactureDTO.setBeforeLot(manufactureDTO.getThisLot());
        manufactureDTO.setThisLot("CO-"+manufactureDTO.getManufacture_inTime());

        return manufactureDTO;
    }
}
