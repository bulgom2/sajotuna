package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShipmentController {

    // html 불러오기(bom 관리 페이지)
    @GetMapping("/shipment")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "shipment";
    }
}