package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {


    Company findByItemContaining(String item);
}
