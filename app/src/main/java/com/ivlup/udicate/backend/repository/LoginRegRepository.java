package com.ivlup.udicate.backend.repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class LoginRegRepository {
    //Для входа в Firebase
    private static FirebaseAuth mAuth;
    // Пользователь из authentification
    private static FirebaseUser user;

    public static FirebaseUser getUser() {
        return user;
    }

    public static void setUser(FirebaseUser user) {
        LoginRegRepository.user = user;
    }

    public static void setmAuth(FirebaseAuth mAuth) {
        LoginRegRepository.mAuth = mAuth;
    }

    public interface Callback{
        void userBack();
    }

    static Callback callback;


    public static void registerCallBack(Callback callback){
        LoginRegRepository.callback = callback;
    }

    public static boolean checkUser(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i("Test_log", String.valueOf(currentUser.getUid()));
        return true;
    }

    public static void emailPasswordLogin(Activity activity, FirebaseAuth fa, String email, String password){
        setmAuth(fa);
        Log.i("MyLog", email + " : " + password);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("MyTag", "signInWithEmail:success");
                            setUser(mAuth.getCurrentUser());
                            callback.userBack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Auth", "Догин или пароль введены не верно");
                            callback.userBack();
                        }
                    }
                });
    }

    public static void passwordRegistration(Activity activity, FirebaseAuth fa, String email, String password){
        setmAuth(fa);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MyLog", "createUserWithEmail:success");
                            setUser(mAuth.getCurrentUser());
                            callback.userBack();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MyLog", "createUserWithEmail:failure", task.getException());
                        }

                        // ...
                    }
                });
    }

}
