package com.mes.sajotuna.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    // html 불러오기(기준정보 관리 페이지)
    @GetMapping("/product")
    public String productInformation(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "product";
    }
}
