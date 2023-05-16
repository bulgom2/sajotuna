package com.mes.sajotuna.entity;

import javax.persistence.*;

// 재공재고관리
@Entity
@Table(name = "wip")
public class WipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wip_id", nullable = false)
    private Long id;    // id

    @Column(name = "wip_lotid", nullable = false)
    private String lotId;    // Lot 번호

    @Column(name = "wip_name", nullable = false)
    private String name;    // 자재명

    @Column(name = "wip_qtt", nullable = false)
    private Long qtt;    // 재고 수량

    @Column(name = "wip_unit", nullable = false)
    private String unit;    // 단위

    @Column(name = "wip_process", nullable = false)
    private String process;    // 공정

}
