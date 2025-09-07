package com.exam.mytnpsc;

import android.os.Bundle;

import com.exam.mytnpsc.databinding.ActivityMainBinding;
import com.exam.mytnpsc.databinding.BottomNavigationBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.exam.mytnpsc.databinding.ActivitySubjectDetailBinding;

public class SubjectDetail extends AppCompatActivity {

    private ActivitySubjectDetailBinding subjectDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        subjectDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_subject_detail);
        subjectDetailBinding.setLifecycleOwner(this);
        ViewCompat.setOnApplyWindowInsetsListener(subjectDetailBinding.main, (v, windowInsets) -> {
            Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return windowInsets;
        });

        String title = getIntent().getStringExtra("title");
        subjectDetailBinding.header.pageTitle.setText(title);
        subjectDetailBinding.header.toolbarRightIcon.setVisibility(View.INVISIBLE);
        subjectDetailBinding.header.toolbarBacButton.setOnClickListener(v -> {
            finish();
        });


    }
}