package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Shipment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class ShipmentRepositoryTest {

    @Autowired
    protected ShipmentRepository shipmentRepository;

    @Test
    void Test12() {

        Shipment shipment = new Shipment();

        shipment.setCompany("fdsfsdf");
        shipment.setNo("dfdfd");
        shipment.setQtt(30L);
        shipment.setDate(LocalDateTime.now());
        shipment.setName("dfdfd");

        shipmentRepository.save(shipment);

        System.out.println(shipment);
    }
}
