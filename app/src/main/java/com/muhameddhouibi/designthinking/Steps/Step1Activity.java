package com.muhameddhouibi.designthinking.Steps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.R;

import java.util.HashMap;

public class Step1Activity extends AppCompatActivity {
    Button Instructions ;
    Button discussion ;
    Button result ;
    String player1,player2,player3,player4,player5,roomname;
    FirebaseDatabase firebaseDatabase ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    DatabaseReference Discussions ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        Instructions=findViewById(R.id.instructions);
        discussion=findViewById(R.id.Disscussion);
        result=findViewById(R.id.Final);
        player1 =  getIntent().getStringExtra("player1");
        player2 =  getIntent().getStringExtra("player2");
        player3 =  getIntent().getStringExtra("player3");
        player4 =  getIntent().getStringExtra("player4");
        player5 =  getIntent().getStringExtra("player5");
        roomname =  getIntent().getStringExtra("roomName");
        Toast toast = Toast. makeText(this,player2 , Toast.LENGTH_SHORT); toast. show();
        Discussions=FirebaseDatabase.getInstance().getReference("Discussions");
        mAuth = FirebaseAuth.getInstance();
        final String Discussion_id= Discussions.push().getKey();
        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("discussionId",Discussion_id);
                hashMap.put("StepNum","1");
                hashMap.put("RoomName",roomname);
                Discussions.child(Discussion_id).setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                HashMap<String,String> hashMap1 = new HashMap<>();
                                hashMap1.put("Player1",player1);
                                hashMap1.put("Player2",player2);
                                hashMap1.put("Player3",player3);
                                hashMap1.put("Player4",player4);
                                hashMap1.put("Player5",player5);
                                Discussions.child(Discussion_id).child("Participants").setValue(hashMap1);
                            }
                        });
            }
        });
    }
}
