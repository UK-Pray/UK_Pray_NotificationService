package com.ukpray.notificationservice.controllers;

import com.mailgun.model.message.MessageResponse;
import com.ukpray.notificationservice.models.PrayerPartner;
import com.ukpray.notificationservice.services.PrayerPartnerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmailController {

    private final PrayerPartnerService prayerPartnerService;

    public EmailController(PrayerPartnerService prayerPartnerService){
        this.prayerPartnerService = prayerPartnerService;
    }
    @PostMapping(path = "/prayer-partner", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> createPrayerPartner(@RequestBody PrayerPartner prayerPartner){
        return ResponseEntity.ok(prayerPartnerService.signUpPrayerPartner(prayerPartner));
    }
}
