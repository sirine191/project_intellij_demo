package com.springdemo.ipvproject.controller;

import com.springdemo.ipvproject.entity.Societe;
import com.springdemo.ipvproject.service.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/societes")
public class SocieteController {

    @Autowired
    private SocieteService societeService;

    @GetMapping
    public List<Societe> getAllSocietes() {
        return societeService.getAllSocietes();
    }

    @GetMapping("/search")
    public List<Societe> searchSocietes(@RequestParam String keyword) {
        return societeService.searchSocietes(keyword);
    }
}
