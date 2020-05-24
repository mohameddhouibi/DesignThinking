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
import com.muhameddhouibi.designthinking.Discussions.Chat5Activity;
import com.muhameddhouibi.designthinking.Discussions.Chat6Activity;
import com.muhameddhouibi.designthinking.Discussions.Chat7Activity;
import com.muhameddhouibi.designthinking.Instructions.Instruction5Activity;
import com.muhameddhouibi.designthinking.Instructions.Instruction6Activity;
import com.muhameddhouibi.designthinking.Phases.Ideation;
import com.muhameddhouibi.designthinking.Phases.Inspiration;
import com.muhameddhouibi.designthinking.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import java.util.HashMap;

public class Step6Activity extends AppCompatActivity {
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
        setContentView(R.layout.activity_step6);
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
                Intent i = new Intent(Step6Activity.this, Inspiration.class);
                i.putExtra("discussion",roomname);
                startActivity(i);
                overridePendingTransition(0,0);
            }
        });

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Step6Activity.this, Instruction6Activity.class);
                i.putExtra("discussion",roomname+"Step5");
                startActivity(i);
                overridePendingTransition(0,0);

            }
        });



        Toast toast = Toast. makeText(this,roomname , Toast.LENGTH_SHORT); toast. show();
        Discussions=FirebaseDatabase.getInstance().getReference("Discussions");
        mAuth = FirebaseAuth.getInstance();
        final String Discussion_id= Discussions.push().getKey();
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationAlertBuilder ();
            }
        });


        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("discussionId",Discussion_id);
                hashMap.put("StepNum","1");
                hashMap.put("RoomName",roomname);
                Discussions.child(roomname+"Step6").setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                playerreff1 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+1);
                                playerreff2 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+2);
                                playerreff3 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+3);
                                playerreff4 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+4);
                                playerreff5 =FirebaseDatabase.getInstance().getReference("rooms/" + roomname + "/player"+5);
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
                                Discussions.child(roomname+"Step5").child("Participants").setValue(hashMap1);
                                Intent i = new Intent(Step6Activity.this, Chat6Activity.class);
                                i.putExtra("discussion",roomname+"Step6");
                                startActivity(i);
                                overridePendingTransition(0,0);


                            }
                        });
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
