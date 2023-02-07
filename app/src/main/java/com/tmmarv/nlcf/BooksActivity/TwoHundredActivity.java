package com.tmmarv.nlcf.BooksActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tmmarv.nlcf.Adapters.BooksAdapter;
import com.tmmarv.nlcf.BaseActivity;
import com.tmmarv.nlcf.Models.BooksModel;
import com.tmmarv.nlcf.R;

import java.util.ArrayList;
import java.util.List;

public class TwoHundredActivity extends BaseActivity {

    private RecyclerView growthRecycler;
    private BooksAdapter mGrowthAdapter;
    private ArrayList<BooksModel> mGrowthList;
    private FirebaseFirestore db;
    private ProgressBar mProgressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_hundred);

        db = FirebaseFirestore.getInstance();
        mProgressBar = findViewById(R.id.progressBar);

        growthRecycler = findViewById(R.id.two_hundred_recycler);
        mGrowthList = new ArrayList<>();
        mGrowthAdapter = new BooksAdapter(this, mGrowthList);
        growthRecycler.setHasFixedSize(true);
        growthRecycler.setLayoutManager(new LinearLayoutManager(this));

        db.collection("two").get().addOnSuccessListener(queryDocumentSnapshots -> {
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
                Toast.makeText(TwoHundredActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(TwoHundredActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        activateToolBar(false);
    }
}