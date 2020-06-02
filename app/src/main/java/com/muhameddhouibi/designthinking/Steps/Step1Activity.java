package com.muhameddhouibi.designthinking.Steps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TaskInfo;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.muhameddhouibi.designthinking.Entity.Game;
import com.muhameddhouibi.designthinking.Entity.Step;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.TestSwiActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;

public class Step1Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_step1);
        Infodiaog = new Dialog(this);
        slidr = Slidr.attach(this);
        Instructions=findViewById(R.id.instructions);
        discussion=findViewById(R.id.Disscussion);
        result=findViewById(R.id.Final);
        redo_btn= findViewById(R.id.redo_btn);
        roomname =  getIntent().getStringExtra("roomName");

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
                Intent i = new Intent(Step1Activity.this, Step2Activity.class);
                i.putExtra("discussion",roomname);
                startActivity(i);
                overridePendingTransition(3,3);
            }
        });

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Step1Activity.this, TestSwiActivity.class);
                i.putExtra("discussion",roomname+"Step1");
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });


        Toast toast = Toast. makeText(this,roomname , Toast.LENGTH_SHORT); toast. show();
        Discussions=FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("Step1").child("Discussion");
        mAuth = FirebaseAuth.getInstance();
        final String Discussion_id= Discussions.push().getKey();
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationAlertBuilder ();
            }
        });
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("discussionId",Discussion_id);
        hashMap.put("StepNum","1");
        hashMap.put("RoomName",roomname);
        Discussions.child("Step1").setValue(hashMap)
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
                Intent i = new Intent(Step1Activity.this, ChatStepActivity.class);
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
        rat=findViewById(R.id.rat);

        closeDialog = (ImageView) Infodiaog.findViewById(R.id.close);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Infodiaog.dismiss();
            }
        });

        DatabaseReference rrf =FirebaseDatabase.getInstance().getReference("Workshops").child(roomname).child("Step1")
                .child("FinalDecision");
        rrf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        final String value2 = dataSnapshot.getValue(String.class);
                        if (value2 == null)
                        {
                            tx.setText("You didnt set the final decision yet !");
                        }
                        else
                        {
                            tx.setText(value2);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
