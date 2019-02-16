package com.ivlup.udicate;

import android.support.annotation.NonNull;
import android.view.View;

import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.education.Lesson;
import com.ivlup.udicate.backend.users.Person;
import com.ivlup.udicate.databinding.PersonItemBinding;
import com.ivlup.udicate.databinding.PersonItemBinding;
import com.xwray.groupie.databinding.BindableItem;

public class PersonItem extends BindableItem<PersonItemBinding> {

    private Person person;
    public PersonItem(Person person) {
        this.person = person;
    }

    @Override
    public void bind(@NonNull final PersonItemBinding viewBinding, int position) {
        viewBinding.personName.setText(person.name + " "+ person.surname);

        Lesson l = Temp.lessons.get(Temp.t_lesson_id);

        if (l.group.get(person.id)) {
            viewBinding.personCheckBox.toggle();
        }


        viewBinding.personCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBinding.personCheckBox.toggle();
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.person_item;
    }
}
