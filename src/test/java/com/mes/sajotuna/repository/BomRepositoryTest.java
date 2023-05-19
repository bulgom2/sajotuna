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
            bom.setInput1(30L);
            bom.setMaterial2("d");
            bom.setInput2(40L);
            bom.setMaterial3("c");
            bom.setInput3(50L);
            bom.setMaterial4("b");
            bom.setInput4(70L);

            bomRepository.save(bom);

            System.out.println(bom);
        }
    }
