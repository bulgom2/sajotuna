package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAll();

    // 수주 확정 변경
    @Modifying
    @Query("UPDATE Orders  SET status = :status WHERE no = :no")
    void updateStatusByNo(@Param("status") String status, @Param("no") String no);

    // 수주번호로 찾기
    Orders findByNo(String selectedNo);

    // 날짜 필터링
    List<Orders> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Orders> findAllByStatus(String status);

}
