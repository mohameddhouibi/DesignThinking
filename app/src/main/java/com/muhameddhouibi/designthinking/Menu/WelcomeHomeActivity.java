package com.muhameddhouibi.designthinking.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.TestSwiActivity;
import com.muhameddhouibi.designthinking.WorkshopActivity;

public class WelcomeHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    ImageView introduction,workshop,help,constribute;
    String playerName ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_home);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        UpdateConnectStatut();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.homemenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homemenu :
                        return true ;
                    case R.id.notificationsmenu :
                        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.friendsmenu :
                        startActivity(new Intent(getApplicationContext(), FriendsActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.profilemenu :
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.gamesmenu :
                        startActivity(new Intent(getApplicationContext(), MyGamesActivity.class));
                        overridePendingTransition(0,0);
                        return true ;

                }
                return false;
            }
        });

        introduction=findViewById(R.id.introduction);
        workshop=findViewById(R.id.worksho);
        help = findViewById(R.id.hel);
        constribute=findViewById(R.id.constribute);
        mAuth = FirebaseAuth.getInstance();
        playerName = mAuth.getCurrentUser().getDisplayName();



        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeHomeActivity.this, TestSwiActivity.class);
                startActivity(i);
            }
        });

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeHomeActivity.this, WorkshopActivity.class);
                startActivity(i);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    private void UpdateConnectStatut() {
        final String his_name = mAuth.getCurrentUser().getDisplayName();
        final DatabaseReference connectionRef = FirebaseDatabase.getInstance().getReference("Users/"+his_name).child("connection");
        //   final DatabaseReference lastconnected = FirebaseDatabase.getInstance().getReference("Users").child("last connected");
        final DatabaseReference infoconnected = FirebaseDatabase.getInstance().getReference(".info/connected");

        infoconnected.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean connected = dataSnapshot.getValue(Boolean.class);
                if (connected)
                {
                    DatabaseReference con = connectionRef;
                    con.setValue(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
