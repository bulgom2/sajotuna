package com.mes.sajotuna.dto;

import com.mes.sajotuna.entity.Manufacture;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ManufactureDTO implements Cloneable{

    Long manufacture_id;
    String manufacture_item;
    String process_id;
    String orders_no;
    String facility_id;
    long manufacture_qtt;
    LocalDateTime manufacture_inTime;
    LocalDateTime manufacture_outTime;
    String beforeLot;
    String thisLot;
    Long outPut;


    public ManufactureDTO (){

    }

    public ManufactureDTO(ManufactureDTO m){
        this.manufacture_id = m.getManufacture_id();
        this.manufacture_item = m.getManufacture_item();
        this.process_id = m.getProcess_id();
        this.orders_no = m.getOrders_no();
        this.facility_id = m.getFacility_id();
        this.manufacture_qtt = m.getManufacture_qtt();
        this.manufacture_inTime = m.getManufacture_inTime();
        this.manufacture_outTime = m.getManufacture_outTime();
        this.beforeLot = m.getBeforeLot();
        this.thisLot = m.getThisLot();
        this.outPut = m.getOutPut();
    }

    @Override
    public ManufactureDTO clone() {
        try {
            return (ManufactureDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public static ModelMapper modelMapper = new ModelMapper();

    public Manufacture createManufacture(){
        return modelMapper.map(this, Manufacture.class);
    }

    public static ManufactureDTO of(Manufacture manufacture){
        return modelMapper.map(manufacture, ManufactureDTO.class);
    }


    public Manufacture dtoToEntity(ManufactureDTO dto){

        Manufacture entity = Manufacture.builder()
                .manufacture_item(dto.getManufacture_item() )
                .process_id(dto.getProcess_id())
                .orders_no(dto.getOrders_no())
                .facility_id(dto.getFacility_id())
                .manufacture_qtt(dto.getManufacture_qtt())
                .manufacture_inTime(dto.getManufacture_inTime())
                .manufacture_outTime(dto.getManufacture_outTime())
                .build();

        return entity;
    }


    public static List<ManufactureDTO> of(List<Manufacture> Manufacture){
//        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(Manufacture, new TypeToken<List<ManufactureDTO>>() {}.getType());
    }

}
