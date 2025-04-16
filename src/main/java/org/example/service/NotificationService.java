package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.Notification;
import org.example.entitiy.NotificationEntity;

import org.example.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {




        private final NotificationRepository notificationRepository;


        private final SimpMessagingTemplate messagingTemplate;

        public List<NotificationEntity> getAllNotifications() {
            return notificationRepository.findAll();
        }

        public List<NotificationEntity> getNotificationsByUser(String userId) {
            return notificationRepository.findByUserIdOrderByTimestampDesc(userId);
        }

        public List<NotificationEntity> getUnreadNotificationsByUser(String userId) {
            return notificationRepository.findByUserIdAndReadOrderByTimestampDesc(userId, false);
        }

        public long countUnreadNotifications(String userId) {
            return notificationRepository.countByUserIdAndRead(userId, false);
        }

        public NotificationEntity saveNotification(NotificationEntity notification) {
            if (notification.getTimestamp() == null) {
                notification.setTimestamp(LocalDateTime.now());
            }
            return notificationRepository.save(notification);
        }

        public NotificationEntity markAsRead(Long id) {
            NotificationEntity notification = notificationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Notification not found"));
            notification.setRead(true);
            return notificationRepository.save(notification);
        }

        public void markAllAsRead(String userId) {
            List<NotificationEntity> unreadNotifications = notificationRepository.findByUserIdAndReadOrderByTimestampDesc(userId, false);
            for (NotificationEntity notification : unreadNotifications) {
                notification.setRead(true);
                notificationRepository.save(notification);
            }
        }

        public void sendNotification(NotificationEntity notification) {
            messagingTemplate.convertAndSend("/topic/notifications/" + notification.getUserId(), notification);
            // Also send to a general topic for system-wide notifications if needed
            messagingTemplate.convertAndSend("/topic/notifications", notification);
        }

        public void deleteNotification(Long id) {
            notificationRepository.deleteById(id);
        }
    }


