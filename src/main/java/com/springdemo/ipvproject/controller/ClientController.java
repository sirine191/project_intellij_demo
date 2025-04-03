package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.dto.ClientDTO;
import com.springdemo.ipvproject.entity.Client;
import com.springdemo.ipvproject.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody ClientDTO clientDTO) {
        try {
            Client savedClient = clientService.register(clientDTO);
            return ResponseEntity.ok(savedClient);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Client>> searchClients(@RequestParam String keyword) {
        List<Client> clients = clientService.searchClientsBySociete(keyword);
        return ResponseEntity.ok(clients);
    }
}
