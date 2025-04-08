package org.example.repository;

import org.example.entitiy.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    List<NotificationEntity> findBySupplierId(Long supplierId);
    List<NotificationEntity> findBySupplierIdAndIsRead(Long supplierId, boolean isRead);
}
