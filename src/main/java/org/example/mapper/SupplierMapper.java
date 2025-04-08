package org.example.mapper;

import org.example.dto.SupplierDTO;
import org.example.entitiy.SuplierEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface SupplierMapper {

        SupplierDTO toDto(SuplierEntity supplier);

        SuplierEntity toEntity(SupplierDTO supplierDTO);
}
