package com.mes.sajotuna.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

// BOM
@Entity
@Table(name = "bom")
@Getter
@Setter
@ToString
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bom_id", nullable = false)
    private Long id;  // id

    @Column(name = "bom_item", nullable = false)
    private String item;  // 제품명

    @Column(name = "bom_material_1", nullable = false)
    private String material1;  // 재료명1

    @Column(name = "bom_input_1", nullable = false)
    private Long input1;  // 재료1 투입량

    @Column(name = "bom_material_2", nullable = false)
    private String material2;  // 재료명2

    @Column(name = "bom_input_2", nullable = false)
    private Long input2;  // 재료2 투입량

    @Column(name = "bom_material_3", nullable = false)
    private String material3;  // 재료명3

    @Column(name = "bom_input_3", nullable = false)
    private Long input3;  // 재료3 투입량

    @Column(name = "bom_material_4", nullable = false)
    private String material4;  // 재료명4

    @Column(name = "bom_input_4", nullable = false)
    private Long input4;  // 재료4 투입량

}
