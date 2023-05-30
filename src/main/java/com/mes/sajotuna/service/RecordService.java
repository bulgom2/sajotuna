package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.dto.RecordDTO;
import com.mes.sajotuna.entity.Process;
import com.mes.sajotuna.entity.Purchase;
import com.mes.sajotuna.entity.Record;
import com.mes.sajotuna.repository.ProcessRepository;
import com.mes.sajotuna.repository.PurchaseRepository;
import com.mes.sajotuna.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final PurchaseRepository purchaseRepository;

    private final ProcessRepository processRepository;

    private final RecordRepository recordRepository;



    public RecordDTO recordSave(OrdersDTO ordersDTO){

        String ordersNo = ordersDTO.getNo();

        RecordDTO recordDTO = new RecordDTO();

        recordDTO.setBeId(ordersNo);
        recordDTO.setStartTime(ordersDTO.getDate());

        List<Purchase> purchaseList = purchaseRepository.findByOrdersNo(ordersNo);

        if(purchaseList.size() != 0){
            recordDTO.setNo(purchaseList.get(0).getNo());
            recordDTO.setEndTime(purchaseList.get(0).getShipDate());
            recordDTO.setQtt(purchaseList.get(0).getQtt());


            Process process = processRepository.findByNo(purchaseList.get(0).getNo().substring(0,2));

            System.out.println("공정은 : " + process);

            recordDTO.setProcessNo(process.getName());

            recordDTO.setStatus(0L);

            Record record = recordDTO.createRecord();

            recordRepository.save(record);
        }

        return recordDTO;
    }
}
