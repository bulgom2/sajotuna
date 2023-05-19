package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// 품목 정보
@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;  // id

    @Column(name = "product_name", nullable = false)
    private String name;  // 품목명

    @Column(name = "product_no", nullable = false)
    private String no;  // 품목코드

    @Column(name = "product_status", nullable = false)
    private String status;  // 분류

    @Column(name = "product_unit", nullable = false)
    private String unit;  // 단위

}
