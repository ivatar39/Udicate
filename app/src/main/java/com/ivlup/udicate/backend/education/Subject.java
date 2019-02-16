package com.ivlup.udicate.backend.education;

import android.graphics.Bitmap;

public class Subject {
    private String  name;
    private Boolean checked;
    private String  image;

    public Subject() {
    }

    public Subject(String name, Boolean checked, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
