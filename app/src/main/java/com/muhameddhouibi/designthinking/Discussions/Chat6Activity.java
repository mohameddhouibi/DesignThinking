package com.muhameddhouibi.designthinking.Discussions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.muhameddhouibi.designthinking.ChatStepActivity;
import com.muhameddhouibi.designthinking.Entity.ChatGroup;
import com.muhameddhouibi.designthinking.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat6Activity extends AppCompatActivity {
    private ImageButton attach,send ;
    private EditText messageEt ;
    private String discussion ;
    private FirebaseAuth mAuth;
    private RecyclerView ChatRv ;
    private ArrayList<ChatGroup> chatGroupList =new ArrayList<>();
    private ArrayList<ChatGroup> chatGroupListtest =new ArrayList<>();
    private AdapterGroupChat adapterGroupChat ;
    private Button tutor , finalDecision ,confbtn1 ,annulbtn2;
    EditText info87 ;
    Dialog Infodiaog,Infodiaog2 ;
    ImageView closeDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat6);
        discussion  = getIntent().getStringExtra("discussion");
        mAuth = FirebaseAuth.getInstance();
        loadGroupeMessages();

        attach=findViewById(R.id.attch_btn);
        send=findViewById(R.id.send_btn);
        messageEt=findViewById(R.id.messageEt);
        ChatRv=findViewById(R.id.ChatRv);
        loadGroupeMessages();

        Infodiaog = new Dialog(this);
        Infodiaog2 = new Dialog(this);

        tutor = findViewById(R.id.tutor);
        finalDecision = findViewById(R.id.set);
        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////
                Toast.makeText(Chat6Activity.this, "Tutor is in his way to join the discussion wait for him! ", Toast.LENGTH_LONG).show();

            }
        });
        finalDecision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog2.setContentView(R.layout.final_decisionn);

                confbtn1=(Button) Infodiaog2.findViewById(R.id.conf);
                annulbtn2=(Button) Infodiaog2.findViewById(R.id.annul);
                info87 = (EditText)Infodiaog2.findViewById(R.id.code_ui);
                closeDialog1 = (ImageView) Infodiaog2.findViewById(R.id.close);
                closeDialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Infodiaog2.dismiss();
                    }
                });
                annulbtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Infodiaog2.dismiss();
                    }
                });
                confbtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference rrf =FirebaseDatabase.getInstance().getReference("Workshops").child(discussion).child("Step1")
                                .child("FinalDecision");;


                        final String aaa = info87.getText().toString();
                        if(aaa.equals(""))
                        {
                            Toast.makeText(Chat6Activity.this, "can't add an Empty value ! ", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            rrf.setValue(aaa);
                            Toast.makeText(Chat6Activity.this, "Final decission added successfully :) ", Toast.LENGTH_SHORT).show();
                            Infodiaog2.dismiss();
                        }
                    }
                });
                Infodiaog2.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEt.getText().toString().trim();
                if(TextUtils.isEmpty(message))
                {
                    Toast.makeText(Chat6Activity.this,discussion,Toast.LENGTH_SHORT).show();
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
                        chatGroupList.clear();

                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            ChatGroup chatGroup = ds.getValue(ChatGroup.class);
                            chatGroupList.add(chatGroup);
                        }
                        adapterGroupChat = new AdapterGroupChat(Chat6Activity.this,chatGroupList);
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
                Toast.makeText(Chat6Activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



    }


}
