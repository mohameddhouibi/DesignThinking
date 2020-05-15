package com.muhameddhouibi.designthinking.Steps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.muhameddhouibi.designthinking.R;

public class Step1Activity extends AppCompatActivity {

    Button Instructions ;
    Button discussion ;
    Button result ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step1);

        Instructions=findViewById(R.id.instructions);
        discussion=findViewById(R.id.Disscussion);
        result=findViewById(R.id.Final);
    }
}
