package com.ivlup.udicate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ivlup.udicate.R;
import com.ivlup.udicate.fragment.OrganizationsFragment;
import com.ivlup.udicate.fragment.ProfileFragment;
import com.ivlup.udicate.fragment.LessonListFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private static final String SELECTED_ITEM = "arg_selected_item";
    private int mSelectedItem;
    Fragment mChat, mProfile, mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNav = (BottomNavigationView) (findViewById(R.id.navigation));
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 2);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(2);
        }
        selectFragment(selectedItem);

    }
   @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(2);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }

    private void selectFragment(MenuItem item) {

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_tasks:
                mTasks = new LessonListFragment();
                fragmentTransaction.replace(R.id.container, mTasks);
                fragmentTransaction.commit();
                break;
            case R.id.menu_chat:
                mChat = new OrganizationsFragment();
                fragmentTransaction.replace(R.id.container, mChat);
                fragmentTransaction.commit();
                break;
            case R.id.menu_profile:
                mProfile = new ProfileFragment();
                fragmentTransaction.replace(R.id.container, mProfile);
                fragmentTransaction.commit();
                break;
        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        /*if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }*/
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(MainActivity.this, FirebaseUIActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        // [END auth_fui_signout]
    }

   /* private void fetchTasks() {
        for (Lesson less : Temp.lessons) {

            arrayObjectLessons.add(less);
        }
        for (int i = 0; i < 20; i++) {
            Person p = new Person();
            p.name = "Иван " + i;
            arrayObjectPersons.add(p);
        }
    }*/


}
