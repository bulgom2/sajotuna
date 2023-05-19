package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// 설비관리
@Entity
@Table(name = "facility")
@Getter
@Setter
@ToString
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id", nullable = false)
    private Long id;  // 설비 코드

    @Column(name = "facility_name", nullable = false)
    private String name;  // 설비명

    @Column(name = "facility_item", nullable = false)
    private String item;  // 생산 제품

    @Column(name = "facility_qtt", nullable = false)
    private Long qtt;  // 생산 가능량

    @Column(name = "facility_pt")
    private int pt;  // 생산 준비시간

    @Column(name = "facility_lt")
    private int lt;  // 생산 소요 시간

    @Column(name = "facility_status")
    private Boolean status;  // 설비 상태

}
