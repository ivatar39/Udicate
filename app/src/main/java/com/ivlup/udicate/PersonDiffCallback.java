package com.ivlup.udicate;

import android.support.v7.util.DiffUtil;

import com.ivlup.udicate.backend.objects.Person;

import java.util.List;

public class PersonDiffCallback extends DiffUtil.Callback {

    private final List<Person> oldList;
    private final List<Person> newList;

    public PersonDiffCallback(List<Person> oldList, List<Person> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition).id == newList.get(newPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        Person oldPerson = oldList.get(oldPosition);
        Person newPerson = newList.get(newPosition);
        return oldPerson.name.equals(newPerson.name)
                && oldPerson.city.equals(newPerson.city)
                && oldPerson.url.equals(newPerson.url);
    }

}
