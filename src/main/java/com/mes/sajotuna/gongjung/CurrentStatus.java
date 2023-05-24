package com.mes.sajotuna.gongjung;

import java.time.Duration;
import java.time.LocalDateTime;

public class CurrentStatus {

    private InspectionProcess inspectionProcess;
    private CoolingProcess coolingProcess;
    private PackagingProcess packagingProcess;
    private ShipmentProcess shipmentProcess;

    public double completeInspectionCnt;    // 실시간 검사 완료 수량
    public double completeCoolingCnt;      // 실시간 냉각 완료 수량
    public double completePackagingCnt;     // 실시간 포장 완료 수량
    public double completeShipmentCnt;



    // 실시간 공정별 현황(각 공정별 완료된 제품수량을 통해 실시간으로 퍼센트)
    public double inspectionPercent() {
        Duration runtime = InspectionRuntime(); // 가동 시간 계산
        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수
        int ptTime = 10;    // 시간당 준비 시간

        if (runtime.getSeconds() > 0) {
            long minutes = runtime.toMinutes(); // 가동 시간을 분으로 변환
            int processTime = (int) (minutes - (minutes / 60) * ptTime);   // 가동 시간에 준비 시간 차감
            int completeCnt = (int) ((inspectionCnt / 60.0) * processTime); // 완료된 제품 수량 계산
            Long qtt = InspectionProcess.getQtt(); // 총 수량 가져오기
            double completePercent;

            if (qtt > 0) {
                completePercent = (completeCnt / (double) qtt) * 100.0; // 검사 완료 수량의 퍼센트 계산
            } else {
                completePercent = 0.0; // 총 수량이 0인 경우 완료된 제품 수량의 퍼센트는 0
            }

            completeInspectionCnt = completeCnt;
            return completePercent;
        } else {
            return 0.0; // 검사 공정이 진행되지 않았을 경우 완료된 제품 수량의 퍼센트는 0
        }
    }


//    public int CoolingPercent() { // 구하는 것 가능?
//        Duration runtime = CoolingRuntime(); // 검사 공정의 가동 시간을 가져옴
////        int CoolingCnt = qtt; 냉각은 포장에서 받아온 수 그대로. 시간에 대한 게 없음.
//
//        if (runtime.getSeconds() > 0) {
//            long minutes = runtime.toMinutes(); // 가동 시간을 분으로 변환
//            int completedCnt = (int) (CoolingCnt * minutes); // 완료된 제품 수량 계산
//            return completedCnt;
//        } else {
//            return 0; // 검사 공정이 진행되지 않았을 경우 완료된 제품 수량은 0
//        }
//    }

    public double packagingPercent() {
        Duration runtime = PackagingRuntime(); // 포장 공정의 가동 시간을 가져옴
        int packagingCnt = 200; // 시간당 포장 가능한 제품 수(박스)
        int ptTime = 20; // 준비 시간(분)

        if (runtime.getSeconds() > 0) {
            long minutes = runtime.toMinutes(); // 가동 시간을 분으로 변환

            // 포장 클래스에서 제품명과 수량을 가져와서 확인
            String productName = PackagingProcess.getProductName();
            Long qtt = PackagingProcess.getQtt();

            // 제품 종류에 따라 박스 개수 계산
            Long boxes;

            if (productName.equals("즙")) {
                boxes = qtt / 30; // 즙은 박스당 30개
            } else {
                boxes = qtt / 25; // 젤리는 박스당 25개
            }

            // 포장 소요 시간 계산
            Long packagingTime = (boxes / packagingCnt) * 60; // 시작 후 경과한 시간(분)
            Long addPtTime = (packagingTime / 60) * ptTime; // 시작 후 1시간마다 준비 시간 추가
            Long leadTime = packagingTime + addPtTime; // 총 포장 소요 시간

            int completedCnt = (int) (packagingCnt * minutes); // 완료된 제품 수량 계산
            double completePercent = (completedCnt / (double) boxes) * 100.0; // 포장 완료 수량의 퍼센트 계산
            completePackagingCnt = completedCnt;
            return completePercent;
        } else {
            return 0; // 포장 공정이 진행되지 않았을 경우 완료된 제품 수량은 0
        }
    }

    // 현재 공정별 가동 시간
    public Duration InspectionRuntime() {
        if (inspectionProcess != null && inspectionProcess.getDate() != null) {
            LocalDateTime startTime = inspectionProcess.getDate(); // 검사 공정 시작 시간
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            return Duration.between(startTime, currentTime);
        } else {
            return Duration.ZERO; // 검사 공정이 진행되지 않았을 경우 가동 시간은 0
        }
    }

    public Duration CoolingRuntime() { // 냉각은 예상 시간 없음(그냥 종료 시간을 다음날 아침 9시 전?)
        if (coolingProcess != null && coolingProcess.getDate() != null) {
            LocalDateTime startTime = coolingProcess.getDate(); // 냉각 공정 시작 시간
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            return Duration.between(startTime, currentTime);
        } else {
            return Duration.ZERO; // 냉각 공정이 진행되지 않았을 경우 가동 시간은 0
        }
    }

    public Duration PackagingRuntime() {
        if (packagingProcess != null && packagingProcess.getDate() != null) {
            LocalDateTime startTime = packagingProcess.getDate(); // 포장 공정 시작 시간
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            return Duration.between(startTime, currentTime);
        } else {
            return Duration.ZERO; // 포장 공정이 진행되지 않았을 경우 가동 시간은 0
        }
    }

//    // 실시간 제품 현황(현재 재고에 있는 완제품 수량), 재고 테이블에서 값 가져오기
//    public int Jelly1Cnt() {}   // 석류 젤리 재고수량
//    public int Jelly2Cnt() {}   // 매실 젤리 재고수량
//    public int Juice1Cnt() {}   // 양배추즙 재고수량
//    public int Juice2Cnt() {}   // 흑마늘즙 재고수량

    // 공정별 재공 재고 현황(미투입 원재료 수량) // 전체 수량(qtt)에서 실시간 공정별 현황의 수량(qtt)를 뺀 값
    public Long InspectionWip() {
        Long inspectionWip = (long) (inspectionProcess.getQtt() - completeInspectionCnt);
        return inspectionWip;
    }

//    public Long CoolingWip() { // 냉각 공정은 일단 보류
//        Long coolingWip = (long) (coolingProcess.getQtt() - completeCoolingCnt);
//        return coolingWip;
//    }

    public Long PackagingWip() {
        Long packagingWip = (long) (packagingProcess.getQtt() - completePackagingCnt);
        return packagingWip;
    }

}
