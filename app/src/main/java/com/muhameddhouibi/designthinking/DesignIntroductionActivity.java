package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.StackView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.Question;

import java.util.ArrayList;
import java.util.List;

public class DesignIntroductionActivity extends AppCompatActivity {

    StackView stackview ;
    DatabaseReference databaseqsts ;
    private FirebaseAuth mAuth;
    List<Question> Questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_introduction);
        databaseqsts= FirebaseDatabase.getInstance().getReference("YesNoQuestions");
        mAuth = FirebaseAuth.getInstance();
        stackview = findViewById(R.id.stackview);

        InstructionAdapter adaperinstruction = new InstructionAdapter(numberWord(),numberImage(),R.layout.item_instruction,DesignIntroductionActivity.this);
        stackview.setAdapter(adaperinstruction);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        databaseqsts.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                word.clear();
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //getting artist
//                    Question qst = postSnapshot.getValue(Question.class);
//                    //adding artist to the list
//                    String qstt = qst.getQuestion();
//                    word.add(qstt);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    private List<String>numberWord()
    {
        List<String> word =new ArrayList<>();
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");
        word.add("ddddd");


        return word ;

    }
    private List<Integer>numberImage()
    {
        List<Integer>Image=new ArrayList<>();
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);
        Image.add(R.drawable.yesno);


        return Image ;

    }
}
