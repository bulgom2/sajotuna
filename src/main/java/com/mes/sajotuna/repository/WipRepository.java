package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.WipEntity;

public interface WipRepository extends JpaRepository<WipEntity, Long> {
}
