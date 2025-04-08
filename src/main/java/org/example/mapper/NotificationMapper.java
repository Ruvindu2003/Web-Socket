package org.example.mapper;

import org.example.entitiy.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.management.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {



        Notification toDto(NotificationEntity notification);



        @Mapping(target = "supplierId", source = "supplier.id")
        @Mapping(target = "supplierName", source = "supplier.name")
        org.example.dto.Notification toDtoWithSupplier(NotificationEntity notification);

        NotificationEntity toEntity(org.example.dto.Notification notificationDTO);
}

    // SupplierMapper.java






