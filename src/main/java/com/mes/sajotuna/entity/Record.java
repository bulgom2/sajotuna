package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 이력관리
@Entity
@Table(name = "record")
@Getter @Setter
@ToString
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Long id;    // id

    @Column(name = "record_no")
    private String no;    // Lot.no

    @Column(name = "process_id", nullable = false)
    private String processId;    // 공정 id

    @Column(name = "record_beid")
    private String beId;    // 이전 Lot.no

    @Column(name = "record_qtt", nullable = false)
    private Long qtt;    // 투입량

    @Column(name = "record_status", nullable = false)
    private Long status;    // 진행 상태

    @Column(name = "record_starttime")
    private LocalDateTime startTime;    // 시작 시간

    @Column(name = "record_endtime")
    private LocalDateTime endTime;    // 종료 시간

}
