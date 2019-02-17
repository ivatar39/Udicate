package com.ivlup.udicate.backend.objects;

public class Organization {
    private String name;
    private String address;
    private String logo;
    private int[] type;

    public Organization() {
    }

    public Organization(String name, String address, String logo, int[] type) {
        this.name = name;
        this.address = address;
        this.logo = logo;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int[] getType() {
        return type;
    }

    public void setType(int[] type) {
        this.type = type;
    }
}
