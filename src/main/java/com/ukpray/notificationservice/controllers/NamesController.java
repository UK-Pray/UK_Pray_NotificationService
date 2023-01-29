package com.ukpray.notificationservice.controllers;

import com.ukpray.notificationservice.models.CountData;
import com.ukpray.notificationservice.services.NamesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/names")
public class NamesController {
    private final NamesService namesService;

    public NamesController(NamesService namesService){
        this.namesService = namesService;
    }
    @PostMapping
    public ResponseEntity<String> addNames(){
        namesService.addNames();
        return ResponseEntity.ok("Success, names added and count set.");
    }
    @PostMapping(path="/count")
    public ResponseEntity<CountData> setCount(){
        return ResponseEntity.ok(namesService.setNamesCount());
    }
}
