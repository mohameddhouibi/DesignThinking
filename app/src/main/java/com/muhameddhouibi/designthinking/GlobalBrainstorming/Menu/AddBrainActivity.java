package com.muhameddhouibi.designthinking.GlobalBrainstorming.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.Entity.Idea;
import com.muhameddhouibi.designthinking.GlobalBrainstorming.GloablBrainStorming;
import com.muhameddhouibi.designthinking.R;

public class AddBrainActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    FirebaseAuth mAuth;


    CardView cardIdea , cardChalleng ;
    Button btn_addBrain,btn_addIdea,btn_conf,btn_Conf1,btnCancel,btn_cancel1;
    TextView idea_title , challenge_title , idea_sector,challenge_sector ,idea_problem,challenge_problem,idea_desc ,challeng_desc ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brain);
        mAuth = FirebaseAuth.getInstance();

        cardIdea = findViewById(R.id.add_idea);
        cardChalleng= findViewById(R.id.add_challenge);
        btn_addBrain= findViewById(R.id.Add_challenge);
        btn_addIdea= findViewById(R.id.Add_Idea);
        btn_conf= findViewById(R.id.conf);
        btn_Conf1= findViewById(R.id.conf_challenge);
        btnCancel= findViewById(R.id.annul);
        btn_cancel1= findViewById(R.id.annul_challenge);
        idea_title= findViewById(R.id.info1);
        challenge_title= findViewById(R.id.info1_challenge);
        idea_sector= findViewById(R.id.info3);
        challenge_sector= findViewById(R.id.info3_challenge);
        idea_problem= findViewById(R.id.info4);
        challenge_problem= findViewById(R.id.info4_challenge);
        idea_desc= findViewById(R.id.info2);
        challeng_desc= findViewById(R.id.info2_challenge);
        btn_addBrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardChalleng.setVisibility(View.VISIBLE);
                cardIdea.setVisibility(View.GONE);
            }
        });
        btn_addIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardChalleng.setVisibility(View.GONE);
                cardIdea.setVisibility(View.VISIBLE);
            }
        });
        
        btn_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ideas");
                String ideatitle = idea_title.getText().toString();
                String ideasector = idea_sector.getText().toString();
                String ideaproblem = idea_problem.getText().toString();
                final String Room_id= reference.push().getKey();
                String timestamp = ""+System.currentTimeMillis();
                String ideadesc = idea_desc.getText().toString();
                String User = mAuth.getCurrentUser().getDisplayName();
                Idea idea = new Idea(Room_id,ideasector,ideaproblem,ideatitle,timestamp,User);
                reference.child(Room_id).setValue(idea);
                Toast.makeText(AddBrainActivity.this, "done ", Toast.LENGTH_SHORT).show();


            }
        });


        // Bottom Navigation //
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.addmenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.homemenu :
                        startActivity(new Intent(getApplicationContext(), GloablBrainStorming.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.Searchmenu :
                        startActivity(new Intent(getApplicationContext(), SearchBrainActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.friendsmenu :
                        startActivity(new Intent(getApplicationContext(), FriendsBrainActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.profilemenu :
                        startActivity(new Intent(getApplicationContext(), ProfileBrainActivity.class));
                        overridePendingTransition(0,0);
                        return true ;

                }
                return false;
            }
        });
        //end Bottom Navigation //


    }
}
