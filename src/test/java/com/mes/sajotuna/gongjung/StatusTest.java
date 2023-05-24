package com.mes.sajotuna.gongjung;

import org.springframework.boot.test.context.SpringBootTest;
import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootTest
public class StatusTest {
    private InspectionProcess inspectionProcess;
    private int completeInspectionCnt;

    public double getInspectionPercent() {
        Duration runtime = getInspectionRuntime(); // 가동 시간 계산
        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수

        if (runtime.getSeconds() > 0) {
            long minutes = runtime.toMinutes(); // 가동 시간을 분으로 변환
            int completeCnt = (int) ((inspectionCnt / 60.0) * minutes); // 완료된 제품 수량 계산
            Long qtt = inspectionProcess.getQtt(); // 총 수량 가져오기
            double completePercent = (completeCnt / (double) qtt) * 100.0; // 검사 완료 수량의 퍼센트 계산
            completeInspectionCnt = completeCnt;
            return completePercent;
        } else {
            return 0.0; // 검사 공정이 진행되지 않았을 경우 완료된 제품 수량의 퍼센트는 0
        }
    }


    public Duration getInspectionRuntime() {
        if (inspectionProcess != null && inspectionProcess.getDate() != null) {
            LocalDateTime startTime = inspectionProcess.getDate(); // 검사 공정 시작 시간
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            return Duration.between(startTime, currentTime);
        } else {
            return Duration.ZERO; // 검사 공정이 진행되지 않았을 경우 가동 시간은 0
        }
    }

    public Long getInspectionWip() {
        Long inspectionWip = (long) (inspectionProcess.getQtt() - completeInspectionCnt);
        return inspectionWip;
    }

    public static void main(String[] args) {
        // 테스트 코드 작성
        StatusTest statusTest = new StatusTest();

        // 검사 공정 설정
        InspectionProcess inspectionProcess = new InspectionProcess();
        inspectionProcess.setQtt(15000L); // 총 수량 설정
        LocalDateTime startTime = LocalDateTime.now().minusHours(2); // 2시간 전(120분전)
        inspectionProcess.setDate(startTime); // 시작 시간 설정
        statusTest.inspectionProcess = inspectionProcess;

        // 시간이 지남으로써 검사 완료된 제품 수량 증가
        int processMinutes = (int) Duration.between(startTime, LocalDateTime.now()).toMinutes(); // 현재 시간과 시작 시간의 차이를 분으로 계산

        // 총 검사 공정 가동시간을 계산 (시간당 준비 시간 10분을 고려)
        int ptTime = 10;
        int processTime = processMinutes - ((processMinutes - ptTime) / 60) * ptTime;
        int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수
        int completeCnt = (int) ((inspectionCnt / 60.0) * processTime); // 완료된 제품 수량 계산(총 가동시간)

        statusTest.completeInspectionCnt = completeCnt;


        // 결과 출력
        double inspectionPercent = statusTest.getInspectionPercent();
        Duration inspectionRuntime = statusTest.getInspectionRuntime();
        Long inspectionWip = statusTest.getInspectionWip();

        System.out.println("검사 완료 수량: " + completeCnt + "개");
        System.out.println("가동 시간: " + processTime + "분");
        System.out.println("재공 재고 수량: " + inspectionWip);
    }
}
