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

    @Column(name = "facility_id", nullable = false)
    private Long facilityId;  // 설비 정보

    @Column(name = "process_pt")
    private String pt;  // 공정 준비시간

    @Column(name = "process_lt")
    private String lt;  // 공정 소요 시간

    public enum ProcessStatus {
        IN_PROGRESS, COMPLETED  // 가동중, 완료
    }

}
