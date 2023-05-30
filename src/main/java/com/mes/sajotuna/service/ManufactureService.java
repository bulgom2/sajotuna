package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.process.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ManufactureService {


    public ManufactureDTO ExpectedDate(PurchaseDTO purchaseDTO){

        ManufactureDTO resultMS;
        List<ManufactureDTO> ppList = new ArrayList<>();
        List<ManufactureDTO> ccList = new ArrayList<>();
        List<ManufactureDTO> mxList;
        List<ManufactureDTO> fiList;
        List<ManufactureDTO> isList;
        List<ManufactureDTO> coList = new ArrayList<>();
        List<ManufactureDTO> pkList = new ArrayList<>();

        Measurement measurement = new Measurement();
        PreProcessing preProcessing = new PreProcessing();
        Extraction extraction = new Extraction();
        Mix mix = new Mix();
        Fill fill = new Fill();
        Inspection inspection = new Inspection();
        Cooling cooling = new Cooling();
        Packaging packaging = new Packaging();



    }

}
