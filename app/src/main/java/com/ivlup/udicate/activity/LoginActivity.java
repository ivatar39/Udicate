package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.repository.FirebaseRepository;

public class LoginActivity extends AppCompatActivity {
    Button mEnter;
    String email, password;
    TextInputEditText mlogin, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);


        mEnter = (Button) (findViewById(R.id.buttonEnter));
        mlogin = (TextInputEditText) (findViewById(R.id.editTextEmailLogin));
        mPassword = (TextInputEditText) (findViewById(R.id.editTextPasswordLogin));

        mEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInfo();
                //createMainIntent();
            }
        });

    }

    private void createMainIntent() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void gatherInfo() {
        //if (mEnter.getText().length() > 3 && mPassword.getText().length() > 3) {
            email = String.valueOf(mlogin.getText());
            password = String.valueOf(mPassword.getText());
            FirebaseRepository.emailPasswordLogin(this, FirebaseAuth.getInstance(), email, password);

      //  else Toast.makeText(this, "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();

    }

}
