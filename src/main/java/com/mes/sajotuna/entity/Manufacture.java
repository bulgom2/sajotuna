package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

// 생산관리
@Entity
@Table(name = "manufacture")
@Getter @Setter
@ToString
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacture_id", nullable = false)
    private String manufacture_id;    // id

    @Column(name = "manufacture_item", nullable = false)
    private String manufacture_item;    // 제품명

    @Column(name = "process_id", nullable = false)
    private String process_id;    // 공정 id

    // 23/05/22 String → LocalDateTime 변경
    @Column(name = "manufacture_inTime", nullable = false)
    private LocalDateTime manufacture_inTime;    // 투입 전 시간

    @Column(name = "manufacture_outTime", nullable = false)
    private LocalDateTime manufacture_outTime;    // 투입 후 시간

    @Column(name = "manufacture_qtt", nullable = false)
    private Long manufacture_qtt;    // 투입량

    @Column(name = "orders_no", nullable = false)
    private String orders_no;    // 수주번호

    @Column(name = "facility_id")
    private String facility_id; //설비 명

    @Column(name = "manufacture_status")
    @ColumnDefault("'N'")
    private String status;

    public Manufacture(int manufactureId, String manufactureItem, String processId, LocalDateTime manufactureInTime, LocalDateTime manufactureOutTime, long manufactureQtt, String ordersNo, String facilityId) {
    }

    public Manufacture() {

    }
}
