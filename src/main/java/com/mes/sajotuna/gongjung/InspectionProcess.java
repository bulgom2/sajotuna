package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;

//  검사 공정
public class InspectionProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private static Long qtt; // 수량
    private String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처

    public InspectionProcess() {
        this.no = no;
        this.qtt = qtt;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
    }

    public void startInspection(LocalDateTime date, Long qtt) {
        // 이전 공정(충진)에서 startInspection에 데이터를 저장했음
        // 충진 데이터를 받아오면 검사 공정 시작
//        if (no != null && qtt > 0 && date != null && productName != null && ordersId != null && company != null) {
            this.no = no;  // 현재 공정 로트 번호로 업데이트
            this.qtt = qtt; // 현재 공정 수량
            this.productName = productName; // 현재 공정 제품명
            this.ordersId = ordersId; // 현재 공정 수주 번호
            this.company = company; // 현재 공정 거래처

//            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            LocalDateTime startTime = date.plusMinutes(10); // 공정 시작 시간(현재 시간에서 준비 시간 10분 추가)

            int inspectionCnt = 5000; // 시간당 검사 가능한 제품 수
            int ptTime = 10; // 준비 시간(분)

            // 검사 소요 시간 계산
            Long inspectionTime = (qtt / inspectionCnt) * 60; // 제품수에 따른 검사 소요 시간(분)
//            Long addPtTime = ((inspectionTime / 60) * ptTime);   // 시작 후 1시간마다 준비 시간(분) 추가
            Long leadTime = inspectionTime; // 총 검사 소요 시간(분)

            this.date = startTime.plusMinutes(leadTime); // 다음 공정의 시작 시간(현재 시간에 리드 타임을 더함)


//        } else {
//            System.out.println("현재 검사가 진행중이 아닙니다.");   // 충진 데이터가 없다면
//        }
    }

    public LocalDateTime getDate() {return date;}

    public static Long getQtt() {return qtt;}

    public void setQtt(Long qtt) {
        this.qtt = qtt;
    }

    public void setDate(LocalDateTime now) {}
}

