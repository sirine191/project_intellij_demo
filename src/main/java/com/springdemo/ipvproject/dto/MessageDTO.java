package com.springdemo.ipvproject.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDTO {
    private String sender;
    private String recipient;
    private String content;
    private LocalDateTime timestamp;
}
