package com.muhameddhouibi.designthinking.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Home2Activity;
import com.muhameddhouibi.designthinking.LoginActivity;
import com.muhameddhouibi.designthinking.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgProfile ;
    TextView UsernameProfile ;
    TextView UserEmailProfile ;
    Button btnlogout;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        imgProfile=findViewById(R.id.profile_photo);
        UsernameProfile=findViewById(R.id.username_profile);
        UserEmailProfile = findViewById(R.id.email_profile);
        btnlogout = findViewById(R.id.btlogout);


        if (currentUser != null)
        {
            UsernameProfile.setText(currentUser.getDisplayName());
            UserEmailProfile.setText(currentUser.getEmail());
            Glide.with(this).load(currentUser.getPhotoUrl()).into(imgProfile);
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.profilemenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.profilemenu :
                        return true ;
                    case R.id.notificationsmenu :
                        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.homemenu :
                        startActivity(new Intent(getApplicationContext(), WelcomeHomeActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.friendsmenu :
                        startActivity(new Intent(getApplicationContext(), FriendsActivity.class));
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

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateConnectionStatut();
            }
        });


    }
    private void UpdateConnectionStatut() {
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
                    con.setValue(false);
                    mAuth.getInstance().signOut();
                    Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
