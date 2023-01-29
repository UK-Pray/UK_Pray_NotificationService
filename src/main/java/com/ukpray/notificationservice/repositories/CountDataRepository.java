package com.ukpray.notificationservice.repositories;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.ukpray.notificationservice.models.CountData;
import org.springframework.stereotype.Repository;

@Repository
public interface CountDataRepository extends DatastoreRepository<CountData, Long> {
    CountData findById(String id);
}
