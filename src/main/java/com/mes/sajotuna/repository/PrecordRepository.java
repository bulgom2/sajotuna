package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.PrecordEntity;

public interface PrecordRepository extends JpaRepository<PrecordEntity, Long> {
}
