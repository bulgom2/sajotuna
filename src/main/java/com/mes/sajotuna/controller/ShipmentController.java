package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Shipment;
import com.mes.sajotuna.gongjung.ShipmentProcess;
import com.mes.sajotuna.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    private ShipmentProcess shipmentProcess;

    public ShipmentController() {
        shipmentProcess = new ShipmentProcess();  // 출하 객체 생성
    }

    @PostMapping("/startShipment")
    public String startShipment(int no, String productName, String date, String ordersId, String company, int boxes, Model model) {

        // 출하 공정 시작
        shipmentProcess.startShipment(no, productName, date, ordersId, company, boxes);

        model.addAttribute("shipmentProcess", shipmentProcess);
        return "redirect:/shipment";  // 출하 페이지로 리다이렉트
    }

    @GetMapping("/shipment")
    public String showShipment(Model model) {

        List<Shipment> shipmentList = shipmentRepository.findAll();

        model.addAttribute("shipmentProcess", shipmentList);
        return "shipment";
    }
}

