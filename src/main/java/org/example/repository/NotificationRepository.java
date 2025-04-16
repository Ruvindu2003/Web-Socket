package org.example.repository;

import org.example.entitiy.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {
    List<NotificationEntity> findByUserIdOrderByTimestampDesc(String userId);
    List<NotificationEntity> findByUserIdAndReadOrderByTimestampDesc(String userId, boolean read);
    long countByUserIdAndRead(String userId, boolean read);
}
