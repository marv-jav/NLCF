package com.tmmarv.nlcf.Engine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Auth {

    private FirebaseAuth mAuth;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog mProgressDialog;

    public void registerUser(String name, String email, String phone, String password, Context context, Intent intent) {
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Creating Account...");
        mProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Map<String, Object> users = new HashMap<>();
                users.put("name", name);
                users.put("phone", phone);
                users.put("email", email);
                users.put("unit", "");
                users.put("dateOfBirth", "");
                users.put("level", "");

                db.collection("users").add(users).addOnSuccessListener(documentReference -> {
                    mProgressDialog.dismiss();
                    goToHome(intent, context);
                }).addOnFailureListener(e -> {
                    mProgressDialog.dismiss();
                    Toast.makeText(context, "An error has occurred, try again.", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    public void loginUser(String email, String password, Context context, Intent intent) {
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                mProgressDialog.dismiss();
                goToHome(intent, context);
            }else{
                mProgressDialog.dismiss();
                Toast.makeText(context, "An error has occurred, try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToHome(Intent intent, Context context) {
        context.startActivity(intent);
    }
}
