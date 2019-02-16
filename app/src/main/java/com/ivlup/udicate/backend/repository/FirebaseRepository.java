package com.ivlup.udicate.backend.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public abstract class FirebaseRepository {
    private static FirebaseAuth mAuth;
    @SuppressLint("StaticFieldLeak")
    private static GoogleSignInClient mGoogleSignInClient;

    public static void setmGoogleSignInClient(GoogleSignInClient mGoogleSignInClient) {
        FirebaseRepository.mGoogleSignInClient = mGoogleSignInClient;
    }

    boolean checkUser(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i("Test_log", String.valueOf(currentUser));
        return true;
    }

    void emailPasswordLogin(FirebaseAuth fa, String email, String password){
        setmAuth(fa);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MyTag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.i("Auth", "Всё окич )");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Auth", "Догин или пароль введены не верно");
                        }
                    }
                });
    }
    void configueGoogleSign(Context context){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        setmGoogleSignInClient(GoogleSignIn.getClient(context, gso));
    }
    void googleLogin(Context context){

    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
}
