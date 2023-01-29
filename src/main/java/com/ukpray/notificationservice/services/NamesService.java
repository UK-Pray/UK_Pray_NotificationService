package com.ukpray.notificationservice.services;

import com.ukpray.notificationservice.models.CountData;
import com.ukpray.notificationservice.models.Names;
import com.ukpray.notificationservice.repositories.CountDataRepository;
import com.ukpray.notificationservice.repositories.NamesRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class NamesService {
    private final NamesRepository namesRepository;
    private final CountDataRepository countDataRepository;

    public NamesService(NamesRepository namesRepository, CountDataRepository countDataRepository){
        this.namesRepository = namesRepository;
        this.countDataRepository = countDataRepository;
    }


    public void addNames(){
        //TODO: Convert this to read from gcs bucket
        //Read data from provided file
        List<String> nameList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/UKPrayNames.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                nameList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Get filler names if last collection is <40
        List<String> fillerNames = new ArrayList<>();
        for ( int i = 0; i < 40; i++) {
            fillerNames.add(nameList.get(i));
        }

        long index = 0;
        while(!nameList.isEmpty()) {
            //Add data to individual list of 40 names
            Names names = new Names();
            names.setId(String.valueOf(index));
            for (int i = 0; i < 40; i++) {
                //If namesList is empty for last collection of Names
                //then it resorts to filler names
                try {
                    names.getNames().add(nameList.remove(0));
                }catch (IndexOutOfBoundsException ignored) {
                    names.getNames().add(fillerNames.remove(0));
                }
            }
            //Upload list to Datastore
            namesRepository.save(names);
            names.getNames().clear();
            index++;
        }
        //Update CountData with number of Names collections
        countDataRepository.save(new CountData("namesCount", index));
    }

    public CountData setNamesCount(){ //2023 count: 1396
        return countDataRepository.save(new CountData("namesCount", namesRepository.count()));
    }

}
