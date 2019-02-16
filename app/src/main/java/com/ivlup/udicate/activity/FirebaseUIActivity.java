package com.ivlup.udicate.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.DB;
import com.ivlup.udicate.backend.Temp;

import java.util.Arrays;
import java.util.List;

public class FirebaseUIActivity extends AppCompatActivity implements DB.Callback {

    private static final int RC_SIGN_IN = 123;
    public static final int STUDENT_PARENT = 1;
    public static final int TEACHER = 2;
    public static final int OFFLINE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new DB().getLessons();
        ActionBar bar = getActionBar();
        //bar.setBackgroundDrawable(new ColorDrawable("COLOR"));

        Button mBtEnter = (Button) (findViewById(R.id.bt_enter));
        Button mBtReg = (Button) (findViewById(R.id.bt_reg));

        mBtEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createSignInIntent();
            }
        });
        createSignInIntent();

        mBtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRegIntent();
            }
        });
    }

    private void createRegIntent() {
        Intent intent = new Intent(FirebaseUIActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
                );

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);

        // [END auth_fui_create_intent]
    }

    // [START auth_fui_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                Temp.user = FirebaseAuth.getInstance().getCurrentUser();

                CheckUserStatus(Temp.user.getUid());
                // ...
            } else {

            }
        }
    }
    // [END auth_fui_result]



    public void delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_delete]
    }

  /*  public void themeAndLogo() {
        List<AuthUI.IdpConfig> providers = Collections.emptyList();

        // [START auth_fui_theme_logo]
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.my_great_logo)      // Set logo drawable
                        .setTheme(R.style.MySuperAppTheme)      // Set theme
                        .build(),
                RC_SIGN_IN);
        // [END auth_fui_theme_logo]
    }
*/


    public void CheckUserStatus(String key) {
        /*
        1 = Ученик / Родитель
        2 = Преподаватель
        3 = Нет в системе
         */
        Log.i("MyLog", key);
        DB db = new DB();
        db.registerCallBack(this);
        db.getPersonById(key);
        db.getTeacherById(key);
    }

    @Override
    public void callingBack(){
        if(Temp.start == 2){
            if (Temp.person != null) {
                Temp.who = 1;
                Intent intent = new Intent(FirebaseUIActivity.this, MainActivity.class);
                startActivity(intent);
            }

            else if(Temp.teacher != null){
                Temp.who = 2;
                Log.i("MyLog", String.valueOf(Temp.who));
                Log.i("MyLog", "Go teach");
                Intent intent = new Intent(FirebaseUIActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Temp.who = 3;
                Log.i("registration", "registered");
                //registration
                Intent intent = new Intent(FirebaseUIActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        }

    }

}
