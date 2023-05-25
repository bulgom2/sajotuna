package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Stock;
import com.mes.sajotuna.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    // html 불러오기(재고 관리 페이지)
    @GetMapping("/stock")
    public String stockInformation(Model model){

        List<Stock> stockList = stockRepository.findAll();

        model.addAttribute("stockList", stockList);

        return "stock";
    }
}