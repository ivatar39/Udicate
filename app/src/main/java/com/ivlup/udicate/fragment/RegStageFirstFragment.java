package com.ivlup.udicate.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.ivlup.udicate.activity.FirebaseUIActivity;
import com.ivlup.udicate.R;

public class RegStageFirstFragment extends Fragment {
    public static RadioButton mBtStudent, mBtParent, mBtTeacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.reg_stage1_role, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtParent  = view.findViewById(R.id.bt_parent);
        mBtTeacher = view.findViewById(R.id.bt_teacher);

    }

    public static int getSelected() {  //returns type
        if (mBtParent.isSelected())     return FirebaseUIActivity.STUDENT_PARENT;
        if (mBtTeacher.isSelected())    return FirebaseUIActivity.TEACHER;
        return -1;
    }
}
