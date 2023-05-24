package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;

public class ShipmentProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처

    private Long boxes; // 수량

    public ShipmentProcess() {};

    public ShipmentProcess(String no, String productName, LocalDateTime date, String ordersId, String company, Long boxes) {
        this.no = no;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
        this.boxes = boxes;
    }

    // 출하 공정은 포장 공정에서 데이터 값을 받아 바로 적용.
    public void startShipment(String no, String productName, LocalDateTime date, String ordersId, String company, Long boxes) {
        // 이전 공정(포장)에서 startShipment에 데이터를 저장했음
        // 포장 데이터를 받아오면 출하 공정 시작
        if (no != null && boxes > 0 && date != null && productName != null && ordersId != null && company != null) {
            this.no = no;  // 출하할 로트 번호로 업데이트
            this.date = date;   // 출하 시간 업데이트
            this.productName = productName; // 출하할 제품명
            this.ordersId = ordersId; // 출하할 수주 번호
            this.company = company; // 출하할 공정 거래처
            this.boxes = boxes;   // 출하할 제품 수량
        } else {
            System.out.println("현재 출하가 진행중이 아닙니다.");  // 포장 데이터가 없다면
        }
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setQtt(long boxes) {
        this.boxes = boxes;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNo() {return no;}

    public Long getBoxes() {return boxes;}

    public void startShipment(int no, String date, String productName, String ordersId, String company, int boxes) {}
}
