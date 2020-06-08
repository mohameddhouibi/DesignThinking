package com.muhameddhouibi.designthinking.GlobalBrainstorming.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.muhameddhouibi.designthinking.R;

public class AddBrainActivity extends AppCompatActivity {

    CardView cardIdea , cardChalleng ;
    Button btn_addBrain,btn_addIdea,btn_conf,btn_Conf1,btnCancel,btn_cancel1;
    TextView idea_title , challenge_title , idea_sector,challenge_sector ,idea_problem,challenge_problem,idea_desc ,challeng_desc ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_brain);

        cardIdea = findViewById(R.id.add_idea);
        cardChalleng= findViewById(R.id.add_challenge);
        btn_addBrain= findViewById(R.id.Add_challenge);
        btn_addIdea= findViewById(R.id.Add_Idea);
        btn_conf= findViewById(R.id.conf);
        btn_Conf1= findViewById(R.id.conf_challenge);
        btnCancel= findViewById(R.id.annul);
        btn_cancel1= findViewById(R.id.annul_challenge);
        idea_title= findViewById(R.id.info1);
        challenge_title= findViewById(R.id.info1_challenge);
        idea_sector= findViewById(R.id.info3);
        challenge_sector= findViewById(R.id.info3_challenge);
        idea_problem= findViewById(R.id.info4);
        challenge_problem= findViewById(R.id.info4_challenge);
        idea_desc= findViewById(R.id.info2);
        challeng_desc= findViewById(R.id.info2_challenge);
    }
}
