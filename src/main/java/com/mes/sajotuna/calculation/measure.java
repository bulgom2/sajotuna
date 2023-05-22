package com.mes.sajotuna.calculation;

import com.example.domain.ManufactureDTO;
import com.example.domain.OrdersDTO;
import com.example.process.Measurement;
import com.example.process.PreProcessing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class measure {

    public static void main(String[] args) {

        // 년 월 일 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초");
        // 공정 계산 할 때 시간
        LocalDateTime workTime;

        //<수주 등록>
        OrdersDTO ordersDTO = new OrdersDTO();
        ordersDTO.setOrders_item("ybc");
        ordersDTO.setOrders_qtt(100);
        ordersDTO.setOrders_date(LocalDateTime.now());

        String now = LocalDateTime.now().format(formatter);

        System.out.println("=== 수주 등록 ==================================================================================================");
        System.out.println("주문 상품 : "+ordersDTO.getOrders_item());
        System.out.println("주문 수량 : "+ ordersDTO.getOrders_qtt());
        System.out.println("수주 등록 시간 : "+ now);
        System.out.println(" ");



        ///////////////////////////////////// 원료 계산 ////////////////////////////////////////
        //<원료 계량 생산계획 정보 가져오기>
        ManufactureDTO getMS = new ManufactureDTO();
        getMS.setManufacture_outtime(LocalDateTime.of(2023, 05,19,12,50));             //원료 계획 최종 시간
        LocalDateTime shipmentTime = LocalDateTime.of(2023,05,22,10,00,0);      //발주 입고 시간

        //<원료 계량 시간 계산>
        Measurement measurement = new Measurement();
        ManufactureDTO resultMs = measurement.measurementByMaterial(getMS, shipmentTime);
        resultMs.setManufacture_qtt(50);

        String start = resultMs.getManufacture_intime().format(formatter);
        String end = resultMs.getManufacture_outtime().format(formatter);

        System.out.println("=== 원료 계량 ==================================================================================================");
        System.out.println(resultMs);
        System.out.println("원료 계량 시작 시간 : "+ start);
        System.out.println("원료 계량 종료 시간 : "+ end);
        System.out.println("원료 계량 결과 량 : "+resultMs.getManufacture_qtt()+"kg");
        System.out.println(" ");

        workTime = resultMs.getManufacture_outtime();
        ///////////////////////////////////// 전처리 /////////////////////////////////////////

        // 원료 계량 양
        int amount = resultMs.getManufacture_qtt();
        // 최대 생산 가능 양
        int availableMax = 1000;
        // 작업 횟수 계산
        int numOfWorks = (int)Math.ceil((double) amount/availableMax);

        List<ManufactureDTO> ppList = new ArrayList<>();

        ManufactureDTO getPP = new ManufactureDTO();

        // 전처리 계획 최종 시간
        getPP.setManufacture_outtime(LocalDateTime.of(2023,05,22,14,30,0));
        ppList.add(getPP);


        LocalDateTime referenceTime = getPP.getManufacture_outtime();    // 전처리 계획 최종 시간

        PreProcessing preProcessing = new PreProcessing();

        for(int i = 1; i <= numOfWorks; i++){
            int amountPerWork = Math.min(amount, availableMax); // 각 작업당 처리할 양
            ManufactureDTO amountWork = new ManufactureDTO();
            amountWork.setManufacture_qtt(amountPerWork);
            ppList.add(amountWork);
            amount -= amountPerWork;
        }


        for(int i = 1; i < ppList.size() ; i++){
            ppList.set(i, preProcessing.preProcessing(ppList.get(i), referenceTime, workTime));
            System.out.println("List "+i+"번째 :"+ppList.get(i));
            referenceTime = ppList.get(i).getManufacture_outtime();
            workTime = ppList.get(i).getManufacture_outtime();
        }





    }

}
