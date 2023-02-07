package com.tmmarv.nlcf.Adapters;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.tmmarv.nlcf.HomeActivity;
import com.tmmarv.nlcf.LoginActivity;
import com.tmmarv.nlcf.Models.SermonModel;
import com.tmmarv.nlcf.R;
import com.tmmarv.nlcf.SplashActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SermonAdapter extends RecyclerView.Adapter<SermonAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<SermonModel> mSermonModelList;

    public SermonAdapter(Context context, ArrayList<SermonModel> sermonModelList) {
        mContext = context;
        mSermonModelList = sermonModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sermon_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SermonModel model = mSermonModelList.get(position);
        holder.titleTv.setText(model.getSermonName());
        holder.sizeTv.setText(convertBToMb(model.getSize(),1000000));

        holder.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.progress.setVisibility(View.VISIBLE);
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReferenceFromUrl("gs://nlcf-9d352.appspot.com").child("audio/").child(model.getLastPath());
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    final File localFile;
                    try {
                        localFile = File.createTempFile("audio", "mpeg");
                        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request request = new DownloadManager.Request(uri);

                                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, model.getSermonName());

                                downloadManager.enqueue(request);
                                holder.progress.setVisibility(View.GONE);
                                Handler handler = new Handler();
                                handler.postDelayed(() -> {
                                    holder.done.setVisibility(View.VISIBLE);
                                }, 100);
                                holder.done.setVisibility(View.GONE);
                            }
                        }).addOnFailureListener(e -> {
                            holder.progress.setVisibility(View.GONE);
                            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSermonModelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView sizeTv;
        private ImageView downloadBtn;
        private ProgressBar progress;
        private ImageView done;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.sermon_title);
            sizeTv = itemView.findViewById(R.id.sermon_size);
            downloadBtn = itemView.findViewById(R.id.download_btn);
            progress = itemView.findViewById(R.id.download_status);
            done = itemView.findViewById(R.id.done);
        }
    }

    private String convertBToMb(long kb, long div) {
        double toDouble = ((double) kb / div);
        @SuppressLint("DefaultLocale") String approx = String.format("%.1f", toDouble);
        return approx + "MB";
    }
}
