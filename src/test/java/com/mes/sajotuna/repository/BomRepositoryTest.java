package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Bom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

    @SpringBootTest
    public class BomRepositoryTest {

        @Autowired
        private BomRepository bomRepository;

        @Test
        void test4() {
            Bom bom = new Bom();

            bom.setItem("jell");
            bom.setMaterial1("a");
            bom.setInput1(30.0);
            bom.setMaterial2("d");
            bom.setInput2(40.0);
            bom.setMaterial3("c");
            bom.setInput3(50.0);
            bom.setMaterial4("b");
            bom.setInput4(70.0);

            bomRepository.save(bom);

            System.out.println(bom);
        }
    }
