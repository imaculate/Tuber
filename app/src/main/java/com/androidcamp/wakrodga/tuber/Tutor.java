package com.androidcamp.wakrodga.tuber;

/**
 * Created by demouser on 8/4/16.
 */
public class Tutor {
    String name;
    String country;
    String city;
    String reputation;
    String image;

    public String getCity() {
        return city;
    }

    public String getImage() {
        return image;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getReputation() {
        return reputation + "/5";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }
}
