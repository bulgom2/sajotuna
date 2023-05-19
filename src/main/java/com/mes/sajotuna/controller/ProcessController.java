package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProcessController {

    // html 불러오기(공정 관리 페이지)
    @GetMapping("/process")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "process";
    }
}
