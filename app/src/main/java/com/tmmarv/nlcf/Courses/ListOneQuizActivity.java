package com.tmmarv.nlcf.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tmmarv.nlcf.R;

public class ListOneQuizActivity extends AppCompatActivity {

    private CardView mts;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_one_quiz);

        mts = findViewById(R.id.mts_101);
        mts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListOneQuizActivity.this, MtsOneActivity.class));
            }
        });
    }
}