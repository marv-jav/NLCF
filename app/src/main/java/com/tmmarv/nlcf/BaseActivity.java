package com.tmmarv.nlcf;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class BaseActivity extends AppCompatActivity {
    protected void activateToolBar(boolean enableHome){
        ActionBar actionBar =getSupportActionBar();
        if (actionBar == null) {
            MaterialToolbar toolbar = findViewById(R.id.material_toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }
        }
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
