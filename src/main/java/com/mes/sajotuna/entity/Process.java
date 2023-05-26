package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// 공정 정보
@Entity
@Table(name = "process")
@Getter @Setter
@ToString
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "process_id", nullable = false)
    private Long id;  // id

    @Column(name = "process_no", nullable = false)
    private String no;  // 공정 코드

    @Column(name = "process_name", nullable = false)
    private String name;  // 공정명

    @Column(name = "process_status", nullable = false)
    private Boolean status;  // 설비 사용 여부

    // 23/05/23 컬럼 추가
    @Column(name = "process_category")
    private String category;  //  (수작업) 자동 설비 여부

    @Column(name = "process_preparation")
    private Integer preparation;  // 공정 준비시간

    @Column(name = "process_lead")
    private Integer lead;  // 공정 소요 시간

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facility_id")
    private Facility facility;  // 설비 정보


}
