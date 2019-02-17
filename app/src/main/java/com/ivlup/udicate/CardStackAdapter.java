package com.ivlup.udicate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ivlup.udicate.backend.objects.Person;

import java.util.List;

public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Person> persons;

    public CardStackAdapter(Context context, List<Person> persons) {
        this.inflater = LayoutInflater.from(context);
        this.persons = persons;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_person, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Person person = persons.get(position);
        holder.name.setText(person.name);
        holder.city.setText(person.city);
        Glide.with(holder.image)
                .load(person.url)
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), person.name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView city;
        ImageView image;
        ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.item_name);
            this.city = view.findViewById(R.id.item_city);
            this.image = view.findViewById(R.id.item_image);
        }
    }

}
