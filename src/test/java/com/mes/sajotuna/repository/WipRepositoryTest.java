package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Wip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WipRepositoryTest {

    @Autowired
    private WipRepository wipRepository;

    @Test
    void Test13() {

        Wip wip = new Wip();

        wip.setName("SDfsdfsd");
        wip.setUnit("kg");
        wip.setProcess("dfd");
        wip.setQtt(5L);
        wip.setLotId("dddd");

        wipRepository.save(wip);

        System.out.println(wip);
    }
}
