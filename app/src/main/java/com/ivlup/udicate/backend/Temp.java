package com.ivlup.udicate.backend;

import com.google.firebase.auth.FirebaseUser;
import com.ivlup.udicate.backend.objects.Lesson;
import com.ivlup.udicate.backend.users.Person;
import com.ivlup.udicate.backend.users.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Temp {
    public static FirebaseUser user;
    public static Map<String, Lesson> lessons = new HashMap<>();
    public static Person person;
    public static Teacher teacher;
    public static int start = 0;
    public static int who = 0;
    public static Map<String, ArrayList<Person>> lessons_students = new HashMap<>();
    public static String t_lesson_id;
    // public static ArrayList<Map<String, ArrayList<Person>>> lessons_students = new ArrayList<>();
    /*
    1  = User
    2 = Teacher
    3 = noName
     */
}
