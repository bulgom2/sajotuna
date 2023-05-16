package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Tests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Tests, Long> {

    Tests findByTestId(Long id);
}
