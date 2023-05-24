package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecordController {

    // html 불러오기(이력 관리 페이지)
    @GetMapping("/record")
    public String recordInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "record";
    }
}