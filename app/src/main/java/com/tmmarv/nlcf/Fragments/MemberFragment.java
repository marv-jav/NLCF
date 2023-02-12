package com.tmmarv.nlcf.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.tmmarv.nlcf.Adapters.MemberAdapter;
import com.tmmarv.nlcf.Models.MemberModel;
import com.tmmarv.nlcf.Models.SermonModel;
import com.tmmarv.nlcf.R;
import com.tmmarv.nlcf.SermonsActivity;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment {

    private RecyclerView memberRecycler;
    private MemberAdapter mMemberAdapter;
    private ArrayList<MemberModel> mMemberList;
    private FirebaseFirestore db;
    private ProgressBar mProgressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        memberRecycler = view.findViewById(R.id.recycler_members);
        mMemberList = new ArrayList<>();
        mMemberAdapter = new MemberAdapter(getActivity(), mMemberList);
        mProgressBar = view.findViewById(R.id.member_load);
        db = FirebaseFirestore.getInstance();

        memberRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        memberRecycler.setHasFixedSize(true);

        db.collection("members").get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (!queryDocumentSnapshots.isEmpty()) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d : list) {
                    MemberModel c = d.toObject(MemberModel.class);
                    mMemberList.add(c);
                }
                memberRecycler.setAdapter(mMemberAdapter);
                mMemberAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }else{
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

        return view;
    }
}