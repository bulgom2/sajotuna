package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAll();

//    @Transactional
    @Modifying
    @Query("UPDATE Orders o SET o.status = :status WHERE o.no = :no")
    void updateStatusByNo(@Param("no") String no, @Param("status") String status);

}
