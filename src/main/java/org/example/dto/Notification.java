package org.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {

    private Long id;
    private String title;
    private String message;
    private Long supplierId;
    private String supplierName;
    private LocalDateTime createdAt;
    private boolean isRead;
}






