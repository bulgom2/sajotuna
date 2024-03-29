package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.PurchaseDTO;
import com.mes.sajotuna.dto.StockDTO;
import com.mes.sajotuna.entity.Product;
import com.mes.sajotuna.entity.Stock;
import com.mes.sajotuna.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final ProductRepository productRepository;


    public StockDTO stockSave(PurchaseDTO purchaseDTO){

        String item = purchaseDTO.getItem();

        Product product = productRepository.findByName(item);

        StockDTO stockDTO = new StockDTO();

        stockDTO.setNo(purchaseDTO.getNo());
        stockDTO.setStatus("입고");
        stockDTO.setQtt(purchaseDTO.getQtt());
        stockDTO.setTime(purchaseDTO.getShipDate());
        stockDTO.setUnit(product.getUnit());
        stockDTO.setProductName(product.getName());

        Stock stock = stockDTO.createStock();

        stockRepository.save(stock);

        stockDTO.setStatus("출고");
        stockDTO.setQtt(purchaseDTO.getQtt() * (-1));

        stock = stockDTO.createStock();

        stockRepository.save(stock);

        return stockDTO;
    }

}
