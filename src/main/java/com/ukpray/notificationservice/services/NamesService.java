package com.ukpray.notificationservice.services;

import com.ukpray.notificationservice.models.Names;
import com.ukpray.notificationservice.repositories.NamesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class NamesService {

    public long namesCount;
    private final NamesRepository namesRepository;

    public NamesService(NamesRepository namesRepository){
        this.namesRepository = namesRepository;
    }

    public void addNames(){
        //TODO: Convert this to read from gcs bucket
        List<String> nameList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/trent_rush/Projects/Personal/UK_Pray/NotificationService/src/main/resources/UKPrayNames.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                nameList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long index = 1;
        while(!nameList.isEmpty()) {
            Names names = new Names();
            names.setId(index);
            for (int i = 0; i < 40; i++) {
                try {
                    names.getNames().add(nameList.remove(0));
                }catch (IndexOutOfBoundsException ignored) {
                }
            }
            if (names.getNames().isEmpty() || names.getNames().size() < 5){
                throw new RuntimeException();
            }
            namesRepository.save(names);
            names.getNames().clear();
            index = index + 1;
        }
        namesCount = namesRepository.count();
    }

}
