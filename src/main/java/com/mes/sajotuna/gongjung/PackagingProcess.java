package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;

public class PackagingProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private int qtt; // 수량
    private String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처

    public PackagingProcess() {};

    public PackagingProcess(String no, int qtt, LocalDateTime date, String productName, String ordersId, String company) {
        this.no = no;
        this.qtt = qtt;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
    }

    public void startPackaging(String no, int qtt, String productName, LocalDateTime date, String ordersId, String company) {
        // 이전 공정(검사)에서 startPackaging에 데이터를 저장했음
        // 검사 데이터를 받아오면 냉각 공정 시작
        if (no != null && qtt > 0 && date != null && productName != null && ordersId != null && company != null) {
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간
            LocalDateTime startTime = currentTime.plusMinutes(20); // 현재 시간에서 준비 시간 20분 추가
            this.no = no;  // 현재 공정 로트 번호로 업데이트

            // 근무 시간인지 확인
            if ((currentTime.getHour() >= 9 && currentTime.getHour() < 12) ||
                    (currentTime.getHour() >= 13 && currentTime.getHour() < 18)) {  // 점심 시간(12시~1시)을 뺀 근무 시간 9 to 6

                // 제품 종류에 따라 박스 개수 계산
                int boxes;
                if (productName.equals("즙")) {
                    boxes = qtt / 30; // 즙은 박스당 30개
                } else {
                    boxes = qtt / 25; // 젤리는 박스당 25개
                }

                // 포장 소요 시간 계산
                int packagingCnt = 200; // 시간당 포장 가능한 제품 수(박스)
                int ptTime = 20; // 준비 시간(분)

                int packagingTime = (boxes / packagingCnt) * 60; // 시작 후 경과한 시간(분)
                int addPtTime = ((packagingTime / 60) / 1) * ptTime; // 시작 후 1시간마다 준비 시간 추가
                int leadTime = packagingTime + addPtTime; // 총 포장 소요 시간

                date = startTime.plusMinutes(leadTime); // 다음 공정의 시작 시간

                // 출하 공정으로 데이터 전달
                ShipmentProcess shipmentProcess = new ShipmentProcess();
                shipmentProcess.startShipment(no, qtt, productName, date, ordersId, company);

            } else {
                System.out.println("현재 근무 시간이 아닙니다.");    // 근무시간이 아니면
            }
        }   else {
            System.out.println("현재 포장이 진행중이 아닙니다.");    // 냉각 데이터가 없다면
        }
    }
}

