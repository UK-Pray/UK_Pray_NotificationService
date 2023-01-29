package com.ukpray.notificationservice.models;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class CountData {

    @Id
    private String id;
    private Long count;

    public CountData(String id, Long count) {
        this.id = id;
        this.count = count;
    }
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
