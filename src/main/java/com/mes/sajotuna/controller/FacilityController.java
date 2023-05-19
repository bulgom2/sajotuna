package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilityController {

    // html 불러오기(설비 관리 페이지)
    @GetMapping("/facility")
    public String bomInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "facility";
    }
}
