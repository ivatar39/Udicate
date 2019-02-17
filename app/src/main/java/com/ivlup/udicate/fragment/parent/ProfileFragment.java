package com.ivlup.udicate.fragment.parent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ivlup.udicate.R;

public class ProfileFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView mProfilePic = (ImageView) view.findViewById(R.id.profile_pic);
        // Picasso.get().load(Temp.teacher.avatar).resize(300,400).centerCrop().into(mProfilePic);

        TextView mName = (TextView) view.findViewById(R.id.profile_name);
        // mName.setText(Temp.teacher.name+" "+Temp.teacher.surname);

        TextView mStage = (TextView) view.findViewById(R.id.profile_stage);
        // mStage.setText(Temp.teacher.email);
        TextView mSpeciality = (TextView) view.findViewById(R.id.profile_speciality);
        // mSpeciality.setText(Temp.teacher.phone);

        TextView mAbout = (TextView) view.findViewById(R.id.profile_about);
        // mAbout.setText(Temp.teacher.info);

        TextView mPlace = (TextView)  view.findViewById(R.id.profile_work);
        mPlace.setText("Академия гениев");
    }

}
