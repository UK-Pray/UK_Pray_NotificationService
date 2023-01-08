package com.ukpray.notificationservice.repositories;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import com.ukpray.notificationservice.models.PrayerPartner;
import org.springframework.stereotype.Repository;

@Repository
public interface PrayerPartnerRepository extends DatastoreRepository<PrayerPartner, Long> {
}
