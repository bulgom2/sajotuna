package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByName(String name);
}
