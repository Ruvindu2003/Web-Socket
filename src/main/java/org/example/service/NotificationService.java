package org.example.service;

import org.example.mapper.NotificationMapper;
import org.example.dto.Notification;
import org.example.entitiy.NotificationEntity;
import org.example.entitiy.SuplierEntity;

import org.example.repository.NotificationRepository;
import org.example.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final SupplierRepository supplierRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository,
                               SupplierRepository supplierRepository,
                               NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.supplierRepository = supplierRepository;
        this.notificationMapper = notificationMapper;
    }

    public Notification createNotification(Notification notificationDTO) {
        SuplierEntity supplier = supplierRepository.findById(notificationDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        NotificationEntity notificationEntity = notificationMapper.toEntity(notificationDTO);
        notificationEntity.setSupplier(supplier);

        NotificationEntity savedEntity = notificationRepository.save(notificationEntity);


        return notificationMapper.toDtoWithSupplier(notificationEntity);
    }

    public List<Notification> getNotificationsBySupplierId(Long supplierId) {
        return notificationRepository.findBySupplierId(supplierId)
                .stream()
                .map(notificationMapper::toDtoWithSupplier)
                .collect(Collectors.toList());
    }

    public Notification markAsRead(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        notification = notificationRepository.save(notification);

        return notificationMapper.toDtoWithSupplier(notification);
    }
}
