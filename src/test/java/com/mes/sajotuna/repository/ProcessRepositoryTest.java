package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Process;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
public class ProcessRepositoryTest {

    @Autowired
    private ProcessRepository processRepository;

    @Test
    void Test8() {
        Process process = new Process();

        process.setNo("dddd");
        process.setPt(30);
        process.setLt(Duration.ofMinutes(6));
        process.setName("sdfdf");
        process.setStatus(true);
        process.setFacilityId(10L);

        processRepository.save(process);

        System.out.println(process);
    }
}
