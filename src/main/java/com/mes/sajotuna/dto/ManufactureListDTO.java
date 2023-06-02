package com.mes.sajotuna.dto;

import lombok.Data;

import java.util.List;

@Data
public class ManufactureListDTO {
    private List<ManufactureDTO> manufactureDTOList;

//    public  List<Manufacture> ListDTOToListEntity(List<ManufactureDTO> manufactureDTO){
//        return manufactureDTO.stream()
//                .map(ManufactureDTO::toEntity)
//                .collect(Collectors.toList());
//    }



}
