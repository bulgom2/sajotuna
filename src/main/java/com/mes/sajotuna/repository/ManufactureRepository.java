package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
//    List<Manufacture> findAllByOrdersNo(String ordersNo);
}
