package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Wip;

public interface WipRepository extends JpaRepository<Wip, Long> {
}
