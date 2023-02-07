package com.tmmarv.nlcf.Adapters;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tmmarv.nlcf.Models.BooksModel;
import com.tmmarv.nlcf.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<BooksModel> mModels;

    public BooksAdapter(Context context, ArrayList<BooksModel> models) {
        mContext = context;
        mModels = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spiritual_item, parent,false);
        return new BooksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BooksModel model = mModels.get(position);
        holder.titleTv.setText(model.getPdfName());
        holder.sizeTv.setText(convertBToMb(model.getSize(),1000000));

        holder.downloadBtn.setOnClickListener(v -> {
            holder.progress.setVisibility(View.VISIBLE);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReferenceFromUrl("gs://nlcf-9d352.appspot.com").child("spiritual/").child(model.getLastPath());
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                final File localFile;
                try {
                    localFile = File.createTempFile("spiritual", "pdf");
                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(uri);

                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, model.getPdfName());

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
        });

    }

    @Override
    public int getItemCount() {
        return mModels.size();
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
