package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void test2() {
        Stock stock = new Stock();

        stock.setProductId("121");
        stock.setQtt(1000L);
        stock.setUnit("kg");
        stock.setNo("232");
        stock.setTime(LocalDateTime.now());
        stock.setStatus("wldf");

        stockRepository.save(stock);

        System.out.println(stock);
    }
}
