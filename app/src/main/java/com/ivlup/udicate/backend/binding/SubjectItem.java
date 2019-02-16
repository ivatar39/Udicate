package com.ivlup.udicate.backend.binding;

import android.support.annotation.NonNull;
import android.view.View;

import com.ivlup.udicate.R;
import com.ivlup.udicate.backend.education.Subject;
import com.ivlup.udicate.databinding.SubjectItemBinding;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.databinding.BindableItem;

public class SubjectItem extends BindableItem<SubjectItemBinding> {

    private Subject subject;

    public SubjectItem(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void bind(@NonNull final SubjectItemBinding viewBinding, int position) {
        viewBinding.tvSubjectName.setText(subject.getName());
        viewBinding.subjectCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewBinding.subjectCheckBox.toggle();
            }
        });
        //Picasso.get().load(subject.getImage()).into(viewBinding.ivSubject);
    }

    @Override
    public int getLayout() {
        return R.layout.subject_item;
    }

}