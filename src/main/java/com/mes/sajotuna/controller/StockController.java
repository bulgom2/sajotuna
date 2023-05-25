package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StockController {

    // html 불러오기(재고 관리 페이지)
    @GetMapping("/stock")
    public String stockInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "stock";
    }
}