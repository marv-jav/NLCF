package com.tmmarv.nlcf.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.tmmarv.nlcf.R;
import com.tmmarv.nlcf.ScheduleActivity;
import com.tmmarv.nlcf.SermonsActivity;

public class HomeFragment extends Fragment {

    private Button calendarBtn;
    private Button sermonsBtn;
    private FirebaseAuth mAuth;
    private TextView dailyVerse;
    private TextView weeklyVerse;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getDailyVerse();

        calendarBtn = view.findViewById(R.id.calendarBtn);
        sermonsBtn = view.findViewById(R.id.sermonBtn);
        dailyVerse = view.findViewById(R.id.dailyVerseTv);
        weeklyVerse = view.findViewById(R.id.weeklyVerseTv);

        calendarBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), ScheduleActivity.class)));
        sermonsBtn.setOnClickListener(v -> startActivity(new Intent(getActivity(), SermonsActivity.class)));

        return view;
    }

    private void getDailyVerse() {
        db.collection("daily").document(mAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                String verse = value.getString("daily");
                dailyVerse.setText(verse);
            }
        });
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