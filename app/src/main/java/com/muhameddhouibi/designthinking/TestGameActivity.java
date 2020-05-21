package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import com.muhameddhouibi.designthinking.Entity.Game;
import com.muhameddhouibi.designthinking.Entity.Invitation;
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.Menu.FriendsActivity;
import com.muhameddhouibi.designthinking.Menu.MyGamesActivity;
import com.muhameddhouibi.designthinking.Menu.NotificationsActivity;
import com.muhameddhouibi.designthinking.Menu.ProfileActivity;
import com.muhameddhouibi.designthinking.Menu.WelcomeHomeActivity;
import com.muhameddhouibi.designthinking.Steps.Step1Activity;

public class TestGameActivity extends AppCompatActivity {
    Button ready;
    String playerName;
    String playerId;
    FirebaseAuth mAuth;
    private FirebaseRecyclerOptions<User> options;
    private FirebaseRecyclerAdapter<User,MyViewHolder>adapter;
    private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference Users ;
    DatabaseReference invitationReff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_game);
        //btn_test =findViewById(R.id.bt_test);


        // Bottom Navigation //
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.homemenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.notificationsmenu :
                        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
                        overridePendingTransition(0,0);
                        return true ;                    case R.id.friendsmenu :
                        startActivity(new Intent(getApplicationContext(), FriendsActivity.class));
                        overridePendingTransition(0,0);
                        return true ;
                    case R.id.homemenu :
                        startActivity(new Intent(getApplicationContext(), WelcomeHomeActivity.class));
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
        //end Bottom Navigation //



        final String roomid = getIntent().getStringExtra("roomname");

        firebaseDatabase = FirebaseDatabase.getInstance();
        Users=FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        playerName=mAuth.getCurrentUser().getDisplayName();
        playerId=mAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recyclerConnectedUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        options =new FirebaseRecyclerOptions.Builder<User>().setQuery(Users,User.class).build();
        adapter = new FirebaseRecyclerAdapter<User, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyViewHolder holder, int position, @NonNull final User model) {

                holder.btn_invite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i ;
                        final String reciever =model.getUserName();
                        final String sender =mAuth.getCurrentUser().getDisplayName();
                        invitationReff=FirebaseDatabase.getInstance().getReference("Invitation:"+reciever);
                        holder.btn_invite.setText("Invited");
                        holder.btn_invite.setEnabled(false);
                        final String invitation_id= invitationReff.push().getKey();
                        Invitation invitation = new Invitation(invitation_id,roomid,sender,reciever);
                        invitationReff.child(invitation_id).setValue(invitation);

                    }
                });
                holder.userName.setText(""+model.getUserName());

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

             View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        ready=findViewById(R.id.ready);
        ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TestGameActivity.this, RooActivity.class);
                i.putExtra("room",roomid);
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });

    }

}
