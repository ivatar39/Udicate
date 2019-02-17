package com.ivlup.udicate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.binding.SubjectItem;
import com.ivlup.udicate.backend.objects.Subject;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class SubjectsActivity extends AppCompatActivity {
    ArrayList<Subject> subjectList;
    RecyclerView mRecyclerView;
    Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_subjects);
        mRecyclerView = (RecyclerView) (findViewById(R.id.recylcler_subjects));

        fetchSubjects();
        setAdapter();
    }


    private void fetchSubjects() {
        subjectList = new ArrayList<>();
        subjectList.clear();

        subject =new Subject("Математика", false, R.drawable.division);
        subjectList.add(subject);
        subject =new Subject("Спорт", false, R.drawable.football);
        subjectList.add(subject);
        subject =new Subject("Языки", false, R.drawable.language);
        subjectList.add(subject);
        subject =new Subject("ИТ", false, R.drawable.chip);
        subjectList.add(subject);
        subject =new Subject("Физика", false, R.drawable.atomic);
        subjectList.add(subject);

    }


    private void setAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        GroupAdapter adapter = new GroupAdapter();
        adapter.clear();
        ArrayList<Item> items = new ArrayList<>();

        for (Subject subject : subjectList) {
            items.add(new SubjectItem(subject));
        }
        adapter.addAll(items);
        mRecyclerView.setAdapter(adapter);
    }
}
