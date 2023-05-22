//package com.mes.sajotuna.gongjung;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//public class CurrentStatus {
//
//    private InspectionProcess inspectionProcess;
//    private CoolingProcess coolingProcess;
//    private PackagingProcess packagingProcess;
//    private ShipmentProcess shipmentProcess;
//
//    // 공정별 현황(완성된 제품수량을 통해 실시간 퍼센트)
//    public void updateInspectionStatus(InspectionProcess inspectionProcess) {
//        this.inspectionProcess = inspectionProcess;
//        System.out.println("검사 공정 : " + inspectionProcess.getStatus()) + " %";
//    }
//
//    public void updateCoolingStatus(CoolingProcess coolingProcess) {
//        this.coolingProcess = coolingProcess;
//       System.out.println("냉각 공정 : " + coolingProcess.getStatus()) + " %";
//    }
//
//    public void updatePackagingStatus(PackagingProcess packagingProcess) {
//        this.packagingProcess = packagingProcess;
//        System.out.println("포장 공정 : " + packagingProcess.getStatus()) + " %";
//    }
//
//    public void updateShipmentStatus(ShipmentProcess shipmentProcess) {
//        this.shipmentProcess = shipmentProcess;
//        System.out.println("출하 공정: " + shipmentProcess.getStatus()) + " %";
//    }
//
//    // 공정별 가동 시간
//    public Duration calculateInspectionRuntime() {
//        if (inspectionProcess != null && inspectionProcess.getDate() != null) {
//            LocalDateTime startTime = inspectionProcess.getDate(); // 검사 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 검사 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//    public Duration calculateCoolingRuntime() { // 정한 시간 없음(그냥 종료시간을 다음날 아침 9시 전?)
//        if (coolingProcess != null && coolingProcess.getDate() != null) {
//            LocalDateTime startTime = coolingProcess.getDate(); // 냉각 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 냉각 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//    public Duration calculatePackagingRuntime() {
//        if (packagingProcess != null && packagingProcess.getDate() != null) {
//            LocalDateTime startTime = packagingProcess.getDate(); // 포장 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 포장 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//
//    // 실시간 제품 현황(완제품 수량만 나타낼지? 각 공정에서 완료된 제품의 수인지?)
//    public int getCurrentInspectionProductCount() {}
//    public int getCurrentCoolingProductCount() {}
//    public int getCurrentPackagingProductCount() {}
//    public int getCurrentShipmentProductCount() {}
//
//    // 공정별 재공 재고 현황(미투입 원재료 수량)
//    public int getInspectionWIPInventory() {}
//    public int getCoolingWIPInventory() {}
//    public int getPackagingWIPInventory() {}
//    public int getShipmentWIPInventory() {}
//
//}
