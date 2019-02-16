package com.ivlup.udicate.backend.users;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Person {
    public boolean     child;
    public boolean     parent;
    public String      email;
    public String      id;
    public String      name;
    public String      surname;
    public String      avatar;
    public String      phone;

    public Person() {}

    public Person(boolean child, boolean parent, String email, String id, String name, String surname, String avatar, String phone) {
        this.child = child;
        this.parent = parent;
        this.email = email;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.phone = phone;
    }

}
