package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;

public class ShipmentProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private int qtt; // 수량
    private String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처

    public ShipmentProcess() {};

    public ShipmentProcess(String no, int qtt, String productName, LocalDateTime date, String ordersId, String company) {
        this.no = no;
        this.qtt = qtt;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
    }

    // 출하 공정은 포장 공정에서 데이터 값을 받아 바로 적용.
    public void startShipment(String no, int qtt, String productName, LocalDateTime date, String ordersId, String company) {
        // 이전 공정(포장)에서 startShipment에 데이터를 저장했음
        // 포장 데이터를 받아오면 출하 공정 시작
        if (no != null && qtt > 0 && date != null && productName != null && ordersId != null && company != null) {
            date = LocalDateTime.now(); // 현재 시간
            this.no = no;  // 현재 공정 로트 번호로 업데이트
        } else {
            System.out.println("현재 출하가 진행중이 아닙니다.");  // 포장 데이터가 없다면
        }
    }
}
