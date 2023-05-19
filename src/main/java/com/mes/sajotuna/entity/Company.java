package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// 거래처 정보
@Entity
@Table(name = "company")
@Getter
@Setter
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private Long id;  // id

    @Column(name = "company_no", nullable = false)
    private String no;  // 거래처 코드

    @Column(name = "company_name", nullable = false)
    private String name;  // 거래처명

    @Column(name = "company_addr", nullable = false)
    private String addr;  // 주소

    @Column(name = "company_person", nullable = false)
    private String person;  // 담당자

    @Column(name = "company_item", nullable = false)
    private String item;  // 담당 품목

}
