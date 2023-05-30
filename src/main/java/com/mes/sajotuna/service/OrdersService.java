package com.mes.sajotuna.service;

import com.mes.sajotuna.dto.OrdersDTO;
import com.mes.sajotuna.entity.Orders;
import com.mes.sajotuna.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;



    // 수주 번호 등록 후 저장하기
    public OrdersDTO ordersMakeCode(OrdersDTO ordersDTO){

        ordersDTO.setDate(LocalDateTime.now());

        System.out.println("OrdersDto " + ordersDTO.toString());

        LocalDateTime orderDay = ordersDTO.getDate();

        String dateTime[] = {orderDay.getMonthValue()+"", orderDay.getDayOfMonth()+"", orderDay.getHour()+"", orderDay.getMinute()+"", orderDay.getSecond()+""};

        String code = "SJ" + orderDay.getYear();

        for(int i=0; i<dateTime.length; i++){
            if(dateTime[i].length() < 2){
                dateTime[i] = "0" + dateTime[i];
            }
            code += dateTime[i];
        }

        System.out.println("수주번호 : " + code);

        ordersDTO.setNo(code);

        ordersDTO.setStatus("대기");

        Orders orders = ordersDTO.createOrders();

        System.out.println("orders " + orders);

        ordersRepository.save(orders);

        System.out.println("수주 dto : " + ordersDTO);

        ordersDTO = OrdersDTO.of(orders);

        return ordersDTO;
    }


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

    // 선택 날짜 필터링
    public List<Orders> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findByDateBetween(startDate, endDate);
    }

    public void updateShipDateByOrdersNo(String ordersNo, LocalDateTime shipDate) {
        Orders orders = ordersRepository.findByNo(ordersNo);

        if (orders == null) {
            throw new EntityNotFoundException("Orders not found with no: " + ordersNo);
        }

        orders.setShipDate(shipDate);
        ordersRepository.save(orders);
    }
}
