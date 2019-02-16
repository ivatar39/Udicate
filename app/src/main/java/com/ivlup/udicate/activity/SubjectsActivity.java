package com.ivlup.udicate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.binding.LessonItem;
import com.ivlup.udicate.backend.binding.SubjectItem;
import com.ivlup.udicate.backend.education.Lesson;
import com.ivlup.udicate.backend.education.Subject;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {
    ArrayList<Subject> subjectList;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mRecyclerView = (RecyclerView) (findViewById(R.id.recylcler_subjects));
        GroupAdapter adapter = new GroupAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.clear();

        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {
            Subject subject =new Subject("Хуематика", false, "https://www.pic.com.kw/slider2.png");
            subjectList.add(subject);
        }

        for (Subject subject : subjectList) {
            items.add(new SubjectItem(subject));
        }
        adapter.addAll(items);
    }

}
