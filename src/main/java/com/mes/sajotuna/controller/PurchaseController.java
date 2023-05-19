package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseController {

    // html 불러오기(발주 관리 페이지)
    @GetMapping("/purchase")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "purchase";
    }
}