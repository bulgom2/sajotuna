package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Process;
import com.mes.sajotuna.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProcessController {

    @Autowired
    private ProcessRepository processRepository;

    // html 불러오기(공정 관리 페이지)
    @GetMapping("/process")
    public String processInformation(Model model){

        List<Process> processList = processRepository.findAll();

        model.addAttribute("processList", processList);

        return "process";
    }
}
