package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ivlup.udicate.R;

public class RegistrationActivity extends AppCompatActivity {
    Button register;
    TextInputEditText mLogin, mName, mEmail, mPassword;
    String login, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        register = (Button) (findViewById(R.id.buttonRegister));
        mLogin = (TextInputEditText) (findViewById(R.id.editTextUserLogin));
        mName = (TextInputEditText) (findViewById(R.id.editTextUserName));
        mEmail = (TextInputEditText) (findViewById(R.id.editTextEmail));
        mPassword = (TextInputEditText) (findViewById(R.id.editTextPassword));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInfo();
                createSubjectsIntent();
            }
        });

    }

    private void createSubjectsIntent() {
        Intent intent = new Intent(RegistrationActivity.this, SubjectsActivity.class);
        startActivity(intent);
    }

    private void gatherInfo() {
        if (mEmail.getText().length() > 3 && mPassword.getText().length() > 3) {
            email = String.valueOf(mEmail.getText());
            password = String.valueOf(mPassword.getText());
            login = String.valueOf(mLogin.getText());
            name = String.valueOf(mName.getText());
        }
        else {
            Toast.makeText(this, "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();
        }
    }
}
