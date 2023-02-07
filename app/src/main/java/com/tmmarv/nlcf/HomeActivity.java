package com.tmmarv.nlcf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tmmarv.nlcf.Fragments.BooksFragment;
import com.tmmarv.nlcf.Fragments.HomeFragment;
import com.tmmarv.nlcf.Fragments.MemberFragment;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView mBottomNavigationView;
    FrameLayout mFrameLayout;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mFrameLayout = findViewById(R.id.fragment_holder);

        mBottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = new HomeFragment();

            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.members:
                    selectedFragment = new MemberFragment();
                    break;
                case R.id.edu:
                    selectedFragment = new BooksFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, selectedFragment).commit();
            return true;
        });
        mBottomNavigationView.setSelectedItemId(R.id.home);
    }
}