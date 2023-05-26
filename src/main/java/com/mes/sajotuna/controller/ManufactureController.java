package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Manufacture;
import com.mes.sajotuna.repository.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManufactureController {

    @Autowired
    private ManufactureRepository manufactureRepository;

    // html 불러오기(기준정보 관리 페이지)
    @GetMapping("/manufacture")
    public String manufactureInformation(Model model){

        List<Manufacture> manufactureList = manufactureRepository.findAll();

        model.addAttribute("manufactureList", manufactureList);

        return "manufacture";
    }
}
