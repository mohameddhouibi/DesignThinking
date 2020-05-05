package com.muhameddhouibi.designthinking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RooActivity extends AppCompatActivity {

    ImageView cer,cer2;
    TextView txt_wait ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roo);
        cer=findViewById(R.id.cercle);
        cer2=findViewById(R.id.cercle2);
        txt_wait=findViewById(R.id.wait);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
