package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.entity.Manufacture;
import com.mes.sajotuna.process.*;
import com.mes.sajotuna.repository.ManufactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    public ManufactureDTO expectedDate(PurchaseDTO purchaseDTO){
        System.out.println("=============================================================================");
        System.out.println("purchaseDTO : "+purchaseDTO);

        ManufactureDTO resultMS;
        List<ManufactureDTO> ppList = new ArrayList<>();
        List<ManufactureDTO> ccList = new ArrayList<>();
        List<ManufactureDTO> mxList;
        List<ManufactureDTO> fiList;
        List<ManufactureDTO> isList;
        List<ManufactureDTO> coList = new ArrayList<>();
        List<ManufactureDTO> pkList = new ArrayList<>();


        ManufactureDTO getMS = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("MS"));
        ManufactureDTO getPP = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("PP"));
        ManufactureDTO getIS = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("IS"));
        ManufactureDTO getPK = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("PK"));

        LocalDateTime MX1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("MX01")).getManufacture_outTime();
        LocalDateTime MX2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("MX02")).getManufacture_outTime();

        LocalDateTime FI1;
        LocalDateTime FI2;

        if(purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")){
            FI1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI01")).getManufacture_outTime();
            FI2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI02")).getManufacture_outTime();
        }else {
            FI1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI03")).getManufacture_outTime();
            FI2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI04")).getManufacture_outTime();
        }

        Measurement measurement = new Measurement();
        PreProcessing preProcessing = new PreProcessing();
        Extraction extraction = new Extraction();
        Mix mix = new Mix();
        Fill fill = new Fill();
        Inspection inspection = new Inspection();
        Cooling cooling = new Cooling();
        Packaging packaging = new Packaging();

        resultMS = measurement.measurementByMaterial(getMS, purchaseDTO);
        if (purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")) {


            ///////////////////////////////////// 전처리 /////////////////////////////////////////
            System.out.println("=== 전처리 ==================================================================================================");


            ppList = preProcessing.preProcessing(getPP, resultMS);



            /////////////////////// 추출 공정 (CC == 추출)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            System.out.println("=== 추출 공정 ==================================================================================================");

            //ccList = extraction.extraction(ppList, EA1, EA2);
            ccList = extraction.extraction(ppList, MX1, MX2);

            System.out.println();

        }
        /////////////////////// 혼합 공정 (MIX == 추출)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")) {
            mxList = mix.mix(ccList);
        } else {
            mxList = mix.mix(MX1, MX2, resultMS);
        }


        /////////////////////// 충진 공정 (FI == 충진)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        fiList = fill.fill(mxList, FI1, FI2);



        /////////////////////// 검사 공정 (IS == 검사)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("=== 검사 공정 ==================================================================================================");

        isList = inspection.inspection(fiList, getIS);
        System.out.println("");


        /////////////////////// 냉각 공정 (CO == 냉각)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("=== 냉각 공정 ==================================================================================================");
        coList = cooling.cooling(isList);

        System.out.println("");

        /////////////////////// 포장 공정 (PK == 포장)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("=== 포장 공정 ==================================================================================================");
        //pkList = packaging.packaging(coList, getPK);
        pkList = packaging.packaging(coList, getPK);
        System.out.println("");

        return pkList.get(pkList.size()-1);
    }

    public void confirm(PurchaseDTO purchaseDTO){

        ManufactureDTO resultMS;
        List<ManufactureDTO> ppList = new ArrayList<>();
        List<ManufactureDTO> ccList = new ArrayList<>();
        List<ManufactureDTO> mxList;
        List<ManufactureDTO> fiList;
        List<ManufactureDTO> isList;
        List<ManufactureDTO> coList = new ArrayList<>();
        List<ManufactureDTO> pkList = new ArrayList<>();

        ManufactureDTO getMS = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("MS"));
        ManufactureDTO getPP = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("PP"));
        ManufactureDTO getIS = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("IS"));
        ManufactureDTO getPK = ManufactureDTO.of(manufactureRepository.findLatestManufactureByProcessId("PK"));

        LocalDateTime MX1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("MX01")).getManufacture_outTime();
        LocalDateTime MX2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("MX02")).getManufacture_outTime();
        LocalDateTime FI1;
        LocalDateTime FI2;
        if(purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")){
            FI1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI01")).getManufacture_outTime();
            FI2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI02")).getManufacture_outTime();
        }else {
            FI1 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI03")).getManufacture_outTime();
            FI2 = ManufactureDTO.of(manufactureRepository.findLatestManufactureByFacility_id("FI04")).getManufacture_outTime();
        }

        Measurement measurement = new Measurement();
        PreProcessing preProcessing = new PreProcessing();
        Extraction extraction = new Extraction();
        Mix mix = new Mix();
        Fill fill = new Fill();
        Inspection inspection = new Inspection();
        Cooling cooling = new Cooling();
        Packaging packaging = new Packaging();

        resultMS = measurement.measurementByMaterial(getMS, purchaseDTO);
        if (purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")) {

            ///////////////////////////////////// 전처리 /////////////////////////////////////////
            ppList = preProcessing.preProcessing(getPP, resultMS);

            /////////////////////// 추출 공정 (CC == 추출)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ccList = extraction.extraction(ppList, MX1, MX2);


        }
        /////////////////////// 혼합 공정 (MIX == 추출)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")) {
            mxList = mix.mix(ccList);
        } else {
            mxList = mix.mix(MX1, MX2, resultMS);
        }

        /////////////////////// 충진 공정 (FI == 충진)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        fiList = fill.fill(mxList, FI1, FI2);

        /////////////////////// 검사 공정 (IS == 검사)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        isList = inspection.inspection(fiList, getIS);

        /////////////////////// 냉각 공정 (CO == 냉각)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        coList = cooling.cooling(isList);

        /////////////////////// 포장 공정 (PK == 포장)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        pkList = packaging.packaging(coList, getPK);


        List<Manufacture> manufactureList = new ArrayList<>();

        if(purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")){

            for(int i = 0; i < ppList.size(); i++){
                manufactureList.add(ppList.get(i).dtoToEntity(ppList.get(i)));
            }

            for(int i = 0; i < ccList.size(); i++){
                manufactureList.add(ccList.get(i).dtoToEntity(ccList.get(i)));
            }
        }

        for(int i = 0; i < mxList.size(); i++){
            manufactureList.add(mxList.get(i).dtoToEntity(mxList.get(i)));
        }

        for(int i = 0; i < fiList.size(); i++){
            manufactureList.add(fiList.get(i).dtoToEntity(fiList.get(i)));
        }

        for(int i = 0; i < isList.size(); i++){
            manufactureList.add(isList.get(i).dtoToEntity(isList.get(i)));
        }

        for(int i = 0; i < coList.size(); i++){
            manufactureList.add(coList.get(i).dtoToEntity(coList.get(i)));
        }

        for(int i = 0; i < pkList.size(); i++){
            manufactureList.add(pkList.get(i).dtoToEntity(pkList.get(i)));
        }

        manufactureRepository.save(resultMS.dtoToEntity(resultMS));
        manufactureRepository.saveAll(manufactureList);

    }


}
