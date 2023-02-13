package com.tmmarv.nlcf.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.squareup.picasso.Picasso;
import com.tmmarv.nlcf.MemberDetailsActivity;
import com.tmmarv.nlcf.Models.MemberModel;
import com.tmmarv.nlcf.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MemberModel> mMemberModels;

    public MemberAdapter(Context context, ArrayList<MemberModel> memberModels) {
        mContext = context;
        mMemberModels = memberModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_item, parent, false);
        return new MemberAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MemberModel memberModel = mMemberModels.get(position);
        holder.memberName.setText(memberModel.getName());
        holder.memberPost.setText(memberModel.getPosition());
        holder.memberLevel.setText(memberModel.getLevel());
        holder.memberDepartment.setText(memberModel.getDepartment());
        Picasso.get().load(memberModel.getImageUrl()).into(holder.memberImage);
    }

    @Override
    public int getItemCount() {
        return mMemberModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView memberImage;
        private TextView memberName;
        private TextView memberPost;
        private TextView memberDepartment;
        private TextView memberLevel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memberImage = itemView.findViewById(R.id.member_image);
            memberName = itemView.findViewById(R.id.member_name);
            memberPost = itemView.findViewById(R.id.member_post);
            memberDepartment = itemView.findViewById(R.id.member_department);
            memberLevel = itemView.findViewById(R.id.member_level);
        }
    }

}
