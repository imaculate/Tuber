package com.androidcamp.wakrodga.tuber;

import java.util.Map;

/**
 * Created by demouser on 8/4/16.
 */
public class Student {
    public String image;
    public Map<String,String> likedTutors;
    public Map<String,String> myTutors;
    public String name;
    public Map<String,String> pendingTutors;

    public Student() {

    }

    public Student(String image, Map<String, String> likedTutors, Map<String, String> myTutors, String name, Map<String, String> pendingTutors) {
        this.image = image;
        this.likedTutors = likedTutors;
        this.myTutors = myTutors;
        this.name = name;
        this.pendingTutors = pendingTutors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, String> getLikedTutors() {
        return likedTutors;
    }

    public void setLikedTutors(Map<String, String> likedTutors) {
        this.likedTutors = likedTutors;
    }

    public Map<String, String> getMyTutors() {
        return myTutors;
    }

    public void setMyTutors(Map<String, String> myTutors) {
        this.myTutors = myTutors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getPendingTutors() {
        return pendingTutors;
    }

    public void setPendingTutors(Map<String, String> pendingTutors) {
        this.pendingTutors = pendingTutors;
    }
}
