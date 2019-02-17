package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.repository.FirebaseRepository;

public class EnterActivity extends AppCompatActivity {
    Button mEnter;
    String email, password;
    TextInputEditText mLogin, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mEnter = (Button) (findViewById(R.id.buttonEnter));
        mLogin = (TextInputEditText) (findViewById(R.id.editTextEmailLogin));
        mPassword = (TextInputEditText) (findViewById(R.id.editTextPasswordLogin));

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInfo();
            }
        });

    }

    private void gatherInfo() {
        if (mEnter.getText().toString().length() > 3 && mPassword.getText().toString().length() > 3) {
            email = mLogin.getText().toString();
            password = mPassword.getText().toString();
            FirebaseRepository.emailPasswordLogin(this, FirebaseAuth.getInstance(), email, password);
        }
        else Toast.makeText(this, "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();
    }

}
