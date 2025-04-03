package com.springdemo.ipvproject.service;

import com.springdemo.ipvproject.entity.Message;
import com.springdemo.ipvproject.repository.MessagingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessagingService {

    @Autowired
    private MessagingRepository messagingRepository;

    public Message sendMessage(String sender, String recipient, String message) {
        Message newMessage = new Message(sender, recipient, message, new Date());
        return messagingRepository.save(newMessage);
    }
}

