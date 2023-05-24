package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ProcessService {

    private final OrdersRepository ordersRepository;


    //특정 게시글 불러오기
    public OrdersDTO ordersDetail(Long id){

        Orders orders = ordersRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        System.out.println("123 : " + orders);

        OrdersDTO ordersDto = OrdersDTO.of(orders);

        System.out.println("123 : " + orders);

        return ordersDto;
    }

    // 특정 게시글 삭제
    public void ordersDelete(Long id){

        Orders orders = ordersRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        ordersRepository.deleteById(id);
    }
}
