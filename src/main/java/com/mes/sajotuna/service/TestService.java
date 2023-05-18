package com.mes.sajotuna.service;

import com.mes.sajotuna.entity.Tests;
import com.mes.sajotuna.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private TestRepository testRepository;

    public void saveNumber(Tests tests) {
        testRepository.save(tests);
    }

}
