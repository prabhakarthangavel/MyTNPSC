package com.exam.mytnpsc;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exam.mytnpsc.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentHomeBinding binding;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setScienceImage(ContextCompat.getDrawable(getContext(), R.drawable.microscope_100));
        binding.setScienceName("Science");
        binding.setGeographyImage(ContextCompat.getDrawable(getContext(), R.drawable.geography_100));
        binding.setGeographyName("Geography");
        binding.setHistoryImage(ContextCompat.getDrawable(getContext(), R.drawable.history_100));
        binding.setHistoryName("History");
        binding.setPoliticsImage(ContextCompat.getDrawable(getContext(), R.drawable.politics_100));
        binding.setPoliticsName("Polity");
        binding.setEconomyImage(ContextCompat.getDrawable(getContext(), R.drawable.economy_100));
        binding.setEconomyName("Economy");
        binding.setTamilNaduImage(ContextCompat.getDrawable(getContext(), R.drawable.chennai_100));
        binding.setTamilNaduName("Tamil Nadu");
        binding.setMathsImage(ContextCompat.getDrawable(getContext(), R.drawable.calculator_100));
        binding.setMathsName("Maths");
        binding.setLanguageImage(ContextCompat.getDrawable(getContext(), R.drawable.language_100));
        binding.setLanguageName("Tamil");

        binding.science.getRoot().setOnClickListener(commonViewClickListener);
        binding.geography.getRoot().setOnClickListener(commonViewClickListener);
        binding.history.getRoot().setOnClickListener(commonViewClickListener);
        binding.politics.getRoot().setOnClickListener(commonViewClickListener);
        binding.economy.getRoot().setOnClickListener(commonViewClickListener);
        binding.tamilNadu.getRoot().setOnClickListener(commonViewClickListener);
        binding.maths.getRoot().setOnClickListener(commonViewClickListener);
        binding.language.getRoot().setOnClickListener(commonViewClickListener);
    }

    View.OnClickListener commonViewClickListener = v -> {
        String selectedSubject = null;
        if (v.getId() == binding.science.getRoot().getId()) {
            selectedSubject = binding.getScienceName();
        } else if (v.getId() == binding.geography.getRoot().getId()) {
            selectedSubject = binding.getGeographyName();
        } else if (v.getId() == binding.history.getRoot().getId()) {
            selectedSubject = binding.getHistoryName();
        } else if (v.getId() == binding.politics.getRoot().getId()) {
            selectedSubject = binding.getPoliticsName();
        } else if (v.getId() == binding.economy.getRoot().getId()) {
            selectedSubject = binding.getEconomyName();
        } else if (v.getId() == binding.tamilNadu.getRoot().getId()) {
            selectedSubject = binding.getTamilNaduName();
        } else if (v.getId() == binding.maths.getRoot().getId()) {
            selectedSubject = binding.getMathsName();
        } else if (v.getId() == binding.language.getRoot().getId()) {
            selectedSubject = binding.getLanguageName();
        }
        Intent intent = new Intent(getContext(), SubjectDetail.class);
        Log.d("TAG", "onSeubjectClick" + getView().toString());
        intent.putExtra("title", selectedSubject);
        startActivity(intent);
    };
}