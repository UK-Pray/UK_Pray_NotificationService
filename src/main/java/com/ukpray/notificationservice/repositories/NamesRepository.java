package com.ukpray.notificationservice.repositories;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.google.cloud.spring.data.datastore.repository.query.Query;
import com.ukpray.notificationservice.models.Names;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamesRepository extends DatastoreRepository<Names, Long> {
}
