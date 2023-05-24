package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Bom;
import com.mes.sajotuna.repository.BomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BomController {

    @Autowired
    private BomRepository bomRepository;

    // html 불러오기(bom 관리 페이지)
    @GetMapping("/bom")
    public String bomInformation(Model model){

        System.out.println("실행");
        System.out.println("abfdsbv");

        List<Bom> bomList = bomRepository.findAll();

        model.addAttribute("bomList", bomList);

        return "BOM";
    }
}
