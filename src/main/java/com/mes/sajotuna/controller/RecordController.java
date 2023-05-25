package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Record;
import com.mes.sajotuna.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    // html 불러오기(이력 관리 페이지)
    @GetMapping("/record")
    public String recordInformation(Model model){

        List<Record> recordList = recordRepository.findAll();
        model.addAttribute("recordList", recordList);

        return "record";
    }
}