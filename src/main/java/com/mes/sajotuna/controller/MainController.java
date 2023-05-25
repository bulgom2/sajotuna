package com.mes.sajotuna.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class MainController {

        @GetMapping("/main")
        public String main(Model model) {
            // 필요한 데이터를 조회하고 모델에 담기

            // 템플릿 이름 반환
            return "main";
        }

    }


