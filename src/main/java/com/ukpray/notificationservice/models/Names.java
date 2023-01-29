package com.ukpray.notificationservice.models;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Names {

    @Id
    private String id;
    private List<String> Names = new LinkedList<>();

    public Names() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> Names) {
        Names = Names;
    }
}
