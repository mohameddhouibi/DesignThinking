package com.muhameddhouibi.designthinking.Phases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.Steps.Step1Activity;
import com.muhameddhouibi.designthinking.Steps.Step7Activity;

public class Inspiration extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase ;
    FirebaseUser currentUser ;
    FirebaseAuth mAuth;
    String roomname ;
    Button start ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspiration);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        start = findViewById(R.id.startt);

        roomname =  getIntent().getStringExtra("discussion");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Inspiration.this, Step7Activity.class);
                i.putExtra("player1",currentUser.getDisplayName());
                i.putExtra("discussion",roomname);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }
}
