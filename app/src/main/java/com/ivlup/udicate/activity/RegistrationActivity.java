package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ivlup.udicate.R;

public class RegistrationActivity extends AppCompatActivity {
    Button register;
    TextInputEditText login, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        register = (Button) (findViewById(R.id.buttonRegister));
        login = (TextInputEditText) (findViewById(R.id.editTextUserLogin));
        name = (TextInputEditText) (findViewById(R.id.editTextUserName));
        email = (TextInputEditText) (findViewById(R.id.editTextEmail));
        password = (TextInputEditText) (findViewById(R.id.editTextPassword));

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

    private void gatherInfo() {

    }
}
