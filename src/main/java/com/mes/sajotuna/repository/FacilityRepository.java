package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
