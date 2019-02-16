package com.ivlup.udicate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ivlup.udicate.PersonItem;
import com.ivlup.udicate.R;
import com.ivlup.udicate.activity.SwipeActivity;
import com.ivlup.udicate.backend.DB;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.users.Person;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class LessonCardFragment extends Fragment {
    public static GroupAdapter adapter;
    private RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lesson_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("FragmentDeb", "created");
        mRecyclerView = view.findViewById(R.id.hamlet_student_list);
        mRecyclerView.setHasFixedSize(true);

        adapter = new GroupAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        final EditText comment = (EditText) view.findViewById(R.id.et_comment);


        ImageButton btSave = (ImageButton) view.findViewById(R.id.bt_submit_task);
        TextView mName = (TextView) view.findViewById(R.id.single_text_task_name);

        mName.setText(Temp.lessons.get(Temp.t_lesson_id).name);

        TextView mTime = (TextView) view.findViewById(R.id.single_text_task_time);
        mTime.setText(Temp.lessons.get(Temp.t_lesson_id).date.get("start"));

        TextView mPlace = (TextView) view.findViewById(R.id.single_text_task_place);
        mPlace.setText(Temp.lessons.get(Temp.t_lesson_id).place);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DB().updateDB(String.valueOf(comment.getText()));
                Temp.lessons.get(Temp.t_lesson_id).status = "1";
                Toast.makeText(getContext(), "Проведено", Toast.LENGTH_LONG).show();
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        adapter.clear();
        ImageButton mBtTinder = (ImageButton) view.findViewById(R.id.bt_tinder);
        mBtTinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getBaseContext(),
                        SwipeActivity.class);    //TINDER activity

                getActivity().startActivity(i);
            }
        });

        ArrayList<Item> items = new ArrayList<>();

        for (Person person: Temp.lessons_students.get(Temp.t_lesson_id)) {
            items.add(new PersonItem(person));
        }
        adapter.addAll(items);
        items.clear();
    }


}
