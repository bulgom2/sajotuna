package com.mes.sajotuna.gongjung;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class CoolingProcess {
    private String no;   // 로트 번호
    private LocalDateTime date;    // 출하 시간
    private Long qtt; // 수량
    private String productName; // 제품명
    private String ordersId;    // 수주 번호
    private String company; // 거래처

    public CoolingProcess() {}

    public CoolingProcess(String no, Long qtt, LocalDateTime date, String productName, String ordersId, String company) {
        this.no = no;
        this.qtt = qtt;
        this.date = date;
        this.productName = productName;
        this.ordersId = ordersId;
        this.company = company;
    }

    public void startCooling(String no, Long qtt, String productName, LocalDateTime date, String ordersId, String company) {
        // 이전 공정(검사)에서 startCooling에 데이터를 저장했음
        // 검사 데이터를 받아오면 냉각 공정 시작
        if (no != null && qtt > 0 && date != null && productName != null && ordersId != null && company != null) {
            this.no = no;  // 현재 공정 로트 번호
            this.qtt = qtt; // 현재 공정 수량
            this.date = date;   // 현재 공정 시간
            this.productName = productName; // 현재 공정 제품명
            this.ordersId = ordersId; // 현재 공정 수주 번호
            this.company = company; // 현재 공정 거래처

            LocalDateTime currentTime = LocalDateTime.now(); // 현재 시간

            if ((currentTime.getHour() >= 12 && currentTime.getHour() < 13) ||
                    (currentTime.getHour() >= 18 && currentTime.isBefore(LocalDateTime.of(currentTime.toLocalDate().plusDays(1), LocalTime.of(9, 20))))) {
                // 오후 12시부터 1시까지 또는 오후 6시부터 다음날 오전 9시 20분까지 데이터 전송 정지(사람 없음)
                this.date = LocalDateTime.of(currentTime.toLocalDate().plusDays(1), LocalTime.of(9, 20));
            } else {
                // 데이터 전송 정지 시간이 아니라면(사람 있음)
                this.date = currentTime; // 바로 다음 공정으로 넘어감
            }

            // 냉각 공정 완료시, 다음날 오전 9시에 시작되도록 설정
            if (this.qtt == qtt) {     // 검사 공정의 qtt를 비교해 냉각 공정 완료를 확인
                LocalDateTime nextDayStartTime = LocalDateTime.of(currentTime.toLocalDate().plusDays(1), LocalTime.of(9, 0));
                this.date = nextDayStartTime;
            }

            // 다음 공정(포장)으로 데이터 전달
            PackagingProcess packagingProcess = new PackagingProcess();
            packagingProcess.startPackaging(no, qtt, productName, date, ordersId, company);

        } else {
            System.out.println("현재 냉각이 진행중이 아닙니다.");    // 검사 데이터가 없다면
        }
    }

    public void startCooling() {
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

    public Long getQtt() {return qtt;}
}
