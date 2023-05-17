package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
