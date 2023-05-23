package com.mes.sajotuna.calculation;

import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.process.Extraction;
import com.mes.sajotuna.process.Measurement;
import com.mes.sajotuna.process.PreProcessing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class measure {

    public static void main(String[] args) {

        // 년 월 일 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");

        // 공정 계산 할 때 시점
        LocalDateTime workTime;

        //<수주 등록>
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setItem("ybc");
        ordersDTO.setQtt(100);
        ordersDTO.setDate(LocalDateTime.now());

        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//
        String now = LocalDateTime.now().format(formatter);
        System.out.println("=== 수주 등록 ==================================================================================================");
        System.out.println("주문 상품 : "+ordersDTO.getItem());
        System.out.println("주문 수량 : "+ ordersDTO.getQtt());
        System.out.println("수주 등록 시간 : "+ now);
        System.out.println(" ");
        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//


        ///////////////////////////////////// 원료 계산 ////////////////////////////////////////
        //<원료 계량 생산계획 정보 가져오기>

        ManufactureDTO getMS = new ManufactureDTO();    //db에서 원료 계량 정보 가져온 값
        getMS.setManufacture_outtime(LocalDateTime.of(2023, 05,19,12,50));             //원료 계획 최종 시간


        //(가상 발주 DTO)
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setOrdersNo("sj_2023_05_21");
        purchaseDTO.setItem("YBC02");
        purchaseDTO.setQtt(5000L);
        purchaseDTO.setShipDate(LocalDateTime.of(2023,05,22,10,00,0));


        //<원료 계량 시간 계산>
        Measurement measurement = new Measurement();
        ManufactureDTO resultMS = measurement.measurementByMaterial(getMS, purchaseDTO);

        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//
        String start = resultMS.getManufacture_intime().format(formatter);
        String end = resultMS.getManufacture_outtime().format(formatter);

        System.out.println("=== 원료 계량 ==================================================================================================");
        System.out.println(resultMS);
        System.out.println("원료 계량 시작 시간 : "+ start);
        System.out.println("원료 계량 종료 시간 : "+ end);
        System.out.println("원료 계량 결과 량 : "+resultMS.getManufacture_qtt()+"kg");
        System.out.println(" ");
        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//


        ///////////////////////////////////// 전처리 /////////////////////////////////////////
        System.out.println("=== 전처리 ==================================================================================================");


        // 전처리 계획 최종 시간
        ManufactureDTO getPP = new ManufactureDTO();
        getPP.setManufacture_outtime(LocalDateTime.of(2023,05,22,14,30,0));


        PreProcessing preProcessing = new PreProcessing();
        List<ManufactureDTO> ppList = preProcessing.preProcessing(getPP, resultMS);

        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//
        System.out.println("ppList : "+ppList);
        for (int i = 0; i < ppList.size(); i++){
            System.out.println((i+1)+"번째 전처리 시작 시간 : "+ppList.get(i).getManufacture_intime().format(formatter)+" 작업량 : "+ppList.get(i).getManufacture_qtt()+"kg");
            System.out.println((i+1)+"번째 전처리 종료 시간 : "+ppList.get(i).getManufacture_outtime().format(formatter));
        }
        System.out.println(" ");
        //@@@@@@@@ 테스트 후 삭제 @@@@@@@@@//


        /////////////////////// 추출 공정 (EA == 추출)////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("=== 추출 공정 ==================================================================================================");

        LocalDateTime  EA1 = LocalDateTime.of(2023,05,21,10,30,0);             //설비1 마지막 공정 시간
        LocalDateTime  EA2 = LocalDateTime.of(2023,05,21,14,30,0);             //설비2 마지막 공정 시간

        Extraction extraction = new Extraction();

        List<ManufactureDTO> ccList = extraction.extraction(ppList, EA1, EA2);





    }

}