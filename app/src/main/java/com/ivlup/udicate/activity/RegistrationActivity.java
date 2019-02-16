package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ivlup.udicate.R;
import com.ivlup.udicate.fragment.RegStageFirstFragment;
import com.ivlup.udicate.fragment.RegStageSecondFragment;

public class RegistrationActivity extends AppCompatActivity {
    Fragment registration, choose;
    FragmentTransaction fTrans;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        choose = new RegStageFirstFragment();
        fragmentTransaction.replace(R.id.frame, choose);
        fragmentTransaction.commit();

        submit = (Button) (findViewById(R.id.bt_submit));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RegStageFirstFragment.getSelected() == -1) {
                    registration = new RegStageSecondFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.frame, registration);
                    fragmentTransaction1.commit();
                }

                if (RegStageSecondFragment.registrationCompleted()) {
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
