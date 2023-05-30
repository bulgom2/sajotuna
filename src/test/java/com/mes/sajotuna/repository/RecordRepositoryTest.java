package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class RecordRepositoryTest {

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void Test11() {

        Record record = new Record();

        record.setBeId("sdds");
        record.setQtt(500L);
        record.setNo("sdfsfs");
//        record.setProcessId("sdfdfd");
        record.setStatus(50L);
        record.setStartTime(LocalDateTime.now());
        record.setEndTime(LocalDateTime.now());

        recordRepository.save(record);

        System.out.println(record);
    }
}
