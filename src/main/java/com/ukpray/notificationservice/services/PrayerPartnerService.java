package com.ukpray.notificationservice.services;

import com.mailgun.model.message.MessageResponse;
import com.ukpray.notificationservice.models.CountData;
import com.ukpray.notificationservice.models.PrayerPartner;
import com.ukpray.notificationservice.repositories.CountDataRepository;
import com.ukpray.notificationservice.repositories.NamesRepository;
import com.ukpray.notificationservice.repositories.PrayerPartnerRepository;
import org.springframework.stereotype.Service;


@Service
public class PrayerPartnerService {
    private final EmailService emailService;
    private final NamesService namesService;
    private final NamesRepository namesRepository;
    private final PrayerPartnerRepository prayerPartnerRepository;
    private final CountDataRepository countDataRepository;

    public PrayerPartnerService(EmailService emailService,
                                NamesService namesService,
                                NamesRepository namesRepository,
                                PrayerPartnerRepository prayerPartnerRepository,
                                CountDataRepository countDataRepository){
        this.emailService = emailService;
        this.namesService = namesService;
        this.namesRepository = namesRepository;
        this.prayerPartnerRepository = prayerPartnerRepository;
        this.countDataRepository = countDataRepository;
    }

    public MessageResponse signUpPrayerPartner(PrayerPartner prayerPartner){
        prayerPartner.setNames(namesRepository.findById(
                String.valueOf(countDataRepository.findById("prayerPartnerCount").getCount()%countDataRepository.findById("namesCount").getCount())
        ).getNames());
        prayerPartnerRepository.save(prayerPartner);
        countDataRepository.save(new CountData("prayerPartnerCount", countDataRepository.findById("prayerPartnerCount").getCount() + 1 ));
        return emailService.sendNewPrayerPartnerEmail(prayerPartner);
    }

    public CountData setPrayerPartnerCount() {
        return countDataRepository.save(new CountData("prayerPartnerCount", prayerPartnerRepository.count()));
    }


}
