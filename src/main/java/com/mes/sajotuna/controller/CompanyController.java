package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Company;
import com.mes.sajotuna.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    // html 불러오기(거래처 관리 페이지)
    @GetMapping("/company")
    public String companyInformation(Model model){

        List<Company> companyList = companyRepository.findAll();

        model.addAttribute("companyList", companyList);

        return "company";
    }
}
