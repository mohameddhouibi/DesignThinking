package com.muhameddhouibi.designthinking.Steps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.muhameddhouibi.designthinking.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class Step2Activity extends AppCompatActivity {
    private SlidrInterface slidr ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step2);
        slidr = Slidr.attach(this);

    }
}
