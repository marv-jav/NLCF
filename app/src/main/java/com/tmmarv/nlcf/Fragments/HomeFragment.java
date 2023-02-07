package com.tmmarv.nlcf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tmmarv.nlcf.R;
import com.tmmarv.nlcf.ScheduleActivity;
import com.tmmarv.nlcf.SermonsActivity;

public class HomeFragment extends Fragment {

    private Button calendarBtn;
    private Button sermonsBtn;
    private FirebaseAuth mAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        calendarBtn = view.findViewById(R.id.calendarBtn);
        sermonsBtn = view.findViewById(R.id.sermonBtn);

        calendarBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), ScheduleActivity.class)));
        sermonsBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), SermonsActivity.class)));

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    mAuth.signOut();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}