package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    // html 불러오기(거래처 관리 페이지)
    @GetMapping("/company")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "company";
    }
}
