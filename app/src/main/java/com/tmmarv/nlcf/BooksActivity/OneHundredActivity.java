package com.tmmarv.nlcf.BooksActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tmmarv.nlcf.Adapters.BooksAdapter;
import com.tmmarv.nlcf.BaseActivity;
import com.tmmarv.nlcf.Courses.ListOneQuizActivity;
import com.tmmarv.nlcf.Models.BooksModel;
import com.tmmarv.nlcf.R;

import java.util.ArrayList;
import java.util.List;

public class OneHundredActivity extends BaseActivity {

    private RecyclerView growthRecycler;
    private BooksAdapter mGrowthAdapter;
    private ArrayList<BooksModel> mGrowthList;
    private FirebaseFirestore db;
    private ProgressBar mProgressBar;
    private FloatingActionButton quiz;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_hundred);

        db = FirebaseFirestore.getInstance();
        mProgressBar = findViewById(R.id.progressBar_one);
        quiz = findViewById(R.id.quiz);

        growthRecycler = findViewById(R.id.one_hundred_recycler);
        mGrowthList = new ArrayList<>();
        mGrowthAdapter = new BooksAdapter(this, mGrowthList);
        growthRecycler.setHasFixedSize(true);
        growthRecycler.setLayoutManager(new LinearLayoutManager(this));

        db.collection("one").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    BooksModel c = d.toObject(BooksModel.class);
                    mGrowthList.add(c);
                }
                growthRecycler.setAdapter(mGrowthAdapter);
                mGrowthAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }else{
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(OneHundredActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(OneHundredActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        activateToolBar(false);

        quiz.setOnClickListener(v -> {
            startActivity(new Intent(OneHundredActivity.this, ListOneQuizActivity.class));
        });
    }
}