package com.muhameddhouibi.designthinking.Discussions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.AdapterGroupChat;
import com.muhameddhouibi.designthinking.Entity.ChatGroup;
import com.muhameddhouibi.designthinking.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat7Activity extends AppCompatActivity {
    private ImageButton attach,send ;
    private EditText messageEt ;
    private String discussion ;
    private FirebaseAuth mAuth;
    private RecyclerView ChatRv ;
    private ArrayList<ChatGroup> chatGroupList =new ArrayList<>();
    private ArrayList<ChatGroup> chatGroupListtest =new ArrayList<>();
    private AdapterGroupChat adapterGroupChat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat5);
        discussion  = getIntent().getStringExtra("discussion");
        mAuth = FirebaseAuth.getInstance();
        loadGroupeMessages();

        attach=findViewById(R.id.attch_btn);
        send=findViewById(R.id.send_btn);
        messageEt=findViewById(R.id.messageEt);
        ChatRv=findViewById(R.id.ChatRv);
        loadGroupeMessages();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEt.getText().toString().trim();
                if(TextUtils.isEmpty(message))
                {
                    Toast.makeText(Chat7Activity.this,discussion,Toast.LENGTH_SHORT).show();
                }else
                {
                    sendMessage(message);

                    messageEt.setText("");
                }
            }
        });
    }

    private void loadGroupeMessages() {

        chatGroupList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Discussions");
        ref.child(discussion).child("Messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            ChatGroup chatGroup = ds.getValue(ChatGroup.class);
                            chatGroupList.add(chatGroup);
                        }
                        adapterGroupChat = new AdapterGroupChat(Chat7Activity.this,chatGroupList);
                        ChatRv.setAdapter(adapterGroupChat);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    private void sendMessage(String message)
    {
        String timestamp = ""+System.currentTimeMillis();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("sender",mAuth.getCurrentUser().getDisplayName());
        hashMap.put("message",message);
        hashMap.put("timestamp",timestamp);
        hashMap.put("typee","text");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Discussions");
        ref.child(discussion).child("Messages").child(timestamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Chat7Activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }


}
