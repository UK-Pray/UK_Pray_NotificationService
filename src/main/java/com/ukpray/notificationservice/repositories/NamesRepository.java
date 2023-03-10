package com.ukpray.notificationservice.repositories;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.ukpray.notificationservice.models.Names;
import org.springframework.stereotype.Repository;

@Repository
public interface NamesRepository extends DatastoreRepository<Names, Long> {
    Names findById(String id);
}
