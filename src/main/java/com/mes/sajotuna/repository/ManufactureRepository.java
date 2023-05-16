package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.ManufactureEntity;

public interface ManufactureRepository extends JpaRepository<ManufactureEntity, Long> {
}
