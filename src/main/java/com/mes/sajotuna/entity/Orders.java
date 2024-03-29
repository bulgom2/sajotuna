package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

// 수주관리
@Entity
@Table(name = "orders")
@Getter @Setter
@ToString
public class Orders {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "orders_id", nullable = false)
//    private Long id;    // 수주id

    @Id
    @Column(name = "orders_no", nullable = false)
    private String no;    // 수주번호

    // 23/05/22 LocalDateTime으로 타입 변경
    @Column(name = "orders_date", nullable = false)
    private LocalDateTime date;    // 수주일자

    @Column(name = "orders_status", nullable = false)
    private String status;    // 진행상태

    @Column(name = "orders_company", nullable = false)
    private String company;    // 거래처

//    @Column(name = "orders_code", nullable = false)
//    private String code;    // 제품코드

    @Column(name = "orders_item", nullable = false)
    private String item;    // 제품명

    // 23/05/19 String → Integer로 자료형 수정
    @Column(name = "orders_qtt", nullable = false)
    private Integer qtt;    // 제품수량

    @Column(name = "orders_shipdate")
    private LocalDateTime shipDate;    // 예상 납품일


//    public enum OrdersStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }
}
