package com.ivlup.udicate.backend.education;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Map;

@IgnoreExtraProperties
public class Lesson {
    public Map<String, String> date;
    public Map<String, Boolean> group;
    public String id;
    public String name;
    public String organization;
    public String place;
    public String status;
    public ArrayList<String> teachers;

    public Lesson(){}

    public Lesson(Map<String, String> date, Map<String, Boolean> group, String id, String name, String organization, String place, String status, ArrayList<String> teachers) {
        this.date = date;
        this.group = group;
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.place = place;
        this.status = status;
        this.teachers = teachers;
    }
}
