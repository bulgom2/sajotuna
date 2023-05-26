package com.mes.sajotuna.gongjung;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatusTest {
    private static InspectionProcess inspectionProcess;
    private CoolingProcess coolingProcess;
    private PackagingProcess packagingProcess;
//    public double packagingPercent() {
//        Duration runtime = PackagingRuntime(); // 포장 공정의 가동 시간을 가져옴
//        double packagingCnt = 200.0; // 시간당 포장 가능한 제품 수(박스)
//        int ptTime = 20; // 준비 시간(분)
//
//        if (runtime.getSeconds() > 0) {
//            long runtimeMinutes = runtime.toMinutes(); // 가동 시간을 분으로 변환
//
//            // 포장 클래스에서 제품명과 수량을 가져와서 확인
//            String productName = PackagingProcess.getProductName();
//            Long qtt = PackagingProcess.getQtt();
//
//            // 제품 종류에 따라 박스 개수 계산
//            Long boxes;
//
//            if (productName.contains("즙")) {
//                boxes = qtt / 30; // 즙은 박스당 30개
//            } else {
//                boxes = qtt / 25; // 젤리는 박스당 25개
//            }
//
//            // 포장 소요 시간 계산
//            int packagingTime = (int)Math.ceil((boxes / packagingCnt) * 60);    // 박스 수에 따른 총 포장 시간
//            float completePackagingCnt = ((float) packagingCnt * (float) runtimeMinutes); // 현재 완료된 제품 수량 계산
//            double completePercent = (completePackagingCnt / (double) boxes) * 100.0; // 포장 완료 수량의 퍼센트 계산
//            return completePercent;
//        } else {
//            return 0; // 포장 공정이 진행되지 않았을 경우 완료된 제품 수량은 0
//        }
//    }
//
//    public Duration PackagingRuntime() {
//        if (packagingProcess != null && packagingProcess.getDate() != null) {
//            LocalDateTime startTime = packagingProcess.getDate(); // 포장 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 포장 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//
//    public long PackagingWip(float completePackagingCnt) {
//        long packagingWip = (long) (packagingProcess.getQtt() - completePackagingCnt);
//        return packagingWip;
//    }
//
//    public double inspectionPercent() {
//        Duration runtime = InspectionRuntime(); // 가동 시간 계산
//        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수
//
//        if (runtime.getSeconds() > 0) {
//            long inspectionTime = runtime.toMinutes(); // 가동 시간을 분으로 변환
//            float completeInspectionCnt = (inspectionCnt / 60.0f) * inspectionTime; // 완료된 제품 수량 계산
//            long qtt = inspectionProcess.getQtt(); // 총 수량 가져오기
//            double inspectionPercent;
//
//            if (qtt > 0) {
//                inspectionPercent = Math.round(((completeInspectionCnt / qtt) * 100.0)); // 검사 완료 수량의 퍼센트 계산
//            } else {
//                inspectionPercent = 0.0; // 총 수량이 0인 경우 완료된 제품 수량의 퍼센트는 0
//            }
//
//            return inspectionPercent;
//        } else {
//            return 0.0; // 검사 공정이 진행되지 않았을 경우 완료된 제품 수량의 퍼센트는 0
//        }
//    }
//
//    public static Duration InspectionRuntime() {
//        if (inspectionProcess != null && inspectionProcess.getDate() != null) {
//            LocalDateTime startTime = inspectionProcess.getDate(); // 검사 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 검사 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//
//    public Long InspectionWip(float completeInspectionCnt) {
//        Long inspectionWip = (long) (inspectionProcess.getQtt() - completeInspectionCnt);
//        return inspectionWip;
//    }

//    public double coolingPercent() {
//        Duration runtime = CoolingRuntime(); // 냉각 공정의 가동 시간을 가져옴
//        Long qtt = coolingProcess.getQtt(); // 냉각 공정의 제품 수량
//
//        if (runtime.getSeconds() > 0) {
//            long runtimeMinutes = runtime.toMinutes(); // 가동 시간(분)
//            long coolingTime = 24 * 60; // 총 소요 시간(24시간)
//
//            if (runtimeMinutes >= coolingTime) { // 현재 가동 시간이 총 소요 시간을 초과하면 완료
//                return 100.0;
//            } else {
//                float completeCoolingCnt = ((float) runtimeMinutes / (float) coolingTime) * qtt;// 실시간 제품 냉각 완료 수량 계산
//                double coolingPercent = Math.round((completeCoolingCnt / (double) qtt) * 100.0); // 전체 수량에 대한 퍼센트 계산
//                return coolingPercent;
//            }
//        } else {
//            return 0.0; // 냉각 공정이 진행되지 않았을 경우 완료된 제품 수량은 0
//        }
//    }
//
//    public Duration CoolingRuntime() { // 냉각은 예상 시간 없음(그냥 종료 시간을 다음날 아침 9시 전?)
//        if (coolingProcess != null && coolingProcess.getDate() != null) {
//            LocalDateTime startTime = coolingProcess.getDate(); // 냉각 공정 시작 시간
//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
//            return Duration.between(startTime, currentTime);
//        } else {
//            return Duration.ZERO; // 냉각 공정이 진행되지 않았을 경우 가동 시간은 0
//        }
//    }
//
//    public Long CoolingWip(float completeCoolingCnt) { // 냉각 공정은 일단 보류
//        Long coolingWip = (long) (coolingProcess.getQtt() - completeCoolingCnt);
//        return coolingWip;
//    }

    public static void main(String[] args) {
        StatusTest statusTest = new StatusTest();
//// 포장 공정 설정
//        PackagingProcess packagingProcess = new PackagingProcess();
//        packagingProcess.setQtt(30000L); // 총 수량 설정
//        packagingProcess.setProductName("양배추즙");
//        LocalDateTime startTime = LocalDateTime.now().minusHours(2); // 2시간 전(120분전)
//        double packagingCnt = 200.0; // 시간당 포장 가능한 제품 수(박스)
//        packagingProcess.setDate(startTime); // 시작 시간 설정
//        statusTest.packagingProcess = packagingProcess;
//
//// 시간이 지남으로써 포장 완료된 제품 수량 증가
//        int processMinutes = (int) Duration.between(startTime, LocalDateTime.now()).toMinutes(); // 현재 시간과 시작 시간의 차이를 분으로 계산
//
//// 총 포장 공정 가동시간을 계산
//        long qtt = packagingProcess.getQtt();
//
//        // 제품 종류에 따라 박스 개수 계산
//        Long boxes;
//
//        String productName = PackagingProcess.getProductName();
//
//        if (productName.contains("즙")) {
//
//            boxes = qtt / 30; // 즙은 박스당 30개
//        } else {
//            boxes = qtt / 25; // 젤리는 박스당 25개
//        }
//
//        long runtimeMinutes = processMinutes; // 가동 시간(분)
//        int packagingTime = (int) Math.ceil((boxes / packagingCnt) * 60);    // 박스 수에 따른 총 포장 시간
//        float completePackagingCnt = ((float) (packagingCnt / 60) * (float) runtimeMinutes); // 현재 완료된 제품 수량 계산
//        double completePercent = (completePackagingCnt / (double) boxes) * 100.0; // 포장 완료 수량의 퍼센트 계산
//
//        qtt = boxes;
//
//        System.out.println("포장 완료 수량: " + completePackagingCnt + "개");
//        System.out.println("포장 완료 퍼센트: " + completePercent + "%");
//        System.out.println("가동 시간: " + runtimeMinutes + "분");
//        System.out.println("재공 재고 수량: " + (packagingProcess.getQtt() - completePackagingCnt) + "개");
//        System.out.println("총 소요 시간: " + packagingTime + "분");
//
//        // 검사 공정 설정
//        InspectionProcess inspectionProcess = new InspectionProcess();
//        inspectionProcess.setQtt(15000L); // 총 수량 설정
//        LocalDateTime startTime = LocalDateTime.now().minusHours(2); // 2시간 전(120분전)
//        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수
//        inspectionProcess.setDate(startTime); // 시작 시간 설정
//        statusTest.inspectionProcess = inspectionProcess;
//
//// 시간이 지남으로써 포장 완료된 제품 수량 증가
//        int InspectionRuntime = (int) Duration.between(startTime, LocalDateTime.now()).toMinutes(); // 현재 시간과 시작 시간의 차이를 분으로 계산
//
//        long inspectionRuntime = InspectionRuntime; // 가동 시간을 분으로 변환
//        float completeInspectionCnt = (inspectionCnt / 60.0f) * inspectionRuntime; // 완료된 제품 수량 계산
//        long qtt = inspectionProcess.getQtt(); // 총 수량 가져오기
//        long inspectionTime = qtt / inspectionCnt * 60; // 총 소요 시간
//        double inspectionPercent;
//        inspectionPercent = Math.round(((completeInspectionCnt / qtt) * 100.0)); // 검사 완료 수량의 퍼센트 계산
//
//        System.out.println("검사 완료 수량: " + completeInspectionCnt + "개");
//        System.out.println("검사 완료 퍼센트: " + inspectionPercent + "%");
//        System.out.println("가동 시간: " + inspectionRuntime + "분");
//        System.out.println("재공 재고 수량: " + (inspectionProcess.getQtt() - completeInspectionCnt) + "개");
//        System.out.println("총 소요 시간: " + inspectionTime + "분");

//        CoolingProcess coolingProcess = new CoolingProcess();
//        coolingProcess.setQtt(15000L); // 총 수량 설정
//        LocalDateTime startTime = LocalDateTime.now().minusHours(2); // 2시간 전(120분전)
//        coolingProcess.setDate(startTime); // 시작 시간 설정
//        statusTest.coolingProcess = coolingProcess;
//
//
//        // 시간이 지남으로써 검사 완료된 제품 수량 증가
//        int processMinutes = (int) Duration.between(startTime, LocalDateTime.now()).toMinutes(); // 현재 시간과 시작 시간의 차이를 분으로 계산
//
//
//        // 총 검사 공정 가동시간을 계산
//        Long qtt = coolingProcess.getQtt();
//        long runtimeMinutes = processMinutes; // 가동 시간(분)
//        long coolingTime = 24 * 60; // 총 소요 시간(24시간)
//        float completeCoolingCnt = ((float) runtimeMinutes / (float) coolingTime) * qtt;// 실시간 제품 냉각 완료 수량 계산
//        double completePercent = Math.round((completeCoolingCnt / (double) qtt) * 100.0); // 전체 수량에 대한 퍼센트 계산
//
//        System.out.println("냉각 완료 수량: " + completeCoolingCnt + "개");
//        System.out.println("냉각 완료 퍼센트: " + completePercent + "%");
//        System.out.println("가동 시간: " + runtimeMinutes + "분");
//        System.out.println("재공 재고 수량: " + (coolingProcess.getQtt() - completeCoolingCnt) + "개");
//        System.out.println("총 소요 시간: " + coolingTime + "분");
    }
}