package com.mes.sajotuna;

import com.mes.sajotuna.entity.Tests;
import com.mes.sajotuna.repository.TestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SajotunaApplicationTests {

//    @Autowired
//    private TestRepository testRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void test(){
//        Tests tests = new Tests();
//
//        tests.setAge(51);
//
//        testRepository.save(tests);
//    }
//
//    @Test
//    void test2(){
//        Long id = 125L;
//        Optional<Tests> tests = testRepository.findById(id);
//
//        System.out.println(tests);
//        System.out.println(tests.get());
//
//        System.out.println("나이 : " + tests.get().getAge());
//
//        System.out.println();
//
//        Tests tests1 = testRepository.findByTestId(id);
//        System.out.println(tests1);
//        System.out.println("나이 : " + tests1.getAge());
//
//    }

}
