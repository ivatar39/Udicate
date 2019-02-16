package com.ivlup.udicate.backend.users;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Teacher {
    public String      email;
    public String      id;
    public String      name;
    public String      surname;
    public String      avatar;
    public String      phone;
    public String      info;
    public List<String> organizations;
    //private List<Object> organizations;

    public Teacher(){}

    public Teacher(String email, String id, String name, String surname, String avatar, String phone, List<String> organizations, String info) {
        this.email = email;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.phone = phone;
        this.organizations = organizations;
        this.info = info;
    }
}
