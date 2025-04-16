package org.example.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Notification {
    private Long id;


    private String message;


    private String type;


    private LocalDateTime timestamp;

    private boolean read;

    private String userId;

}






