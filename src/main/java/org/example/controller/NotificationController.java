package org.example.controller;


import lombok.RequiredArgsConstructor;
import org.example.entitiy.NotificationEntity;
import org.example.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping


public class NotificationController {


    private final NotificationService notificationService;

    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications")
    public NotificationEntity sendNotification(NotificationEntity notification) {
        return notificationService.saveNotification(notification);
    }

    @MessageMapping("/user/{userId}/sendNotification")
    @SendTo("/topic/notifications/{userId}")
    public NotificationEntity sendUserNotification(@DestinationVariable String userId, NotificationEntity notification) {
        notification.setUserId(userId);
        return notificationService.saveNotification(notification);
    }

    @GetMapping("/api/notifications")
    public ResponseEntity<List<NotificationEntity>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/api/users/{userId}/notifications")
    public ResponseEntity<List<NotificationEntity>> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
    }

    @GetMapping("/api/users/{userId}/notifications/unread")
    public ResponseEntity<List<NotificationEntity>> getUnreadUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.getUnreadNotificationsByUser(userId));
    }

    @GetMapping("/api/users/{userId}/notifications/count")
    public ResponseEntity<Long> getUnreadCount(@PathVariable String userId) {
        return ResponseEntity.ok(notificationService.countUnreadNotifications(userId));
    }

    @PostMapping("/api/notifications/create")
    public ResponseEntity<NotificationEntity> createNotification(@RequestBody NotificationEntity notification) {
        NotificationEntity savedNotification = notificationService.saveNotification(notification);
        notificationService.sendNotification(savedNotification);
        return ResponseEntity.ok(savedNotification);
    }

    @PutMapping("/api/notifications/{id}/read")
    public ResponseEntity<NotificationEntity> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @PutMapping("/api/users/{userId}/notifications/read-all")
    public ResponseEntity<?> markAllAsRead(@PathVariable String userId) {
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.ok().build();
    }



}
