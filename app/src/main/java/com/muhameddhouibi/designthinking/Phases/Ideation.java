package com.muhameddhouibi.designthinking.Phases;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.RooActivity;
import com.muhameddhouibi.designthinking.Steps.Step1Activity;

public class Ideation extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference FinalRoom ;
    FirebaseUser currentUser ;
    FirebaseAuth mAuth;
    String roomname ,privacy;
    Button start ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideation);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        start = findViewById(R.id.startt);
        privacy =  getIntent().getStringExtra("privacy");
        roomname =  getIntent().getStringExtra("roomName");

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (Ideation.this, Step1Activity.class);
                i.putExtra("roomName",roomname);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

    }
}
