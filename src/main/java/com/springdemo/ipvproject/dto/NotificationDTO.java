package com.springdemo.ipvproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDTO {
    private Long id;
    private String message;
    private Long clientId;
    private Long societeId;
    private boolean lu;
    private LocalDateTime dateEnvoi;
}
