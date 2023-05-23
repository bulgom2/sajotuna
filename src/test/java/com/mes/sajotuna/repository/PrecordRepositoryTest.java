package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Precord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PrecordRepositoryTest {

    @Autowired
    private PrecordRepository precordRepository;

    @Test
    void Test7() {

        Precord precord = new Precord();

        precord.setOrdersId("ddd");
        precord.setLot1("a");
        precord.setLot2("B");
        precord.setLot3("c");
        precord.setLot4("d");
        precord.setLot5("s");
        precord.setLot6("ee");
        precord.setLot7("d");
        precord.setLot8("d");
        precord.setLot9("a");
        precord.setLot10("ds");
        precord.setLot11("dsd");

        precordRepository.save(precord);

        System.out.println(precord);
    }
}
