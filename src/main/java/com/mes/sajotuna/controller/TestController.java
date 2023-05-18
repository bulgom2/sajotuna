package com.mes.sajotuna.controller;

import com.mes.sajotuna.entity.Tests;
import com.mes.sajotuna.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@Controller
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/")
    public String getTest(){
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
