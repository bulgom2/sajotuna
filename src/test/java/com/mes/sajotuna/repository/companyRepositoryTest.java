package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class companyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void Test3() {

        Company company = companyRepository.findByItemContaining("파우치");

        System.out.println("회사 : " + company);

        System.out.println(company.getName());

    }
}


