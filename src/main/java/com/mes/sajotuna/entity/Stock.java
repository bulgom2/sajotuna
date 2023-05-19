package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 재고관리
@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id", nullable = false)
    private Long id;    // id

    @Column(name = "product_id", nullable = false)
    private String productId;    // 품목 정보 id

    @Column(name = "stock_qtt", nullable = false)
    private Long qtt;    // 입출고 수량

    @Column(name = "stock_unit", nullable = false)
    private String unit;    // 단위

    @Column(name = "stock_no", nullable = false)
    private String no;    // Lot 번호

    @Column(name = "stock_time", nullable = false)
    private LocalDateTime time;    // 시간

    @Column(name = "stock_status", nullable = false)
    private String status;    // 진행상태

}
