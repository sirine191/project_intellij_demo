package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.entity.Message;
import com.springdemo.ipvproject.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessagingController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/send")
    public Message sendMessage(@RequestParam String sender, @RequestParam String recipient, @RequestParam String message) {
        return messagingService.sendMessage(sender, recipient, message);
    }
}

