package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 발주관리
@Entity
@Table(name = "purchase")
@Getter @Setter
@ToString
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false, insertable = false, updatable = false)
    private Long id;    // id

    @Column(name = "purchase_no", nullable = false)
    private String no;    // 발주번호(Lot)

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime date;    // 발주일자

    @Column(name = "purchase_status", nullable = false)
    private Long status;    // 진행상태

    @Column(name = "purchase_company", nullable = false)
    private String company;    // 거래처

    @Column(name = "purchase_code")
    private String code;    // 자재코드

    @Column(name = "purchase_item", nullable = false)
    private String item;    // 자재명

    @Column(name = "purchase_qtt", nullable = false)
    private Long qtt;    // 발주 수량

    @Column(name = "purchase_shipdate", nullable = false)
    private LocalDateTime shipDate;    // 입고일

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders_no")
    private Orders orders;

//    @Column(name = "orders_id", nullable = false)
//    private Long ordersId;    // 수주 번호

//    public enum PurchaseStatus {
//        IN_PROGRESS, COMPLETED  // 진행중, 완료
//    }
}
