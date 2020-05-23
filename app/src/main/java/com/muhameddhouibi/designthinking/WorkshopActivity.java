package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.Menu.FriendsActivity;
import com.muhameddhouibi.designthinking.Menu.MyGamesActivity;
import com.muhameddhouibi.designthinking.Menu.NotificationsActivity;
import com.muhameddhouibi.designthinking.Menu.ProfileActivity;
import com.muhameddhouibi.designthinking.Menu.WelcomeHomeActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WorkshopActivity extends AppCompatActivity  {

    RecyclerView recyclerView ;
    Button create_game ;
    FirebaseAuth mAuth;
    private FirebaseRecyclerOptions<Game> options;
    private FirebaseRecyclerAdapter<Game,MyGameViewHolder> adapter;
    DatabaseReference rooms ;
    FirebaseDatabase firebaseDatabase ;
    Button confbtn , annulbtn ;
    EditText info1 , info2 , info3 , info4 ;
    ImageView closeDialog ;
    Dialog Infodiaog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workshop);

        Infodiaog = new Dialog(this);


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


        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();

        rooms=FirebaseDatabase.getInstance().getReference("rooms");

        recyclerView = findViewById(R.id.listgames);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =new FirebaseRecyclerOptions.Builder<Game>().setQuery(rooms,Game.class).build();

        adapter=new FirebaseRecyclerAdapter<Game, MyGameViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyGameViewHolder holder, int position, @NonNull final Game model) {
               final String roomid = model.getRoom_id();
               final int nbrplayer = model.getNb_players();
               final String roomname = model.getRoomName();

               holder.btn_invite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(WorkshopActivity.this,TestGameActivity.class);
                        i.putExtra("roomid",""+roomid);
                        i.putExtra("roomname",""+roomname);
                        i.putExtra("nbr",""+nbrplayer);
                        startActivity(i);
                        overridePendingTransition(0,0);

                    }
                });
                holder.GameName.setText(""+model.getRoomName());
            }
            @NonNull
            @Override
            public MyGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game,parent,false);

                return new MyGameViewHolder(v);
            }
        };

        adapter.startListening();

        recyclerView.setAdapter(adapter);

        create_game = findViewById(R.id.creategame);


        create_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InformationAlertBuilder ();

            }
        });
    }

    private void InformationAlertBuilder ()
    {
        Infodiaog.setContentView(R.layout.create_game_popup);
        confbtn=(Button) Infodiaog.findViewById(R.id.conf);
        annulbtn=(Button) Infodiaog.findViewById(R.id.annul);
        info1 = (EditText) Infodiaog.findViewById(R.id.info1);
        info2 = (EditText) Infodiaog.findViewById(R.id.info2);
        info3 = (EditText) Infodiaog.findViewById(R.id.info3);
        info4 = (EditText) Infodiaog.findViewById(R.id.info4);
        closeDialog = (ImageView) Infodiaog.findViewById(R.id.close);


        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog.dismiss();
            }
        });

        annulbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog.dismiss();
            }
        });


        confbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (info1.isEmpty() || password.isEmpty()) {
//                    showMessage("Please Verify All Field");
//                    btnLogin.setVisibility(View.VISIBLE);
//                    loginProgress.setVisibility(View.INVISIBLE);
//                }
//                else
//                {
//                    signIn(mail,password);
//                }
//
                create_game.setText("Creating your room");
                create_game.setEnabled(false);
                final String Game_id= rooms.push().getKey();
                final String game_name = info1.getText().toString();
                final String nb = info3.getText().toString();
                int finalValue = Integer.parseInt(nb);


                final String Payer1_name =mAuth.getCurrentUser().getDisplayName();
                Game game = new Game(game_name,Game_id,Payer1_name,null,null,null,null,finalValue);
                rooms.child(game_name).setValue(game);
                Toast toast=Toast. makeText(getApplicationContext(),"Done !",Toast. LENGTH_SHORT);
                toast. show();
                Infodiaog.dismiss();



            }
        });
        Infodiaog.show();
    }
}
