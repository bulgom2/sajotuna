package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Manufacture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ManufactureRepostoryTest {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Test
    void Test6() {
        Manufacture manufacture = new Manufacture();

        manufacture.setQtt(550L);
        manufacture.setItem("jelly");
        manufacture.setInTime(LocalDateTime.now());
        manufacture.setOutTime(LocalDateTime.now());
//        manufacture.setOrdersId("dfdf");
        manufacture.setProcessId("sdff");

        manufactureRepository.save(manufacture);

        System.out.println(manufacture);
    }
}
