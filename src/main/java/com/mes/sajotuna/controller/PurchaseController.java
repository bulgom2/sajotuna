package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Purchase;
import com.mes.sajotuna.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    // html 불러오기(발주 관리 페이지)
    @GetMapping("/purchase")
    public String purchaseList(Model model){

        List<Purchase> purchaseList = purchaseRepository.findAll();

        model.addAttribute("purchaseList", purchaseList);

        return "purchase";
    }

}
