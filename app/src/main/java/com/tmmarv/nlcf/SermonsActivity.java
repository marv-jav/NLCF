package com.tmmarv.nlcf;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tmmarv.nlcf.Adapters.SermonAdapter;
import com.tmmarv.nlcf.Models.SermonModel;

import java.util.ArrayList;
import java.util.List;

public class SermonsActivity extends BaseActivity {

    private RecyclerView sermonRecycler;
    private SermonAdapter mSermonAdapter;
    private ArrayList<SermonModel> mSermonList;
    private FirebaseFirestore db;
    private ProgressBar mProgressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sermons);

        db = FirebaseFirestore.getInstance();
        mProgressBar = findViewById(R.id.progressBar);

        sermonRecycler = findViewById(R.id.sermon_recycler);
        mSermonList = new ArrayList<>();
        mSermonAdapter = new SermonAdapter(this, mSermonList);
        sermonRecycler.setHasFixedSize(true);
        sermonRecycler.setLayoutManager(new LinearLayoutManager(this));

        db.collection("sermons").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    SermonModel c = d.toObject(SermonModel.class);
                    mSermonList.add(c);
                }
                sermonRecycler.setAdapter(mSermonAdapter);
                mSermonAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(SermonsActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(SermonsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
        activateToolBar(false);
    }
}