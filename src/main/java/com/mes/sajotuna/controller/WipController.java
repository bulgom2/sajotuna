package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Wip;
import com.mes.sajotuna.repository.WipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WipController {

    @Autowired
    private WipRepository wipRepository;

    // html 불러오기(재공재고 관리 페이지)
    @GetMapping("/wip")
    public String wipInformation(Model model){

        List<Wip> wipList = wipRepository.findAll();

        model.addAttribute("wipList", wipList);

        return "wip";
    }
}