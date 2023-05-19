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

    @Column(name = "material_lt", nullable = false)
    private String lt;  // 리드타임

    @Column(name = "material_st", nullable = false)
    private String st;  //

    @Column(name = "material_stock", nullable = false)
    private Long stock;  // 재료4 투입량
}
