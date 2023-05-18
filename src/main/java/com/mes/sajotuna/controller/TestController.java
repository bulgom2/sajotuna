package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Tests;
import com.mes.sajotuna.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String getTest(){
        System.out.println("실행");
        System.out.println("abfdsbv");
        return "layout1";
    }

    @PostMapping("/tests")
    public String createTests(@RequestParam int age) {
        System.out.println("age " + age);

        Tests tests = new Tests();
        tests.setAge(age);

        System.out.println("id " + tests.getTestId());
        System.out.println("age " + tests.getAge());

        testRepository.save(tests);

        System.out.println("id " + tests.getTestId());
        System.out.println("age " + tests.getAge());

        return "redirect:/";
    }


}
