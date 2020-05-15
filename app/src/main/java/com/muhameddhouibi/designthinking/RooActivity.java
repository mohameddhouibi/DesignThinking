package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.Steps.Step1Activity;

import org.w3c.dom.Text;

public class RooActivity extends AppCompatActivity {

    Button start;
    String playerName;
    String RoomNameFinal ;

    RelativeLayout r1,r2,r3,r4,r5 ;
    TextView tx1,tx2,tx3,tx4,tx5;
    FirebaseAuth mAuth;
    private FirebaseRecyclerOptions<String> options;
    private FirebaseRecyclerAdapter<String,MyViewHolder> adapter;
    private RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference UsersName ;
    DatabaseReference playerreff2 ;
    DatabaseReference playerreff3 ;
    DatabaseReference playerreff4 ;
    DatabaseReference playerreff5 ;
    FirebaseUser currentUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roo);
        mAuth = FirebaseAuth.getInstance();

        final String roomnamea = getIntent().getStringExtra("room");
        final String roomnamea2 = getIntent().getStringExtra("roo");
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
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (RooActivity.this, Step1Activity.class);
                if(roomnamea == null){
                    RoomNameFinal = roomnamea2 ;
                }
                else if (roomnamea2 == null){
                    RoomNameFinal = roomnamea ;
                }
                i.putExtra("roomName",RoomNameFinal);
                startActivity(i);
            }
        });

        r1.setVisibility(View.VISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        r4.setVisibility(View.INVISIBLE);
        r5.setVisibility(View.INVISIBLE);

        currentUser = mAuth.getCurrentUser();

        tx1.setText(currentUser.getDisplayName());

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (!(roomnamea == null)) {
            playerreff2 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea + "/player"+2);
            playerreff3 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea + "/player"+3);
            playerreff4 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea + "/player"+4);
            playerreff5 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea + "/player"+5);
            Toast toast=Toast. makeText(getApplicationContext(),roomnamea + "1",Toast. LENGTH_SHORT);
            toast. show();
        }else if (!(roomnamea2 == null)) {
            playerreff2 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea2 + "/player"+2);
            playerreff3 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea2 + "/player"+3);
            playerreff4 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea2 + "/player"+4);
            playerreff5 =FirebaseDatabase.getInstance().getReference("rooms/" + roomnamea2 + "/player"+5);
            Toast toast=Toast. makeText(getApplicationContext(),roomnamea2 + "2",Toast. LENGTH_SHORT);
            toast. show();
        }






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





    }






    @Override
    protected void onStart() {
        super.onStart();
    }
}
