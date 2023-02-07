package com.tmmarv.nlcf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tmmarv.nlcf.Adapters.ScheduleAdapters;
import com.tmmarv.nlcf.Models.ScheduleModel;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends BaseActivity {

    private ArrayList<ScheduleModel> mScheduleModelArrayList;
    private RecyclerView mRecyclerView;
    private ScheduleAdapters mScheduleAdapters;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mRecyclerView = findViewById(R.id.recycler);
        db = FirebaseFirestore.getInstance();

        mScheduleModelArrayList = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mScheduleAdapters = new ScheduleAdapters(mScheduleModelArrayList, this);

        db.collection("schedule").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    ScheduleModel c = d.toObject(ScheduleModel.class);
                    mScheduleModelArrayList.add(c);
                }
                mRecyclerView.setAdapter(mScheduleAdapters);
                mScheduleAdapters.notifyDataSetChanged();
            } else {
                Toast.makeText(ScheduleActivity.this, "No data found", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(ScheduleActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show());

        activateToolBar(false);
    }
}