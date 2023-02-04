package com.ukpray.notificationservice.controllers;

import com.mailgun.model.message.MessageResponse;
import com.ukpray.notificationservice.models.CountData;
import com.ukpray.notificationservice.models.PrayerPartner;
import com.ukpray.notificationservice.services.PrayerPartnerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/prayer-partner")
public class PrayerPartnerController {

    private final PrayerPartnerService prayerPartnerService;

    public PrayerPartnerController(PrayerPartnerService prayerPartnerService){
        this.prayerPartnerService = prayerPartnerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> create(@RequestBody PrayerPartner prayerPartner, HttpServletResponse httpServletResponse){
        return ResponseEntity.ok(prayerPartnerService.signUpPrayerPartner(prayerPartner));
    }

    @PostMapping(path="/count")
    public ResponseEntity<CountData> setCount(){
        return ResponseEntity.ok(prayerPartnerService.setPrayerPartnerCount());
    }
}
