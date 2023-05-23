package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// BOM
@Entity
@Table(name = "material")
@Getter @Setter
@ToString
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long id;  // id

    @Column(name = "material_name", nullable = false)
    private String name;  // 재료명

    @Column(name = "material_lt")
    private Double lt;  // 리드타임

    @Column(name = "material_st")
    private Double st;  // 기준 시간

    @Column(name = "material_minorder")
    private Integer minorder;  // 최소 구매 수량

    @Column(name = "material_maxorder")
    private Integer maxorder;  // 최대 구매 수량

    @Column(name = "material_stock")
    private Long stock;  // 재료 재고
}
