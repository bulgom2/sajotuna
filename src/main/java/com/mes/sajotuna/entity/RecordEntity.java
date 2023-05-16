package com.mes.sajotuna.entity;

import javax.persistence.*;

// 이력관리
@Entity
@Table(name = "record")
public class RecordEntity {

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
    private String startTime;    // 시작 시간

    @Column(name = "record_endtime")
    private String endTime;    // 종료 시간

    public enum RecordStatus {
        IN_PROGRESS, COMPLETED  // 진행중, 완료
    }

}
