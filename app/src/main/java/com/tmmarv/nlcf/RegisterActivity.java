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

public class RegisterActivity extends BaseActivity {

    TextInputLayout nameLay;
    TextInputLayout emailLay;
    TextInputLayout phoneLay;
    TextInputLayout passwordLay;
    Button buttonReg;
    TextView loginText;
    Auth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameLay = findViewById(R.id.nameLay);
        emailLay = findViewById(R.id.emailLay);
        phoneLay = findViewById(R.id.phoneLay);
        passwordLay = findViewById(R.id.passwordLay);
        buttonReg = findViewById(R.id.buttonLogin);
        loginText = findViewById(R.id.textRegister);

        auth = new Auth();
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);


        buttonReg.setOnClickListener(v -> {
            String name = Objects.requireNonNull(nameLay.getEditText()).getText().toString();
            String email = Objects.requireNonNull(emailLay.getEditText()).getText().toString();
            String phone = Objects.requireNonNull(phoneLay.getEditText()).getText().toString();
            String password = Objects.requireNonNull(passwordLay.getEditText()).getText().toString();

            if (name.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "One or more fields are empty", Toast.LENGTH_SHORT).show();
            } else {
                auth.registerUser(name, email, phone, password, this, intent);
            }
        });

        loginText.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}