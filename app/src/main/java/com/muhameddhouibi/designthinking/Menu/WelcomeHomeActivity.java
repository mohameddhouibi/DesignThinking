package com.muhameddhouibi.designthinking.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.GlobalBrainstorming.GloablBrainStorming;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.WorkshopActivity;
import com.muhameddhouibi.designthinking.YesNoQstActivity;

public class WelcomeHomeActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    ImageView introduction,workshop,help,constribute;
    String playerName ;
    TextView tx3 ;
    TextView txr2;

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
        tx3 = findViewById(R.id.tx3);
        tx3.setText(mAuth.getCurrentUser().getDisplayName());
        txr2=findViewById(R.id.txr2);

        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeHomeActivity.this, YesNoQstActivity.class);
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WelcomeHomeActivity.this, WorkshopActivity.class);
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GloablBrainStorming.class));
                overridePendingTransition(0,0);
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
//                    mAuth.getInstance().signOut();
//                    Intent i = new Intent(WelcomeHomeActivity.this, LoginActivity.class);
//                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
