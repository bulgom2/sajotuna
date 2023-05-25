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
        Company company = new Company();

        company.setAddr("addd");
        company.setItem("jelly");
        company.setNo("coupang");
        company.setName("coo");
        company.setPerson("dddd");

        companyRepository.save(company);

        System.out.println(company);

    }
}


