package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.ChatGroup;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatStepActivity extends AppCompatActivity {

    ImageButton attach,send ;
    EditText messageEt ;
    String discussion ;
    FirebaseAuth mAuth;
    RecyclerView ChatRv ;
    ArrayList<ChatGroup> chatGroups;
    AdapterGroupChat adapterGroupChat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        discussion  = getIntent().getStringExtra("discussion");
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_chat_step);
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
                    Toast.makeText(ChatStepActivity.this,discussion,Toast.LENGTH_SHORT).show();
                }else
                {
                    sendMessage(message);
                }
            }
        });
    }

    private void loadGroupeMessages() {

        chatGroups = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Discussions");
        ref.child(discussion).child("Messages")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        chatGroups.clear();
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            ChatGroup chatGroup = ds.getValue(ChatGroup.class);
                            chatGroups.add(chatGroup);
                        }
                        adapterGroupChat = new AdapterGroupChat(ChatStepActivity.this,chatGroups);
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
        hashMap.put("sender",""+mAuth.getCurrentUser().getDisplayName());
        hashMap.put("message",""+message);
        hashMap.put("timestamp",""+timestamp);
        hashMap.put("typee",""+"Text");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Discussions");
        ref.child(discussion).child("Messages").child(timestamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });



    }


}
