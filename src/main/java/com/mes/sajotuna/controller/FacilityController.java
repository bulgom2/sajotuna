package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Facility;
import com.mes.sajotuna.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FacilityController {

    @Autowired
    private FacilityRepository facilityRepository;

    // html 불러오기(설비 관리 페이지)
    @GetMapping("/facility")
    public String bomInformation(Model model){

        System.out.println("실행");
        System.out.println("abfdsbv");

        List<Facility> facilityList = facilityRepository.findAll();

        model.addAttribute("facilityList", facilityList);

        return "facility";
    }
}
