package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ivlup.udicate.R;

public class RegistrationActivity extends AppCompatActivity {
    Fragment registration, choose;
    FragmentTransaction fTrans;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        register = (Button) (findViewById(R.id.buttonRegister));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMainIntent();
            }
        });

    }

    private void createMainIntent() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
