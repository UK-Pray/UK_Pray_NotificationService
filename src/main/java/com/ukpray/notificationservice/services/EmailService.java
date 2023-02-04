package com.ukpray.notificationservice.services;

import com.mailgun.client.MailgunClient;
import com.ukpray.notificationservice.models.PrayerPartner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EmailService {

    private final Environment environment;

    public EmailService(Environment environment) {
        this.environment = environment;
    }

    public MessageResponse sendNewPrayerPartnerEmail(PrayerPartner prayerPartner) {
        MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(environment.getRequiredProperty("secrets.mailgun-api-key"))
                .createApi(MailgunMessagesApi.class);

        List<String> senders = new ArrayList<>();
        senders.add("Brady");
        senders.add("Trent");
        senders.add("Austin");
        senders.add("Cole");
        senders.add("Hunter");
        senders.add("Jake");

        String sender = senders.get(new Random().nextInt(senders.size()));
        //TODO: Current limit is ~25 emails if sending subsequently
        Message message = Message.builder()
                .from("UK Pray " + sender + "@ukpray.com")
                .to(prayerPartner.getEmail())
                .subject("Welcome - your names")
                .html(createSignUpEmail(prayerPartner, sender))
                .build();
        return mailgunMessagesApi.sendMessage("ukpray.com", message);
    }

    private String createSignUpEmail(PrayerPartner prayerPartner, String sender) {
        try {
            InputStream is = getClass().getResourceAsStream("/signup-email.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            StringBuilder namesString1 = new StringBuilder();
            StringBuilder namesString2 = new StringBuilder();
            for(int i = 0; i < Objects.requireNonNull(prayerPartner.getNames()).size(); i++){
                if (i < 20){
                    namesString1.append(prayerPartner.getNames().get(i)).append("<br>");
                }else{
                    namesString2.append(prayerPartner.getNames().get(i)).append("<br>");
                }
            }
            content = content.replace("$greeting", prayerPartner.getFirstName());
            content = content.replace("$names1", namesString1.toString());
            content = content.replace("$names2", namesString2.toString());
            content = content.replace("$sender", sender);
            return content;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
