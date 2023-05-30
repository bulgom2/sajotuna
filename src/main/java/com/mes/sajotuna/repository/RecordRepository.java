package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Record;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByBeId(String ordersNo);

    Record findByBeIdContaining(String ordersNo);
}
