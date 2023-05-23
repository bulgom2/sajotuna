package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void Test9 () {

        Product product = new Product();

        product.setNo("a");
        product.setName("b");
        product.setUnit("c");
        product.setStatus("d");

        productRepository.save(product);

        System.out.println(product);

    }
}
