package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Product;
import com.mes.sajotuna.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // html 불러오기(기준정보 관리 페이지)
    @GetMapping("/product")
    public String productInformation(Model model){

        List<Product> productList = productRepository.findAll();

        model.addAttribute("productList", productList);

        System.out.println("실행");
        System.out.println("abfdsbv");
        return "product";
    }
}