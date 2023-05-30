package com.mes.sajotuna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mes.sajotuna.entity.Manufacture;

import java.util.List;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
    List<Manufacture> findAllByOrdersNo(String ordersNo);
}
