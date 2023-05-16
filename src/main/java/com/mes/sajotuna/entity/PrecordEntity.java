package com.mes.sajotuna.entity;

import javax.persistence.*;

// 제품별 이력관리
@Entity
@Table(name = "precord")
public class PrecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "precord_id", nullable = false)
    private Long id;  // id

    @Column(name = "orders_id", nullable = false)
    private String ordersId;  // 수주 번호

    @Column(name = "precord_lot1", nullable = false)
    private String lot1;  // 로트1

    @Column(name = "precord_lot2", nullable = false)
    private String lot2;  // 로트2

    @Column(name = "precord_lot3", nullable = false)
    private String lot3;  // 로트3

    @Column(name = "precord_lot4", nullable = false)
    private String lot4;  // 로트4

    @Column(name = "precord_lot5", nullable = false)
    private String lot5;  // 로트5

    @Column(name = "precord_lot6", nullable = false)
    private String lot6;  // 로트6

    @Column(name = "precord_lot7", nullable = false)
    private String lot7;  // 로트7

    @Column(name = "precord_lot8", nullable = false)
    private String lot8;  // 로트8

    @Column(name = "precord_lot9", nullable = false)
    private String lot9;  // 로트9

    @Column(name = "precord_lot10", nullable = false)
    private String lot10;  // 로트10

    @Column(name = "precord_lot11", nullable = false)
    private String lot11;  // 로트11

}
