package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ivlup.udicate.R;

import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    Button mRegister;
    TextInputEditText mLogin, mName, mEmail, mPassword;
    String login, name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mRegister   = (Button)            (findViewById(R.id.bt_reg));
        mLogin      = (TextInputEditText) (findViewById(R.id.et_reg_login));
        mName       = (TextInputEditText) (findViewById(R.id.et_reg_name));
        mEmail      = (TextInputEditText) (findViewById(R.id.et_reg_email));
        mPassword   = (TextInputEditText) (findViewById(R.id.et_reg_password));

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInfo()) createSubjectsIntent();
                  else  Toast.makeText(getApplicationContext(), "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createSubjectsIntent() {
        Intent intent = new Intent(RegistrationActivity.this, SubjectsActivity.class);
        startActivity(intent);
    }

    private void gatherInfo() {
        email    = mEmail.getText().toString();
        password = mPassword.getText().toString();
        login    = mLogin.getText().toString();
        name     = mName.getText().toString();
    }

    private boolean checkInfo() {
        if (

                mEmail.getText().toString().length() > 3   && mPassword.getText().toString().length() > 3 && mLogin.getText().toString().length() > 3 && mName.getText().toString().length() > 3) {
            gatherInfo();
            return true;
        }
        return false;
    }

}
