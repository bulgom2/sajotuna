package com.mes.sajotuna.gongjung;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class CurrentStatus {

    private InspectionProcess inspectionProcess;
    private CoolingProcess coolingProcess;
    private PackagingProcess packagingProcess;

    public double completeInspectionCnt;    // 실시간 검사 완료 수량
    public double completeCoolingCnt;      // 실시간 냉각 완료 수량
    public double completePackagingCnt;     // 실시간 포장 완료 수량




    // 실시간 공정별 현황(각 공정별 완료된 제품수량을 통해 실시간으로 퍼센트)
    public double inspectionPercent() {
        Duration runtime = InspectionRuntime(); // 가동 시간 계산
        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수

        if (runtime.getSeconds() > 0) {
            long inspectionRuntime = runtime.toMinutes(); // 가동 시간(분)
            float completeInspectionCnt = (inspectionCnt / 60.0f) * inspectionRuntime; // 실시간 완료된 제품 수량 계산
            long qtt = inspectionProcess.getQtt(); // 총 수량 가져오기
            long inspectionTime = qtt / inspectionCnt * 60; // 총 소요 시간
            double inspectionPercent;

            if (qtt > 0) {
                inspectionPercent = Math.round(((completeInspectionCnt / qtt) * 100.0)); // 검사 완료 수량의 퍼센트 계산
            } else {
                inspectionPercent = 0.0; // 총 수량이 0인 경우 완료된 제품 수량의 퍼센트는 0
            }

            return inspectionPercent;
        } else {
            return 0.0; // 검사 공정이 진행되지 않았을 경우 완료된 제품 수량의 퍼센트는 0
        }
    }

    public double coolingPercent() {
        Duration runtime = CoolingRuntime(); // 냉각 공정의 가동 시간을 가져옴
        long qtt = coolingProcess.getQtt(); // 냉각 공정의 제품 수량

        if (runtime.getSeconds() > 0) {
            long runtimeMinutes = runtime.toMinutes(); // 가동 시간(분)
            long coolingTime = 24 * 60; // 총 소요 시간(24시간)

            if (runtimeMinutes >= coolingTime) { // 현재 가동 시간이 총 소요 시간을 초과하면 완료
                return 100.0;
            } else {
                float completeCoolingCnt = ((float) runtimeMinutes / (float) coolingTime) * qtt;// 실시간 제품 냉각 완료 수량 계산
                double coolingPercent = Math.round((completeCoolingCnt / (double) qtt) * 100.0); // 전체 수량에 대한 퍼센트 계산
                return coolingPercent;
            }
        } else {
            return 0.0; // 냉각 공정이 진행되지 않았을 경우 완료된 제품 수량은 0
        }
    }

    public double packagingPercent() {
        Duration runtime = PackagingRuntime(); // 포장 공정의 가동 시간을 가져옴
        double packagingCnt = 200.0; // 시간당 포장 가능한 제품 수(박스)
        int ptTime = 20; // 준비 시간(분)

        if (runtime.getSeconds() > 0) {
            long runtimeMinutes = runtime.toMinutes(); // 가동 시간을 분으로 변환

            // 포장 클래스에서 제품명과 수량을 가져와서 확인
            String productName = PackagingProcess.getProductName();
            Long qtt = PackagingProcess.getQtt();

            // 제품 종류에 따라 박스 개수 계산
            Long boxes;

            if (productName.contains("즙")) {
                boxes = qtt / 30; // 즙은 박스당 30개
                qtt = boxes;    // 박스를 qtt로 바꿈
            } else {
                boxes = qtt / 25; // 젤리는 박스당 25개
                qtt = boxes;    // 박스를 qtt로 바꿈
            }

            System.out.println(qtt);
            // 포장 소요 시간 계산
            int packagingTime = (int)Math.ceil((qtt / packagingCnt) * 60);    // 박스수(qtt)에 따른 총 포장 시간
            float completePackagingCnt = ((float) packagingCnt / 60 * (float) runtimeMinutes); // 현재 완료된 제품 수량 계산
            System.out.println(completePackagingCnt);
            double packagingPercent = Math.round((completePackagingCnt / qtt) * 100.0); // 포장 완료 수량의 퍼센트 계산
            System.out.println(packagingPercent);
            return packagingPercent;
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
    public long InspectionWip(float completeInspectionCnt) {
        long inspectionWip = (long) (inspectionProcess.getQtt() - completeInspectionCnt);
        return inspectionWip;
    }

    public long CoolingWip(float completeCoolingCnt) { // 냉각 공정은 일단 보류
        long coolingWip = (long) (coolingProcess.getQtt() - completeCoolingCnt);
        return coolingWip;
    }

    public long PackagingWip(float completePackagingCnt) {
        long packagingWip = (long) (packagingProcess.getQtt() - completePackagingCnt);
        return packagingWip;
    }

    // getter 메서드 추가
    public double getCompleteInspectionCnt() {
        return completeInspectionCnt;
    }

    public double getCompleteCoolingCnt() {
        return completeCoolingCnt;
    }

    public double getCompletePackagingCnt() {
        return completePackagingCnt;
    }
}