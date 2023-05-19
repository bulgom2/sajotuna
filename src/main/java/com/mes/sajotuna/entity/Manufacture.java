package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 생산관리
@Entity
@Table(name = "manufacture")
@Getter
@Setter
@ToString
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacture_id", nullable = false)
    private Long id;    // id

    @Column(name = "manufacture_item", nullable = false)
    private String item;    // 제품명

    @Column(name = "process_id", nullable = false)
    private String processId;    // 공정 id

    @Column(name = "manufacture_intime", nullable = false)
    private LocalDateTime inTime;    // 투입 전 시간

    @Column(name = "manufacture_outtime", nullable = false)
    private LocalDateTime outTime;    // 투입 후 시간

    @Column(name = "manufacture_qtt", nullable = false)
    private Long qtt;    // 투입량

    @Column(name = "orders_id", nullable = false)
    private String ordersId;    // 수주번호

}
