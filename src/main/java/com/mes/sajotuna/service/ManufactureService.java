package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.ManufactureDTO;
import com.mes.sajotuna.entity.Product;
import com.mes.sajotuna.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ManufactureService {

    private final PurchaseRepository purchaseRepository;

    private final OrdersRepository ordersRepository;

    private final BomRepository bomRepository;

    private final MaterialRepository materialRepository;

    private final CompanyRepository companyRepository;

    private final StockRepository stockRepository;

    private final StockService stockService;

    private final ProductRepository productRepository;


    // 생산관리 번호 만들기
    public String makeManufactureCode(ManufactureDTO manufactureDTO, String name){

        LocalDateTime now = LocalDateTime.now();

        Product product = productRepository.findByName(name);

        // 발주 번호 생성
        String code = "MS00-" + product.getNo() + "-" +  now.getYear();

        String dateTime[] = {now.getMonthValue()+"", now.getDayOfMonth()+"", now.getHour()+"", now.getMinute()+"", now.getSecond()+""};

        for(int j=0; j<dateTime.length; j++){
            if(dateTime[j].length() < 2){
                dateTime[j] = "0" + dateTime[j];
            }
            code += dateTime[j];
        }

        return code;
    }
}
