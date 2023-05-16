package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.RecordEntity;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
}
