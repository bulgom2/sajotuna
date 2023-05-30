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

        manufacture.setManufacture_id("dd");

        manufacture.setManufacture_qtt(550L);
        manufacture.setManufacture_item("jelly");
        manufacture.setManufacture_inTime(LocalDateTime.now());
        manufacture.setManufacture_outTime(LocalDateTime.now());
        manufacture.setOrders_no("dfdf");
        manufacture.setProcess_id("sdff");


        manufactureRepository.save(manufacture);

        System.out.println(manufacture);
    }
}
