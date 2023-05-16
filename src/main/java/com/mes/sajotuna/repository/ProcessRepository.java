package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.ProcessEntity;

public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
}
