package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.repository.LoginRegRepository;
import com.ivlup.udicate.backend.repository.UserProfileRepository;

public class EnterActivity extends AppCompatActivity implements LoginRegRepository.Callback, UserProfileRepository.Callback{
    Button mEnter;
    String email, password;
    TextInputEditText mLogin, mPassword;
    private static final int RC_SIGN_IN = 123;

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

        UserProfileRepository.registerCallBack(this);
        LoginRegRepository.registerCallBack(this);
    }

    private void createTeacherIntent() {
        //Перход на активность
        Intent intent = new Intent(EnterActivity.this, TeacherActivity.class);
        startActivity(intent);
    }

    private void createPersonIntent() {
        //Перход на активность
        Intent intent = new Intent(EnterActivity.this, ParentActivity.class);
        startActivity(intent);
    }

    private void gatherInfo() {
        if (mLogin.getText().toString().length() > 3 && mPassword.getText().toString().length() > 3) {
            email = mLogin.getText().toString();
            password = mPassword.getText().toString();
            LoginRegRepository.emailPasswordLogin(this, FirebaseAuth.getInstance(), email, password);
        }
        else Toast.makeText(this, "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userBack() {
        if (null != LoginRegRepository.getUser()){
            Toast.makeText(this, "Успешная авторизация", Toast.LENGTH_LONG).show();
            Log.i("MyLog", LoginRegRepository.getUser().getUid());
            UserProfileRepository.getUserByUid(LoginRegRepository.getUser().getUid());

        }
        else{
            Toast.makeText(this, "Ошибка во время авторизации", Toast.LENGTH_LONG).show();
        }
//        createSignInIntent();

    }



    @Override
    public void personInfoBack() {
        if (!"".equals(UserProfileRepository.getType())){
            if("teacher".equals(UserProfileRepository.getType())){
                //Открываем профиль учителя
                createTeacherIntent();
            }
            else {
                //Открываем профиль родителя
                createPersonIntent();
            }

        }
        else{
            Toast.makeText(this, "Ошибка во время получения данных", Toast.LENGTH_LONG).show();
        }
    }
}
