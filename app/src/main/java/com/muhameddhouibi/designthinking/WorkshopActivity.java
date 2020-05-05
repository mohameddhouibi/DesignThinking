package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.Game;
import com.muhameddhouibi.designthinking.Entity.User;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workshop);


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
                create_game.setText("Creating your room");
                create_game.setEnabled(false);
                final String Game_id= rooms.push().getKey();
                final String game_name = mAuth.getCurrentUser().getDisplayName();
                final String Payer1_name =mAuth.getCurrentUser().getDisplayName();
                Game game = new Game(game_name,Game_id,Payer1_name,null,null,null,null,1);
                rooms.child(game_name).setValue(game);
            }
        });
    }


}
