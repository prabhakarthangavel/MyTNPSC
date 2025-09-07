package com.exam.mytnpsc;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.exam.mytnpsc.databinding.ActivityMainBinding;
import com.exam.mytnpsc.databinding.BottomNavigationBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BottomNavigationBinding bottomNavigationBinding = mainBinding.mainActivityNavMenu;
        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            bottomNavigationBinding.bottomNavigation.setPadding(0, 0, 0, systemBars.bottom);
            return insets;
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrameLayout, new HomeFragment());
        fragmentTransaction.commit();

        mainBinding.mainActivityNavMenu.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getTitle().toString().equalsIgnoreCase("home")) {

                    return true;
                }else if (item.getTitle().toString().equalsIgnoreCase("bookmarks")) {
                    return true;
                }
                return false;
            }
        });
//
//        <!--            app:itemIconTint="@drawable/item_selector"-->
//                <!--            app:itemTextColor="@drawable/item_selector"-->
//                <!--            app:itemIconSize="@drawable/item_selector"-->

    }
}