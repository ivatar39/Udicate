package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.DB;
import com.ivlup.udicate.backend.Temp;

public class FirebaseUIActivity extends AppCompatActivity implements DB.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //new DB().getLessons();

        Button mBtEnter = (Button) (findViewById(R.id.bt_enter));
        Button mBtReg = (Button) (findViewById(R.id.bt_reg));

        mBtEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createEnterIntent();
            }
        });

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

    private void createEnterIntent() {
        Intent intent = new Intent(FirebaseUIActivity.this, EnterActivity.class);
        startActivity(intent);
    }


   /* public void createSignInIntent() {

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



    public void CheckUserStatus(String key) {

       // 1 = Ученик / Родитель
       // 2 = Преподаватель
       // 3 = Нет в системе

        Log.i("MyLog", key);
        DB db = new DB();
        db.registerCallBack(this);
        db.getPersonById(key);
        db.getTeacherById(key);
    }
   */

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
