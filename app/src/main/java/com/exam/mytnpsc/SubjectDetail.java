package com.exam.mytnpsc;

import static com.exam.mytnpsc.Constants.SYLLABUS_FILE_NAME;

import android.os.Bundle;

import com.exam.mytnpsc.adapter.SyllabusAdaptor;
import com.exam.mytnpsc.databinding.ActivityMainBinding;
import com.exam.mytnpsc.databinding.BottomNavigationBinding;
import com.exam.mytnpsc.models.Syllabus;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exam.mytnpsc.databinding.ActivitySubjectDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SubjectDetail extends AppCompatActivity {

    private ActivitySubjectDetailBinding subjectDetailBinding;
    private SyllabusAdaptor syllabusAdapter;
    private List<Syllabus> syllabusItemList;

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

        setupRecyclerView();
        loadSyllabusData();
    }

    private void setupRecyclerView() {
        // Initialize RecyclerView
        RecyclerView recyclerView = subjectDetailBinding.syllabusRecyclerView; // Get from binding
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true); // Optimization if item sizes don't change

        // Initialize adapter with an empty list initially
        syllabusItemList = new ArrayList<>();
        syllabusAdapter = new SyllabusAdaptor(syllabusItemList);
        recyclerView.setAdapter(syllabusAdapter);
    }

    private void loadSyllabusData() {
        this.syllabusItemList.clear();
        this.syllabusItemList.addAll(this.getSyllabusItemList(SYLLABUS_FILE_NAME));
        syllabusAdapter.notifyDataSetChanged(); // Or use a more specific notify method or DiffUtil
    }

    private List<Syllabus> getSyllabusItemList(final String fileName) {
        List<Syllabus> syllabusList = new ArrayList<>();
        String json = this.loadJSONFromAsset(fileName);
        try {
            // Parse the JSON string
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("syllabus");

            // Iterate through the array and extract the data
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject topicObject = jsonArray.getJSONObject(i);
                int id = topicObject.getInt("id");
                String name = topicObject.getString("name");
                syllabusList.add(new Syllabus(id, name));
            }
        } catch (JSONException jsonException) {
            Log.e("JSON_PARSER", "Error parsing JSON data", jsonException);
        }
        return syllabusList;
    }
    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            // Open the JSON file from the assets folder as an InputStream
            InputStream inputStream = getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ioException) {
            Log.e("JSON_PARSER", "Error reading asset file: " + fileName, ioException);
        }
        return json;
    }
}