package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.ShipmentEntity;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
}
