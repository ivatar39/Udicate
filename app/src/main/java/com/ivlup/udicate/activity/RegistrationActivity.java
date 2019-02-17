package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.repository.LoginRegRepository;
import com.ivlup.udicate.backend.repository.UserProfileRepository;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements LoginRegRepository.Callback, UserProfileRepository.Callback{
    Button mRegister;
    RadioButton mParent, mTeacher;
    TextInputEditText mName, mEmail, mPassword;
    String login, name, email, password;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mRegister   = (Button)            (findViewById(R.id.bt_reg));
        mName       = (TextInputEditText) (findViewById(R.id.et_reg_name));
        mEmail      = (TextInputEditText) (findViewById(R.id.et_reg_email));
        mPassword   = (TextInputEditText) (findViewById(R.id.et_reg_password));
        mParent     = (RadioButton)       (findViewById(R.id.rbt_parent));
        mTeacher    = (RadioButton)       (findViewById(R.id.rbt_parent));

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInfo()) {
                    //Чистим
                    gatherInfo();
                    //Регаем
                    LoginRegRepository.passwordRegistration(RegistrationActivity.this, FirebaseAuth.getInstance(), email, password);
                }
                else
                    Toast.makeText(getApplicationContext(), "Не все поля заполнены!" , Toast.LENGTH_SHORT).show();
            }
        });
        LoginRegRepository.registerCallBack(this);
        UserProfileRepository.registerCallBack(this);

    }

    private void createSubjectsIntent() {
        //Перход на активность
        Intent intent = new Intent(RegistrationActivity.this, SubjectsActivity.class);
        startActivity(intent);
    }

    private void gatherInfo() {
        // Перевод к нужному виду
        email    = mEmail.getText().toString();
        password = mPassword.getText().toString();
        name     = mName.getText().toString();
    }

    private boolean checkInfo() {
        // Валидация данных
        return mEmail.getText().toString().length() > 3 && mPassword.getText().toString().length() > 3 && mName.getText().toString().length() > 3;
    }

    @Override
    public void userBack() {
        if (null != LoginRegRepository.getUser()) {
            String uid = LoginRegRepository.getUser().getUid();
            if (mParent.isActivated()) {
                //Создаём профиль родителя/ученика
                Map<String, Object> user_update = new HashMap<>();

                Map<String, Object> user_info = new HashMap<>();
                user_info.put("email", email);
                user_info.put("id", uid);
                user_info.put("avatar", "gs://udicate.appspot.com/imgs/avatars/1.png");
                user_info.put("child", false);
                user_info.put("parrent", false);
                user_info.put("phone", "+79999999999");
                user_info.put("Surname", "");

                user_update.put(uid, user_info);

                UserProfileRepository.createPersonByUid(user_update);
            } else {
                //Создаём профиль учителя
                Map<String, Object> user_update = new HashMap<>();

                Map<String, Object> user_info = new HashMap<>();
                user_info.put("email", email);
                user_info.put("id", uid);
                user_info.put("avatar", "gs://udicate.appspot.com/imgs/avatars/1.png");
                user_info.put("child", false);
                user_info.put("parrent", false);
                user_info.put("phone", "+79999999999");
                user_info.put("Surname", "");

                user_update.put(uid, user_info);

                UserProfileRepository.createTeacherByUid(user_update);
            }
        }
        else {
            Toast.makeText(this, "Ошибка во время создания профиля", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void personInfoBack() {
        if (!"".equals(UserProfileRepository.getType())){
            if("teacher".equals(UserProfileRepository.getType())){
                //Открываем профиль учителя
                createSubjectsIntent();
            }
            else {
                //Открываем профиль родителя
                createSubjectsIntent();
            }

        }
        else{
            Toast.makeText(this, "Ошибка во время получения данных", Toast.LENGTH_LONG).show();
        }
    }

}
