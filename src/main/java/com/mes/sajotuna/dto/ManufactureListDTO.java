package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Manufacture;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ManufactureListDTO {
    private List<ManufactureDTO> manufactureDTOList;

    public  List<Manufacture> ListDTOToListEntity(List<ManufactureDTO> manufactureDTO){
        return manufactureDTO.stream()
                .map(ManufactureDTO::toEntity)
                .collect(Collectors.toList());
    }

}
