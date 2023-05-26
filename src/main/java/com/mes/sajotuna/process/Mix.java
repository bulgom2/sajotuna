package com.mes.sajotuna.process;


import com.mes.sajotuna.dto.ManufactureDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Mix {

    public List<ManufactureDTO> mix(List<ManufactureDTO> ccList , ManufactureDTO getMIX , ManufactureDTO resultMS){
        List<ManufactureDTO> MIXList = new ArrayList<>();

        if(resultMS.getManufacture_item() == "YBC02" || resultMS.getManufacture_item() == "HMN02"){

            for(int i = 0; i < ccList.size(); i++){

                LocalDateTime outtime = ccList.get(i).getManufacture_outtime();
                System.out.println(i+"outtime : "+outtime);


                ccList.get(i).setManufacture_intime(outtime);
                ccList.get(i).setManufacture_outtime(outtime.plusSeconds((long) (ccList.get(i).getManufacture_qtt()*172.8)));
                ccList.get(i).setProcess_id("MIX");

                MIXList.add(ccList.get(i));
                System.out.println(i+"번째 : "+MIXList);
            }



        }else if(resultMS.getManufacture_item() == "YBC02" || resultMS.getManufacture_item() == "HMN02"){

        }


        MIXList = ccList;


        return MIXList;
    }
}
