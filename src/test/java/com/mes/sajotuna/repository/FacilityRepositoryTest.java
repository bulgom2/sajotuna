package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Facility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FacilityRepositoryTest {

    @Autowired
    private FacilityRepository facilityRepository;

    @Test
    void Test5() {
        Facility facility = new Facility();

        facility.setQtt(500L);
        facility.setItem("sdfsdf");
        facility.setPt(10);
//        facility.setLt(Duration.ofMinutes(6));
        facility.setStatus(false);
        facility.setName("jell");

        facilityRepository.save(facility);

        System.out.println(facility);
    }
}
