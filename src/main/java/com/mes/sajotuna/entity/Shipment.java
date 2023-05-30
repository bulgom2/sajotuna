package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 출하관리
@Entity
@Table(name = "shipment")
@Getter @Setter
@ToString
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id", nullable = false)
    private Long id;  // id

    @Column(name = "shipment_company", nullable = false)
    private String company;  // 거래처(=수령자)

    @Column(name = "orders_no", nullable = false)
    private String ordersNo;  // 수주 번호

    @Column(name = "shipment_qtt", nullable = false)
    private Long qtt;  // 출하수량(=완제품 수량)

    @Column(name = "shipment_no", nullable = false)
    private String no;  // 완제품 Lot no

    @Column(name = "shipment_name", nullable = false)
    private String name;  // 품목명

    @Column(name = "shipment_date")
    private LocalDateTime date;  // 수령일(=출하일)

//    public enum ShipmentStatus {
//        COMPLETE("완료"),
//        PROCEEDING("진행중"),
//        WAITING("대기중");
//
//        private String label;
//
//        ShipmentStatus(String label) {
//            this.label = label;
//        }
//
//        public String getLabel() {
//            return label;
//        }
//    }

}

