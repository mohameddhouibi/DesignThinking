package com.muhameddhouibi.designthinking.Steps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.ChatStepActivity;
import com.muhameddhouibi.designthinking.Discussions.Chat2Activity;
import com.muhameddhouibi.designthinking.Discussions.Chat4Activity;
import com.muhameddhouibi.designthinking.Instructions.Instruction2Activity;
import com.muhameddhouibi.designthinking.Instructions.Instruction4Activity;
import com.muhameddhouibi.designthinking.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;

public class Step4Activity extends AppCompatActivity {
    private SlidrInterface slidr ;
    TextView tii;
    Button Instructions ;
    Button discussion ;
    Button result ;
    String roomname ;
    String a1,a2,a3,a4,a5;
    FirebaseDatabase firebaseDatabase ;
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    DatabaseReference Discussions ;
    DatabaseReference playerreff1 ;
    DatabaseReference playerreff2 ;
    DatabaseReference playerreff3 ;
    DatabaseReference playerreff4 ;
    DatabaseReference playerreff5 ;
    ImageView redo_btn ;
    Button subBtn  ;
    TextView tx ;
    ImageView closeDialog ;
    Dialog Infodiaog ;
    RatingBar rat ;
    long tiLeft  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step4);
        Infodiaog = new Dialog(this);
        slidr = Slidr.attach(this);
        Instructions=findViewById(R.id.instructions);
        discussion=findViewById(R.id.Disscussion);
        result=findViewById(R.id.Final);
        redo_btn= findViewById(R.id.redo_btn);
        roomname =  getIntent().getStringExtra("discussion");
        tii=findViewById(R.id.tii);

        CountDownTimer countDownTimer = new CountDownTimer(900000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiLeft = millisUntilFinished ;
                updatetimer();
            }

            private void updatetimer() {
                int minutes = (int) (tiLeft / 60000);
                int seconds = (int) (tiLeft % 60000 / 1000);
                String TimeLeftText ;
                TimeLeftText =""+minutes+":"+seconds;
                tii.setText(TimeLeftText+seconds);
                if (seconds<10)
                    TimeLeftText =""+minutes+":0"+seconds;
                tii.setText(TimeLeftText);

            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();


        redo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Step4Activity.this, Step5Activity.class);
                i.putExtra("discussion",roomname);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Step4Activity.this, Instruction4Activity.class);
                i.putExtra("discussion",roomname+"Step5");
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });



        Toast toast = Toast. makeText(this,roomname , Toast.LENGTH_SHORT); toast. show();
        Discussions= FirebaseDatabase.getInstance().getReference("Discussions");
        mAuth = FirebaseAuth.getInstance();
        final String Discussion_id= Discussions.push().getKey();
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationAlertBuilder ();
            }
        });


        Discussions=FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("Step4").child("Discussion");
        mAuth = FirebaseAuth.getInstance();
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationAlertBuilder ();
            }
        });
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("discussionId",Discussion_id);
        hashMap.put("StepNum","4");
        hashMap.put("RoomName",roomname);
        Discussions.child("Step4").setValue(hashMap)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                playerreff1 =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("InRoom").child("Player1");
                                playerreff2 =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("InRoom").child("Player2");
                                playerreff3 =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("InRoom").child("Player3");
                                playerreff4 =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("InRoom").child("Player4");
                                playerreff5 =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("InRoom").child("Player5");
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


                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                                HashMap<String,String> hashMap1 = new HashMap<>();
                                hashMap1.put("Player1",a1);
                                hashMap1.put("Player2",a2);
                                hashMap1.put("Player3",a3);
                                hashMap1.put("Player4",a4);
                                hashMap1.put("Player5",a5);
                                Discussions.child("Step1").child("Participants").setValue(hashMap1);



                            }
                        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Step4Activity.this, Step5Activity.class);
                i.putExtra("discussion",roomname);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });


    }

    private void InformationAlertBuilder ()
    {
        Infodiaog.setContentView(R.layout.final_decision_popup);
        subBtn= Infodiaog.findViewById(R.id.confff);
        tx= Infodiaog.findViewById(R.id.tx);
        tx.setText("Step Two");
        rat=findViewById(R.id.rat);

        closeDialog = (ImageView) Infodiaog.findViewById(R.id.close);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog.dismiss();
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog.dismiss();
            }
        });
        Infodiaog.show();
    }


}
