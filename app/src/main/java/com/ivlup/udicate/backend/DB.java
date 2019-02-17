package com.ivlup.udicate.backend;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ivlup.udicate.backend.objects.Lesson;
import com.ivlup.udicate.backend.users.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DB {

    public interface Callback{
        void callingBack();
    }

    Callback callback;


    public void registerCallBack(Callback callback){
        this.callback = callback;
    }



    DatabaseReference mDatabase;
    public DB() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }


    public void getPersonById(String id) {
        mDatabase.child("users").child("persons").child(id).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Temp.person = dataSnapshot.getValue(Person.class);
                Temp.start += 1;
                callback.callingBack();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("MyLog", "Erorrrrrrrrrrrrrrrrrrrrrrrr");
            }
        });
    }

    public void getLessons() {
        mDatabase.child("education").child("lessons").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data_lessons = dataSnapshot.getChildren();
                for (DataSnapshot les : data_lessons) {
                    Lesson l = les.getValue(Lesson.class);
                    Log.i("MyLogLesId", l.id);
                    Temp.lessons.put(l.id, l);
                }
                getLessonsStudents();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("MyLog", "Erorrrrrrrrrrrrrrrrrrrrrrrr");
            }
        });
    }

    public void getLessonsStudents() {
        for (final Lesson les: Temp.lessons.values()){
            Log.i("MyLogInfoLes", les.id);
            ArrayList<Person> people = new ArrayList<>();
            Temp.lessons_students.put(les.id, people);

            for (final String key: les.group.keySet()) {

                mDatabase.child("users").child("persons").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Person person = dataSnapshot.getValue(Person.class);
                        ArrayList<Person> data = (ArrayList<Person>) Temp.lessons_students.get(les.id).clone();
                        data.add(person);
                        Temp.lessons_students.put(les.id, (ArrayList<Person>) data.clone());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("MyLog", "Erorrrrrrrrrrrrrrrrrrrrrrrr");
                    }
                });
            }
        }
    }

    public void updateDB(String msg) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/temp/" + mDatabase.child("temp").push().getKey(), msg);
        mDatabase.updateChildren(childUpdates);

    }
}
