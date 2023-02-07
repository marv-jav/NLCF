package com.tmmarv.nlcf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmmarv.nlcf.Models.ScheduleModel;
import com.tmmarv.nlcf.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapters extends RecyclerView.Adapter<ScheduleAdapters.ViewHolder> {

    private ArrayList<ScheduleModel> list;
    private Context context;

    public ScheduleAdapters(ArrayList<ScheduleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapters.ViewHolder holder, int position) {
        ScheduleModel model = list.get(position);
        holder.title.setText(model.getTitle());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        if (model.getStatus().equals("true")) {
            holder.status.setVisibility(View.VISIBLE);
        }else{
            holder.status.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;
        private TextView time;
        private ImageView status;
        private boolean isDone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.schedule_title);
            date = itemView.findViewById(R.id.schedule_date);
            time = itemView.findViewById(R.id.schedule_time);
            status = itemView.findViewById(R.id.status);
        }
    }
}
