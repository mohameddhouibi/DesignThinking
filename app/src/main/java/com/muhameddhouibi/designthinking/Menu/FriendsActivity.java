package com.muhameddhouibi.designthinking.Menu;

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.R;


public class FriendsActivity extends AppCompatActivity {
    String playerName;
    String playerId;
    FirebaseAuth mAuth;
    private FirebaseRecyclerOptions<User> options;
    private FirebaseRecyclerAdapter<User, NewViewHolder> adapter;
    private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference Users ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.friendsmenu);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.friendsmenu :
                        return true ;
                    case R.id.notificationsmenu :
                        startActivity(new Intent(getApplicationContext(), NotificationsActivity.class));
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
        firebaseDatabase = FirebaseDatabase.getInstance();
        Users=FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        playerName=mAuth.getCurrentUser().getDisplayName();
        playerId=mAuth.getCurrentUser().getUid();
        recyclerView = findViewById(R.id.recyclerConnectedUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        options =new FirebaseRecyclerOptions.Builder<User>().setQuery(Users,User.class).build();
        adapter = new FirebaseRecyclerAdapter<User, NewViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final NewViewHolder holder, int position, @NonNull final User model) {

                holder.userName.setText(""+model.getUserName());

            }

            @NonNull
            @Override
            public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user1,parent,false);
                return new NewViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}
