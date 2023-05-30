package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// 제품별 이력관리
@Entity
@Table(name = "precord")
@Getter @Setter
@ToString
public class Precord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "precord_id", nullable = false)
    private Long id;  // id

    @Column(name = "orders_id", nullable = false)
    private String ordersId;  // 수주 번호

    @Column(name = "precord_lot1")
    private String lot1;  // 로트1

    @Column(name = "precord_lot2")
    private String lot2;  // 로트2

    @Column(name = "precord_lot3")
    private String lot3;  // 로트3

    @Column(name = "precord_lot4")
    private String lot4;  // 로트4

    @Column(name = "precord_lot5")
    private String lot5;  // 로트5

    @Column(name = "precord_lot6")
    private String lot6;  // 로트6

    @Column(name = "precord_lot7")
    private String lot7;  // 로트7

    @Column(name = "precord_lot8")
    private String lot8;  // 로트8

    @Column(name = "precord_lot9")
    private String lot9;  // 로트9

    @Column(name = "precord_lot10")
    private String lot10;  // 로트10

    @Column(name = "precord_lot11")
    private String lot11;  // 로트11

}
