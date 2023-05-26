package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;

public class PackagingProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private static Long qtt; // 수량
    private static String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처
    private Long boxes;

    public PackagingProcess() {};

    public PackagingProcess(String no, Long qtt, LocalDateTime date, String productName, String ordersId, String company) {
        this.no = no;
        this.qtt = qtt;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
    }

    public void startPackaging(String no, Long qtt, String productName, LocalDateTime date, String ordersId, String company) {
        // 이전 공정(검사)에서 startPackaging에 데이터를 저장했음
        // 검사 데이터를 받아오면 냉각 공정 시작
        if (no != null && qtt > 0 && date != null && productName != null && ordersId != null && company != null) {
            this.productName = productName; // 현재 공정 제품명
            this.ordersId = ordersId; // 현재 공정 수주 번호
            this.company = company; // 현재 공정 거래처

            LocalDateTime startTime = date.plusMinutes(20); // 현재 시간에서 준비 시간 20분 추가
            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간

            // 근무 시간인지 확인
            if ((currentTime.getHour() >= 9 && currentTime.getHour() < 12) ||
                    ( currentTime.getHour() == 13 && currentTime.getMinute() >= 20 && currentTime.getHour() < 18)) {  // 점심 시간(12시~1시 20분)을 뺀 근무 시간 9 to 6

                // 제품 종류에 따라 박스 개수 계산
                Long boxes;
                if (productName.contains("즙")) {
                    boxes = qtt / 30; // 즙은 박스당 30개
                } else {
                    boxes = qtt / 25; // 젤리는 박스당 25개
                }
                this.boxes = boxes;

                // 포장 소요 시간 계산
                double packagingCnt = 200.0; // 시간당 포장 가능한 제품 수(박스)
                int packagingTime = (int)Math.ceil((boxes / packagingCnt) * 60); // 시작 후 경과한 시간(분)
                int leadTime = packagingTime; // 총 포장 소요 시간

                this.date = startTime.plusMinutes(leadTime); // 다음 공정의 시작 시간

                this.no = no;  // 현재 공정 로트 번호

                // qtt를 박스 값으로 전달
                this.qtt = boxes;

            } else {
                System.out.println("현재 근무 시간이 아닙니다.");    // 근무시간이 아니면
            }
        }   else {
            System.out.println("현재 포장이 진행중이 아닙니다.");    // 냉각 데이터가 없다면
        }
    }

    public void startPackaging(CoolingProcess coolingProcess) {
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setQtt(long qtt) {
        this.qtt = qtt;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public static String getProductName() {
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

    public static Long getQtt() {return qtt;}


    public Long getBoxes() {return boxes;}
}

