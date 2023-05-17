package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
