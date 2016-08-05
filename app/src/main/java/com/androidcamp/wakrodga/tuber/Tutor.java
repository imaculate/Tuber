package com.androidcamp.wakrodga.tuber;

import java.util.List;
import java.util.Map;

/**
 * Created by demouser on 8/4/16.
 */
public class Tutor {

    public String city;
    public String country;
    public Boolean frontal = false;
    public Map<String,String> languages;
    public String name;
    public Boolean online = false;
    public Long price;
    public String image;
    public Double reputation;
    public Map<String,String> subjects;

    public Tutor() {

    }

    public Tutor(String city, String country, Boolean frontal, Map<String, String> languages, String name, Boolean online, Long price, String image, Double reputation, Map<String, String> subjects) {
        this.city = city;
        this.country = country;
        this.frontal = frontal;
        this.languages = languages;
        this.name = name;
        this.online = online;
        this.price = price;
        this.image = image;
        this.reputation = reputation;
        this.subjects = subjects;
    }

    public String getCity() {
        return city;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getFrontal() {
        return frontal;
    }

    public void setFrontal(Boolean frontal) {
        this.frontal = frontal;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }




    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

    public Map<String, String> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<String, String> subjects) {
        this.subjects = subjects;
    }
}
