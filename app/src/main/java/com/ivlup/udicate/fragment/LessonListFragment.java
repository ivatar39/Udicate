package com.ivlup.udicate.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.binding.LessonItem;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.objects.Lesson;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;

import java.util.ArrayList;

public class LessonListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lesson_list_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.tasks_recycler);
        mRecyclerView.setHasFixedSize(true);



        //mLayoutManager = new LinearLayoutManager(getContext());
        //mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //mAdapter = new MyAdapter(myDataset);
        GroupAdapter adapter = new GroupAdapter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter.clear();

        ArrayList<Item> items = new ArrayList<>();


        for (Lesson less : Temp.lessons.values()) {
            items.add(new LessonItem(less));
        }
        adapter.addAll(items);
    }

    public boolean checkStatus() {
        if (Temp.lessons.get(Temp.t_lesson_id).status.equals("1")) return true;
        return false;
    }


}
