package com.muhameddhouibi.designthinking.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.Game;
import com.muhameddhouibi.designthinking.Entity.Invitation;
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.MyInvitationViewHolder;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.RooActivity;
import com.muhameddhouibi.designthinking.TestGameActivity;

public class NotificationsActivity extends AppCompatActivity {
    private FirebaseRecyclerOptions<Invitation> options;
    private FirebaseRecyclerAdapter<Invitation, MyInvitationViewHolder> adapter;
    DatabaseReference invitations ;
    DatabaseReference playerreff2 ;
    DatabaseReference playerreff3 ;
    DatabaseReference playerreff4 ;
    DatabaseReference playerreff5 ;
    DatabaseReference gamereff ;

    FirebaseDatabase firebaseDatabase ;
    FirebaseAuth mAuth;
    String playerName;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();


        // Bottom Navigation //
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.notificationsmenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.notificationsmenu :
                        return true ;
                    case R.id.friendsmenu :
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
        playerName=mAuth.getCurrentUser().getDisplayName();
        invitations=FirebaseDatabase.getInstance().getReference("Invitation:" + playerName );
        recyclerView = findViewById(R.id.recyclerinvitations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        options =new FirebaseRecyclerOptions.Builder<Invitation>().setQuery(invitations,Invitation.class).build();
        adapter = new FirebaseRecyclerAdapter<Invitation, MyInvitationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyInvitationViewHolder holder, int position, @NonNull final Invitation model) {

                    holder.descri.setText("eeee");
                    final String inv_id = model.getInvitation_id();
                    holder.decline.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                DatabaseReference GestInvi=FirebaseDatabase.getInstance().getReference("Invitations").child(playerName).child(inv_id);
                                GestInvi.removeValue();

                        }
                    });
                    holder.btn_invite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int i ;
                            final String roomname = model.getGame_id();

                            playerreff2 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+2);
                            playerreff3 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+3);
                            playerreff4 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+4);
                            playerreff5 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+5);
                            gamereff =FirebaseDatabase.getInstance().getReference("rooms/" + roomname);


                            playerreff2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot ) {
                                    // This method is called once with the initial value and again
                                    // whenever data at this location is updated.
                                    final String value2 = dataSnapshot.getValue(String.class);
                                    if (value2==null )
                                    {
                                        playerreff2.setValue(model.getDesitination_uid());
                                        holder.btn_invite.setEnabled(false);
                                        Intent i = new Intent(NotificationsActivity.this, RooActivity.class);
                                        i.putExtra("roo",roomname);
                                        startActivity(i);
                                        overridePendingTransition(0,0);

                                        DatabaseReference GestInvi=FirebaseDatabase.getInstance().getReference("Invitation:" + playerName).child(inv_id);
                                        GestInvi.removeValue();
                                    }else
                                    {
                                        playerreff3.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            final String value3 = dataSnapshot.getValue(String.class);
                                            if (value3==null && model.getDesitination_uid() !=value2 )
                                            {
                                                playerreff3.setValue(model.getDesitination_uid());
                                                holder.btn_invite.setEnabled(false);
                                                Intent i = new Intent(NotificationsActivity.this, RooActivity.class);
                                                i.putExtra("roo",roomname);
                                                startActivity(i);
                                                overridePendingTransition(0,0);

                                                DatabaseReference GestInvi=FirebaseDatabase.getInstance().getReference("Invitation:" + playerName).child(inv_id);
                                                GestInvi.removeValue();
                                            } else if(value3 !=model.getDesitination_uid() || value2 == value3)
                                            {
                                                holder.btn_invite.setEnabled(false);
                                                playerreff4.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                        final String value4 = dataSnapshot.getValue(String.class);
                                                        if (value4==null && model.getDesitination_uid() !=value2 && model.getDesitination_uid() !=value3 )
                                                        {
                                                            playerreff4.setValue(model.getDesitination_uid());
                                                            holder.btn_invite.setEnabled(false);
                                                            Intent i = new Intent(NotificationsActivity.this, RooActivity.class);
                                                            i.putExtra("roo",roomname);
                                                            startActivity(i);
                                                            overridePendingTransition(0,0);

                                                            DatabaseReference GestInvi=FirebaseDatabase.getInstance().getReference("Invitation:" + playerName).child(inv_id);
                                                            GestInvi.removeValue();
                                                        }else if (value4 !=model.getDesitination_uid() && model.getDesitination_uid() !=value2 && model.getDesitination_uid() !=value3)
                                                        {
                                                            playerreff5.setValue(model.getDesitination_uid());
                                                            holder.btn_invite.setEnabled(false);
                                                            Intent i = new Intent(NotificationsActivity.this, RooActivity.class);
                                                            i.putExtra("roo",roomname);
                                                            startActivity(i);
                                                            overridePendingTransition(0,0);

                                                            DatabaseReference GestInvi=FirebaseDatabase.getInstance().getReference("Invitation:" + playerName).child(inv_id);
                                                            GestInvi.removeValue();
                                                        }



                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError error) {
                                                        // Failed to read value
                                                    }
                                                });


                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                        }
                                    });

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError error) {
                                }

                            });


                        }
                    });
            }
            @NonNull
            @Override
            public MyInvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false);
                return new MyInvitationViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    }