package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {
}
