package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
