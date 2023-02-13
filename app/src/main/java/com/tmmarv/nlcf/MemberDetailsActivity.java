package com.tmmarv.nlcf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MemberDetailsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView name, post, department, level, dob;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        mImageView = findViewById(R.id.user_image);
        name = findViewById(R.id.user_name);
        post = findViewById(R.id.user_post);
        level = findViewById(R.id.user_level);
        department = findViewById(R.id.user_department);
        dob = findViewById(R.id.user_dob);

        Bundle intent = getIntent().getExtras();
        String url = intent.getString("url");
        String n = intent.getString("memberName");
        String p = intent.getString("memberPost");
        String d = intent.getString("memberDepartment");
        String l = intent.getString("memberLevel");
        String da = intent.getString("memberDob");

        Picasso.get().load(url).into(mImageView);
        name.setText(n);
        post.setText(p);
        level.setText(l);
        department.setText(d);
        dob.setText(da);


    }
}