package com.springdemo.ipvproject.repository;

import com.springdemo.ipvproject.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessagingRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(String sender);
    List<Message> findByRecipient(String recipient);
}
