package com.mes.sajotuna.repository;

import com.mes.sajotuna.entity.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {

    @Query(value = "SELECT m FROM Manufacture m WHERE m.process_id = :processId AND m.manufacture_outTime = (SELECT MAX(m2.manufacture_outTime) FROM Manufacture m2 WHERE m2.process_id = :processId)")
    Manufacture findLatestManufactureByProcessId(String processId);

    @Query(value = "SELECT m FROM Manufacture m WHERE m.facility_id = :facility_id AND m.manufacture_outTime = (SELECT MAX(m2.manufacture_outTime) FROM Manufacture m2 WHERE m2.facility_id = :facility_id)")
    Manufacture findLatestManufactureByFacility_id(String facility_id);
}
