package com.mes.sajotuna.gongjung;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CoolingProcessTest {

        public static void main(String[] args) {

            InspectionProcess inspectionProcess = new InspectionProcess();
            inspectionProcess.startInspection(LocalDateTime.now(), 10000L);

            System.out.println(inspectionProcess.getQtt());
            System.out.println("검사 끝나는 시간 : " + inspectionProcess.getDate());

            System.out.println();

            CoolingProcess coolingProcess = new CoolingProcess();
            coolingProcess.startCooling("LOT123",inspectionProcess.getQtt(), "양배추즙", inspectionProcess.getDate(), "ORDER456", "Company XYZ");

            System.out.println(coolingProcess.getNo());
            System.out.println(coolingProcess.getQtt());
            System.out.println(coolingProcess.getDate());
            System.out.println(coolingProcess.getProductName());
            System.out.println(coolingProcess.getOrdersId());
            System.out.println(coolingProcess.getCompany());

            System.out.println();

            PackagingProcess packagingProcess = new PackagingProcess();
            packagingProcess.startPackaging(coolingProcess.getNo(), coolingProcess.getQtt(), coolingProcess.getProductName(), coolingProcess.getDate(), coolingProcess.getOrdersId(), coolingProcess.getCompany());

            System.out.println("2");
            System.out.println(packagingProcess.getNo());
            System.out.println(packagingProcess.getQtt());
            System.out.println(packagingProcess.getDate());
            System.out.println(packagingProcess.getProductName());
            System.out.println(packagingProcess.getOrdersId());
            System.out.println(packagingProcess.getCompany());

            System.out.println();

            ShipmentProcess shipmentProcess = new ShipmentProcess();
            shipmentProcess.startShipment(packagingProcess.getNo(), packagingProcess.getProductName(), packagingProcess.getDate(), packagingProcess.getOrdersId(), packagingProcess.getCompany(), 255L);

            System.out.println("3");
            System.out.println(shipmentProcess.getNo());
            System.out.println(shipmentProcess.getDate());
            System.out.println(shipmentProcess.getProductName());
            System.out.println(shipmentProcess.getOrdersId());
            System.out.println(shipmentProcess.getCompany());
            System.out.println(shipmentProcess.getBoxes());
        }
}


