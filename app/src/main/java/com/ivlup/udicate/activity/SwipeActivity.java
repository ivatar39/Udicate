package com.ivlup.udicate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.ivlup.udicate.CardStackAdapter;
import com.ivlup.udicate.backend.binding.PersonItem;
import com.ivlup.udicate.backend.education.Person;
import com.ivlup.udicate.R;
import com.ivlup.udicate.PersonDiffCallback;
import com.ivlup.udicate.backend.Temp;
import com.ivlup.udicate.backend.education.Lesson;
import com.ivlup.udicate.fragment.LessonCardFragment;
import com.xwray.groupie.Item;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.RewindAnimationSetting;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity implements CardStackListener {


    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private CardStackView cardStackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        setupCardStackView();
        setupButton();
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d("CardStackView", "onCardDragging: d = " + direction.name() + ", r = " + ratio);
    }

    @Override
    public void onCardSwiped(Direction direction) {
        Log.d("CardStackView", "onCardSwiped: p = " + manager.getTopPosition() + ", d = " + direction);
        if (String.valueOf(direction).equals("Left")) {
            Lesson l = Temp.lessons.get(Temp.t_lesson_id);
            l.group.put(Temp.lessons_students.get(Temp.t_lesson_id).get(manager.getTopPosition() -1).id, false);
            Temp.lessons.put(Temp.t_lesson_id, l);
        }
        if (adapter.getItemCount()==0) {
            Log.d("CardStackView" ,"finished! 74");
            finish();
        }
        if (manager.getTopPosition() == adapter.getItemCount() - 5) {

            paginate();
        }
    }

    @Override
    public void onCardRewound() {
        Log.d("CardStackView", "onCardRewound: " + manager.getTopPosition());
    }

    @Override
    public void onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled:" + manager.getTopPosition());
    }

    @Override
    public void onCardAppeared(View view, int position) {
        if (position == adapter.getItemCount()) {

            Log.d("CardStackView" ,"finished! 96");
            finish();
            LessonCardFragment.adapter.clear();
            ArrayList<Item> items = new ArrayList<>();

            for (com.ivlup.udicate.backend.users.Person person: Temp.lessons_students.get(Temp.t_lesson_id)) {
                items.add(new PersonItem(person));
            }
            LessonCardFragment.adapter.addAll(items);
        }
        else {
        TextView textView = view.findViewById(R.id.item_name);
        Log.d("CardStackView", "onCardAppeared: (" + position + ") " + textView.getText());}
    }

    @Override
    public void onCardDisappeared(View view, int position) {
        if (position == adapter.getItemCount()) finish();
        TextView textView = view.findViewById(R.id.item_name);
        Log.d("", "onCardDisappeared: (" + position + ") " + textView.getText());
    }



    private void setupCardStackView() {
        initialize();
    }

    private void setupButton() {
        View skip = findViewById(R.id.skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Left)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
        });

        View rewind = findViewById(R.id.rewind_button);
        rewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RewindAnimationSetting setting = new RewindAnimationSetting.Builder()
                        .setDirection(Direction.Bottom)
                        .setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .build();
                manager.setRewindAnimationSetting(setting);
                cardStackView.rewind();
            }
        });

        View like = findViewById(R.id.like_button);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                        .setDirection(Direction.Right)
                        .setDuration(200)
                        .setInterpolator(new AccelerateInterpolator())
                        .build();
                manager.setSwipeAnimationSetting(setting);
                cardStackView.swipe();
            }
        });
    }

    private void initialize() {
        manager = new CardStackLayoutManager(this, this);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(this, createPersons());
        cardStackView = findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }

    private void paginate() {
        List<Person> oldList = adapter.getPersons();
        List<Person> newList = new ArrayList<Person>() {{
            addAll(adapter.getPersons());
            addAll(createPersons());
        }};
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void reload() {
        List<Person> oldList = adapter.getPersons();
        List<Person> newList = createPersons();
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void addFirst(final int size) {
        List<Person> oldList = adapter.getPersons();
        List<Person> newList = new ArrayList<Person>() {{
            addAll(adapter.getPersons());
            for (int i = 0; i < size; i++) {
                add(manager.getTopPosition(), createPerson());
            }
        }};
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void addLast(final int size) {
        List<Person> oldList = adapter.getPersons();
        List<Person> newList = new ArrayList<Person>() {{
            addAll(adapter.getPersons());
            for (int i = 0; i < size; i++) {
                add(createPerson());
            }
        }};
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void removeFirst(final int size) {
        if (adapter.getPersons().isEmpty()) {
            return;
        }

        List<Person> oldList = adapter.getPersons();
        List<Person> newList = new ArrayList<Person>() {{
            addAll(adapter.getPersons());
            for (int i = 0; i < size; i++) {
                remove(manager.getTopPosition());
            }
        }};
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private void removeLast(final int size) {
        if (adapter.getPersons().isEmpty()) {
            return;
        }

        List<Person> oldList = adapter.getPersons();
        List<Person> newList = new ArrayList<Person>() {{
            addAll(adapter.getPersons());
            for (int i = 0; i < size; i++) {
                remove(size() - 1);
            }
        }};
        PersonDiffCallback callback = new PersonDiffCallback(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);
        adapter.setPersons(newList);
        result.dispatchUpdatesTo(adapter);
    }

    private Person createPerson() {
        return new Person("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800");
    }

    private List<Person> createPersons() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < Temp.lessons_students.get(Temp.t_lesson_id).size(); i++) {
            people.add(new Person(
                    Temp.lessons_students.get(Temp.t_lesson_id).get(i).name,
                    Temp.lessons_students.get(Temp.t_lesson_id).get(i).surname,
                    Temp.lessons_students.get(Temp.t_lesson_id).get(i).avatar
            ));

        }
        return people;
    }

}
