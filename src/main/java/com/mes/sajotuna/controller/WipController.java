package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WipController {

    // html 불러오기(재공재고 관리 페이지)
    @GetMapping("/wip")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "wip";
    }
}