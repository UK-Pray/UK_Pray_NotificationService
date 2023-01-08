package com.ukpray.notificationservice.models;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;

@Entity
public class PrayerPartner {

    @Id
    private Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String church;

    private String city;

    private String state;

    @Nullable
    private List<String> names = new LinkedList<>();

    public PrayerPartner(String firstName, String lastName, String email, String church, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.church = church;
        this.city = city;
        this.state = state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChurch() {
        return church;
    }

    public void setChurch(String church) {
        this.church = church;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Nullable
    public List<String> getNames() {
        return names;
    }

    public void setNames(@Nullable List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "PrayerPartner{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", church='" + church + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", Names=" + names +
                '}';
    }
}
