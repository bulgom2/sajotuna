package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 수주관리
@Entity
@Table(name = "orders")
@Getter @Setter
@ToString
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id", nullable = false)
    private Long id;    // 수주id

    @Column(name = "orders_no", nullable = false)
    private String no;    // 수주번호

    @Column(name = "orders_date", nullable = false)
    private LocalDateTime date;    // 수주일자

    @Column(name = "orders_status", nullable = false)
    private String status;    // 진행상태

    @Column(name = "orders_company", nullable = false)
    private String company;    // 거래처

    @Column(name = "orders_code", nullable = false)
    private String code;    // 제품코드

    @Column(name = "orders_item", nullable = false)
    private String item;    // 제품명

    @Column(name = "orders_qtt", nullable = false)
    private Long qtt;    // 제품수량

    @Column(name = "orders_shipdate")
    private String shipDate;    // 예상 납품일

}
