package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Shipment;
import com.mes.sajotuna.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShipmentController {

    @Autowired
    private ShipmentRepository ShipmentRepository;

    // html 불러오기(bom 관리 페이지)
    @GetMapping("/shipment")
    public String shipmentInformation(Model model){

        List<Shipment> shipmentList = ShipmentRepository.findAll();

        model.addAttribute("shipmentList", shipmentList);

        return "shipment";
    }
}