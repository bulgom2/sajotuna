package com.mes.sajotuna.entity;

import javax.persistence.*;

// 출하관리
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id", nullable = false)
    private Long id;  // id

    @Column(name = "shipment_company", nullable = false)
    private String company;  // 거래처(=수령자)

    @Column(name = "orders_id", nullable = false)
    private String ordersId;  // 수주 번호

    @Column(name = "shipment_qtt", nullable = false)
    private Long qtt;  // 출하수량(=완제품 수량)

    @Column(name = "shipment_no", nullable = false)
    private String no;  // 완제품 Lot no

    @Column(name = "shipment_name", nullable = false)
    private String name;  // 품목명

    @Column(name = "shipment_date")
    private String date;  // 수령일(=출하일)

}
