package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.muhameddhouibi.designthinking.Entity.Room;
import com.muhameddhouibi.designthinking.Menu.FriendsActivity;
import com.muhameddhouibi.designthinking.Menu.MyGamesActivity;
import com.muhameddhouibi.designthinking.Menu.NotificationsActivity;
import com.muhameddhouibi.designthinking.Menu.ProfileActivity;
import com.muhameddhouibi.designthinking.Menu.WelcomeHomeActivity;
import com.muhameddhouibi.designthinking.Phases.Ideation;
import com.muhameddhouibi.designthinking.ViewHolders.MyViewHolder;

public class RooActivity extends AppCompatActivity {

    Button start;
    String playerName;
    String RoomNameFinal ;
    String a1,a2,a3,a4,a5;
    RelativeLayout r1,r2,r3,r4,r5 ;
    TextView tx1,tx2,tx3,tx4,tx5;
    FirebaseAuth mAuth;
    private FirebaseRecyclerOptions<String> options;
    private FirebaseRecyclerAdapter<String, MyViewHolder> adapter;
    private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference FinalRoom ,Workshop ;
    DatabaseReference playerreff1 ;
    DatabaseReference playerreff2 ;
    DatabaseReference playerreff3 ;
    DatabaseReference playerreff4 ;
    DatabaseReference playerreff5 , invitationReff2;
    FirebaseUser currentUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roo);
        mAuth = FirebaseAuth.getInstance();


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


        final String roomnamea = getIntent().getStringExtra("room");
        final String roomnamea2 = getIntent().getStringExtra("roo");
        final String privacy = getIntent().getStringExtra("privacy");
        final String this_player = getIntent().getStringExtra("thisPlayer");
        r1=findViewById(R.id.item);
        r2=findViewById(R.id.item2);
        r3=findViewById(R.id.item22);
        r4=findViewById(R.id.item3);
        r5=findViewById(R.id.item4);

        tx1=findViewById(R.id.UserName_connected);
        tx2=findViewById(R.id.UserName_connected1);
        tx3=findViewById(R.id.UserName_connected2);
        tx4=findViewById(R.id.UserName_connected3);
        tx5=findViewById(R.id.UserName_connected4);
        start  = findViewById(R.id.ready);


        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        r4.setVisibility(View.INVISIBLE);
        r5.setVisibility(View.INVISIBLE);

        currentUser = mAuth.getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (!(roomnamea == null)) {

            playerreff1 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                    .child("Join1");
            playerreff2 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                    .child("Join2");
            playerreff3 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                    .child("Join3");
            playerreff4 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                    .child("Join4");
            playerreff5 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                    .child("Join5");


            //

        }else if (!(roomnamea2 == null)) {
            playerreff1 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                    .child("Join1");
            playerreff2 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                    .child("Join2");
            playerreff3 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                    .child("Join3");
            playerreff4 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                    .child("Join4");
            playerreff5 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                    .child("Join5");

        }

        playerreff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String value2 = dataSnapshot.getValue(String.class);
                if (value2 == null)
                {
                }
                else
                {
                    r1.setVisibility(View.VISIBLE);
                    tx1.setText(value2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        playerreff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String value2 = dataSnapshot.getValue(String.class);
                    if (value2 == null)
                    {

                    }
                    else
                     {
                         r2.setVisibility(View.VISIBLE);
                         tx2.setText(value2);
                     }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        playerreff3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String value2 = dataSnapshot.getValue(String.class);
                if (value2 == null)
                {

                }
                else
                {
                    r3.setVisibility(View.VISIBLE);
                    tx3.setText(value2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        playerreff4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String value2 = dataSnapshot.getValue(String.class);
                if (value2 == null)
                {

                }
                else
                {
                    r4.setVisibility(View.VISIBLE);
                    tx4.setText(value2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        playerreff5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final String value2 = dataSnapshot.getValue(String.class);
                if (value2 == null)
                {

                }
                else
                {
                    r5.setVisibility(View.VISIBLE);
                    tx5.setText(value2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(roomnamea == null){
                    RoomNameFinal = roomnamea2 ;
                }
                else if (roomnamea2 == null){
                    RoomNameFinal = roomnamea ;
                }
                Workshop =FirebaseDatabase.getInstance().getReference("Workshops").child(RoomNameFinal);
                Workshop.child("Room_Name").setValue(RoomNameFinal);

                if (!(roomnamea == null)) {
                    playerreff1 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                            .child("Join1");
                    playerreff2 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                            .child("Join2");
                    playerreff3 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                            .child("Join3");
                    playerreff4 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                            .child("Join4");
                    playerreff5 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea)
                            .child("Join5");
                }else if (!(roomnamea2 == null)) {
                    playerreff1 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                            .child("Join1");
                    playerreff2 = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                            .child("Join2");
                    playerreff3 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                            .child("Join3");
                    playerreff4 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                            .child("Join4");
                    playerreff5 =FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("InRoom").child(roomnamea2)
                            .child("Join5");
                    invitationReff2  =FirebaseDatabase.getInstance().getReference("Invitations").child(this_player);
                    invitationReff2.removeValue();
                    invitationReff2  =FirebaseDatabase.getInstance().getReference("Invitations").child(roomnamea2);
                    invitationReff2.removeValue();

                }
                playerreff1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {

                        }
                        else
                        {
                            a1=value2;
                            Workshop.child("InRoom").child("Player1").setValue(a1);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                playerreff2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {

                        }
                        else
                        {
                            a2=value2;
                            Workshop.child("InRoom").child("Player2").setValue(a2);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                playerreff3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {

                        }
                        else
                        {
                            a3=value2;
                            Workshop.child("InRoom").child("Player3").setValue(a3);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                playerreff4.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {

                        }
                        else
                        {
                            a4=value2;
                            Workshop.child("InRoom").child("Player4").setValue(a3);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                playerreff5.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {

                        }
                        else
                        {
                            a5=value2;
                            Workshop.child("InRoom").child("Player5").setValue(a5);


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                final Intent i = new Intent (RooActivity.this, Ideation.class);

                FinalRoom = FirebaseDatabase.getInstance().getReference("Rooms").child(privacy).child("General").child(RoomNameFinal);
                FinalRoom.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final Room value2 = dataSnapshot.getValue(Room.class);
                        if (value2 == null)
                        {
                            i.putExtra("player1",currentUser.getDisplayName());
                            i.putExtra("roomName",RoomNameFinal);
                            i.putExtra("privacy",privacy);
                            startActivity(i);
                            overridePendingTransition(0,0);
                        }
                        else
                        {
                            FinalRoom.removeValue();
                            i.putExtra("player1",currentUser.getDisplayName());
                            i.putExtra("roomName",RoomNameFinal);
                            i.putExtra("privacy",privacy);
                            startActivity(i);
                            overridePendingTransition(0,0);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });



    }






    @Override
    protected void onStart() {
        super.onStart();
    }
}
