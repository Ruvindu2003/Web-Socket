package org.example.entitiy;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data

@Table(name = "Notification")
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SuplierEntity supplier;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private boolean isRead;

    public NotificationEntity() {
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }


   }
