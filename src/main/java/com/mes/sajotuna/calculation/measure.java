package com.mes.sajotuna.calculation;


import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.process.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class measure {

    public static void main(String[] args) {

        ManufactureDTO resultMS;
        List<ManufactureDTO> ppList = new ArrayList<>();
        List<ManufactureDTO> ccList = new ArrayList<>();
        List<ManufactureDTO> mxList;
        List<ManufactureDTO> fiList;
        List<ManufactureDTO> isList;
        List<ManufactureDTO> coList = new ArrayList<>();
        List<ManufactureDTO> pkList = new ArrayList<>();


        // 원료 계량 최종 시간

        ManufactureDTO getMS = new ManufactureDTO();    //db에서 원료 계량 정보 가져온 값
        //getMS.setManufacture_outTime(LocalDateTime.of(2023, 05, 19, 12, 50));             //원료 계획 최종 시간

        // 전처리 계획 최종 시간
        ManufactureDTO getPP = new ManufactureDTO();    //db에서 전처리 정보 가져온 값
        //getPP.setManufacture_outTime(LocalDateTime.of(2023, 05, 19, 14, 30, 0));

        // 추출기 최종 작업 시간
        LocalDateTime EA1 = LocalDateTime.of(2023, 05, 20, 13, 30, 0);             //설비1 마지막 공정 시간
        LocalDateTime EA2 = LocalDateTime.of(2023, 05, 20, 15, 30, 0);
        // 혼합 계획 최종 시간
        LocalDateTime MX1 = LocalDateTime.of(2023, 05, 24, 15, 30, 0);             //설비1 마지막 공정 시간
        LocalDateTime MX2 = LocalDateTime.of(2023, 05, 24, 15, 30, 0);
        // 혼합 계획 최종 시간
        LocalDateTime FI1 = LocalDateTime.of(2023, 05, 24, 13, 30, 0);             //설비1 마지막 공정 시간
        LocalDateTime FI2 = LocalDateTime.of(2023, 05, 22, 11, 30, 0);

        ManufactureDTO getIS = new ManufactureDTO();
        getIS.setManufacture_outTime(LocalDateTime.of(2023, 05, 19, 14, 30, 0));

        ManufactureDTO getPK = new ManufactureDTO();
        getPK.setManufacture_outTime(LocalDateTime.of(2023, 05, 25, 14, 30, 0));

        // 공정 process 객체
        Measurement measurement = new Measurement();
        PreProcessing preProcessing = new PreProcessing();
        Extraction extraction = new Extraction();
        Mix mix = new Mix();
        Fill fill = new Fill();
        Inspection inspection = new Inspection();
        Cooling cooling = new Cooling();
        Packaging packaging = new Packaging();

        // 년 월 일 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");

        //<수주 등록>
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setItem("ybc");
        ordersDTO.setQtt(1000);
        ordersDTO.setDate(LocalDateTime.now());

        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//
        String now = LocalDateTime.now().format(formatter);
        System.out.println("=== 수주 등록 ==================================================================================================");
        System.out.println("주문 상품 : " + ordersDTO.getItem());
        System.out.println("주문 수량 : " + ordersDTO.getQtt());
        System.out.println("수주 등록 시간 : " + now);
        System.out.println(" ");
        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//

        //(가상 발주 DTO)
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setOrdersNo("sj_2023_05_21");
        purchaseDTO.setItem("YBC02");
        purchaseDTO.setQtt(5000L);
        purchaseDTO.setShipDate(LocalDateTime.of(2023, 05, 22, 10, 00, 0));


        ///////////////////////////////////// 원료 계산 ////////////////////////////////////////

        //<원료 계량 시간 계산>
        resultMS = measurement.measurementByMaterial(getMS, purchaseDTO);

        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//
        String start = resultMS.getManufacture_inTime().format(formatter);
        String end = resultMS.getManufacture_outTime().format(formatter);

        System.out.println("=== 원료 계량 ==================================================================================================");
        System.out.println(resultMS);
        System.out.println("원료 계량 시작 시간 : " + start);
        System.out.println("원료 계량 종료 시간 : " + end);
        System.out.println("원료 계량 결과 량 : " + resultMS.getManufacture_qtt() + "kg");
        System.out.println(" ");
        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//


        if (purchaseDTO.getItem().equals("YBC02") || purchaseDTO.getItem().equals("HMN02")) {


            ///////////////////////////////////// 전처리 /////////////////////////////////////////
            System.out.println("=== 전처리 ==================================================================================================");


            ppList = preProcessing.preProcessing(getPP, resultMS);

            //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//

            for (int i = 0; i < ppList.size(); i++) {
                System.out.println((i + 1) + "번째 전처리 시작 시간 : " + ppList.get(i).getManufacture_inTime().format(formatter) + " 작업량 : " + ppList.get(i).getManufacture_qtt() + "kg");
                System.out.println((i + 1) + "번째 전처리 종료 시간 : " + ppList.get(i).getManufacture_outTime().format(formatter));
            }
            System.out.println(" ");
            //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//


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

        System.out.println("=== 혼합 공정 ==================================================================================================");
        for (int i = 0; i < mxList.size(); i++) {

            System.out.println((i + 1) + "번째 혼합 시작 시간 : " + mxList.get(i).getManufacture_inTime().format(formatter));
            System.out.println((i + 1) + "번째 혼합 종료 시간 : " + mxList.get(i).getManufacture_outTime().format(formatter));
        }
        System.out.println(" ");

        /////////////////////// 충진 공정 (FI == 충진)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        fiList = fill.fill(mxList, FI1, FI2);

        System.out.println("=== 충진 공정 ==================================================================================================");
        for (int i = 0; i < fiList.size(); i++) {

            System.out.println((i + 1) + "번째 충진 시작 시간 : " + fiList.get(i).getManufacture_inTime().format(formatter) + "   " + fiList.get(i).getManufacture_qtt());
            System.out.println((i + 1) + "번째 충진 종료 시간 : " + fiList.get(i).getManufacture_outTime().format(formatter));
        }
        System.out.println("");


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

        /////////////////////// 최종 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("---최종---"+resultMS);
        System.out.println("---최종---"+ppList);
        System.out.println("---최종---"+ccList);
        System.out.println("---최종---"+mxList);
        System.out.println("---최종---"+fiList);
        System.out.println("---최종---"+isList);
        System.out.println("---최종---"+coList);
        System.out.println("---최종---"+pkList);
        System.out.println();
        for(int i = 0; i < 1; i++){
            System.out.println("resultMS ("+(i+1)+") 번째 : "+resultMS);
        }
        System.out.println();
        for(int i = 0; i < ppList.size(); i++){
            System.out.println("ppList ("+(i+1)+") 번째 : "+ppList.get(i));
        }
        System.out.println();
        for(int i = 0; i < ccList.size(); i++){
            System.out.println("ccList ("+(i+1)+") 번째 : "+ccList.get(i));
        }
        System.out.println();
        for(int i = 0; i < mxList.size(); i++){
            System.out.println("mxList ("+(i+1)+") 번째 : "+mxList.get(i));
        }
        System.out.println();
        for(int i = 0; i < fiList.size(); i++){
            System.out.println("fiList ("+(i+1)+") 번째 : "+fiList.get(i));
        }
        System.out.println();
        for(int i = 0; i < isList.size(); i++){
            System.out.println("isList ("+(i+1)+") 번째 : "+isList.get(i));
        }
        System.out.println();
        for(int i = 0; i < coList.size(); i++){
            System.out.println("coList ("+(i+1)+") 번째 : "+coList.get(i));
        }
        System.out.println();
        for(int i = 0; i < pkList.size(); i++){
            System.out.println("pkList ("+(i+1)+") 번째 : "+pkList.get(i));
        }
        System.out.println();
    }

}
