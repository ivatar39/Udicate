package com.ivlup.udicate.backend.objects;

public class Subject {
    private String  name;
    private Boolean checked;
    private int  image;

    public Subject() {
    }

    public Subject(String name, Boolean checked, int image) {
        this.name = name;
        this.checked = checked;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
