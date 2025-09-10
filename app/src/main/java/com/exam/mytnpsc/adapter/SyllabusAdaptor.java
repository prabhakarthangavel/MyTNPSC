package com.exam.mytnpsc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.exam.mytnpsc.R;
import com.exam.mytnpsc.databinding.SubjectSyllabusListBinding;
import com.exam.mytnpsc.models.Syllabus;

import java.util.List;

public class SyllabusAdaptor extends RecyclerView.Adapter<SyllabusAdaptor.SyllabusViewHolder>{
    private List<Syllabus> syllabusList;
    public SyllabusAdaptor(List<Syllabus> syllabusList) {
        this.syllabusList = syllabusList;
    }

    @NonNull
    @Override
    public SyllabusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SubjectSyllabusListBinding syllabusListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.subject_syllabus_list, parent, false);
        return new SyllabusViewHolder(syllabusListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabusViewHolder holder, int position) {
        Syllabus syllabus = syllabusList.get(position);
        holder.syllabusListBinding.setSyllabus(syllabus);
    }

    @Override
    public int getItemCount() {
        return syllabusList.size();
    }

    public static class SyllabusViewHolder extends RecyclerView.ViewHolder{

        private SubjectSyllabusListBinding syllabusListBinding;

        public SyllabusViewHolder(SubjectSyllabusListBinding syllabusListBinding) {
            super(syllabusListBinding.getRoot());
            this.syllabusListBinding = syllabusListBinding;

            syllabusListBinding.getRoot().setOnClickListener(v -> {});
        }
    }
}
