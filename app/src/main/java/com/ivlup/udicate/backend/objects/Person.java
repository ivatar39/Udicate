package com.ivlup.udicate.backend.objects;

public class Person {
    private static int counter = 0;

    public long id;
    public String name;
    public String city;
    public String url;

    public Person(String name, String city, String url) {
        this.id = counter++;
        this.name = name;
        this.city = city;
        this.url = url;
    }
}
