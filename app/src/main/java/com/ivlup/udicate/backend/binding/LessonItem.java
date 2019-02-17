package com.ivlup.udicate.backend.binding;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.objects.Lesson;
import com.ivlup.udicate.databinding.LessonItemBinding;
import com.ivlup.udicate.fragment.teacher.LessonCardFragment;
import com.xwray.groupie.databinding.BindableItem;

public class LessonItem extends BindableItem<LessonItemBinding>{

    private Lesson lesson;

    public LessonItem(com.ivlup.udicate.backend.objects.Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public void bind(@NonNull final LessonItemBinding viewBinding, int position) {
        if (lesson.status.equals("1")) viewBinding.cardView.setCardBackgroundColor(viewBinding.cardView.getContext().getResources().getColor(R.color.colorAccept));
        viewBinding.textTaskName. setText(lesson.name);
        viewBinding.textTaskTime. setText(lesson.date.get("start"));
        viewBinding.textTaskPlace.setText(lesson.place);

        viewBinding.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temp.t_lesson_id = lesson.id;
                Log.i("MyLogClickId",Temp.t_lesson_id);
                Fragment mTaskFragment = new LessonCardFragment();
                replaceFragment(mTaskFragment, v); }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.lesson_item;
    }



    public void replaceFragment(Fragment fragment, View view) {
        FragmentTransaction ft = ((AppCompatActivity) view.getContext()).getSupportFragmentManager()
                .beginTransaction() ;

        ft.addToBackStack(null);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
