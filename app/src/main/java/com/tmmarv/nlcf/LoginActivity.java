package com.tmmarv.nlcf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.tmmarv.nlcf.Engine.Auth;

import java.util.Objects;

public class LoginActivity extends BaseActivity {

    TextInputLayout emailLay;
    TextInputLayout passwordLay;
    Button btnLogin;
    TextView registerTv;
    Auth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLay = findViewById(R.id.emailLay);
        passwordLay = findViewById(R.id.passwordLay);
        btnLogin = findViewById(R.id.buttonLogin);
        registerTv = findViewById(R.id.textRegister);
        mAuth = new Auth();

        registerTv.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> {
            String email = Objects.requireNonNull(emailLay.getEditText()).getText().toString();
            String password = Objects.requireNonNull(passwordLay.getEditText()).getText().toString();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
            }else {
                mAuth.loginUser(email, password, this, intent);
            }
        });
    }
}