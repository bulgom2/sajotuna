package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.PrecordDTO;
import com.mes.sajotuna.entity.Precord;
import com.mes.sajotuna.entity.Purchase;
import com.mes.sajotuna.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PrecordService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    private final PurchaseRepository purchaseRepository;

    private final PrecordRepository precordRepository;


    private final RecordRepository recordRepository;

    private final ManufactureRepository manufactureRepository;

    public PrecordDTO precordSave(OrdersDTO ordersDTO){

        String ordersNo = ordersDTO.getNo();

        PrecordDTO precordDTO = new PrecordDTO();

        precordDTO.setOrdersId(ordersNo);

        List<Purchase> purchaseList = purchaseRepository.findByOrdersNo(ordersNo);

        if(purchaseList.size() != 0){
            precordDTO.setLot1(purchaseList.get(0).getNo());

//        List<Manufacture> manufactureList = manufactureRepository.findAllByOrdersNo(ordersNo);
//
//
//        for (int i = 0; i < manufactureList.size(); i++) {
//            String columnName = "lot" + (i + 2);
//            precordDTO.setLot(columnName, manufactureList.get(i));
//        }

            Precord precord = precordDTO.createPrecord();

            precordRepository.save(precord);
        }

        return precordDTO;
    }

}
