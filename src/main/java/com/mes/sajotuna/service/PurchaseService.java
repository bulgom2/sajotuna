package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.PurchaseDto;
import com.mes.sajotuna.entity.Purchase;
import com.mes.sajotuna.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public PurchaseDto purchaseDto(Long id) {

        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        PurchaseDto purchaseDto = PurchaseDto.of(purchase);

        return purchaseDto;
    }
}
