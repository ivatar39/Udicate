package com.ivlup.udicate.activity;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ivlup.udicate.R;
import com.ivlup.udicate.fragment.teacher.LessonListFragment;
import com.ivlup.udicate.fragment.parent.OrganizationsFragment;
import com.ivlup.udicate.fragment.teacher.ProfileFragment;

public class TeacherActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private static final String SELECTED_ITEM = "arg_selected_item";
    private int mSelectedItem;
    private Fragment Organization, mProfile, mLessons;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mBottomNav = (BottomNavigationView) (findViewById(R.id.navigation));
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exit:
                return true;
                default: return super.onOptionsItemSelected(item);
        }
    }
    private void selectFragment(MenuItem item) {

        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_lessons:
                mLessons = new LessonListFragment();
                fragmentTransaction.replace(R.id.container, mLessons);
                fragmentTransaction.commit();
                break;
            case R.id.menu_organization:
                Organization = new OrganizationsFragment();
                fragmentTransaction.replace(R.id.container, Organization);
                fragmentTransaction.commit();
                break;
            case R.id.menu_teacher_profile:
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

        if (myToolbar != null) {
            myToolbar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }


}
