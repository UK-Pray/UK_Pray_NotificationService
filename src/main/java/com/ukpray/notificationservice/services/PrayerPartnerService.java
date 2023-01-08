package com.ukpray.notificationservice.services;

import com.google.api.services.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.StorageOptions;
import com.mailgun.model.message.MessageResponse;
import com.ukpray.notificationservice.models.PrayerPartner;
import com.ukpray.notificationservice.repositories.NamesRepository;
import com.ukpray.notificationservice.repositories.PrayerPartnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrayerPartnerService {

    private static long counter = 0;
    private final EmailService emailService;
    private final NamesService namesService;
    private final NamesRepository namesRepository;
    private final PrayerPartnerRepository prayerPartnerRepository;

    public PrayerPartnerService(EmailService emailService, NamesService namesService, NamesRepository namesRepository, PrayerPartnerRepository prayerPartnerRepository){
        this.emailService = emailService;
        this.namesService = namesService;
        this.namesRepository = namesRepository;
        this.prayerPartnerRepository = prayerPartnerRepository;
    }

    public MessageResponse signUpPrayerPartner(PrayerPartner prayerPartner){
        prayerPartner.setNames(namesRepository.findById(
                counter%namesService.namesCount != 0 ? counter+1 : counter+2
        ).get().getNames());
        prayerPartnerRepository.save(prayerPartner);
        return emailService.sendNewPrayerPartnerEmail(prayerPartner);
    }


}
