package com.muhameddhouibi.designthinking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

import java.util.List;

class InstructionAdapter extends ArrayAdapter {
    List<Integer>numberImage ;
    List<String>numbreWord;
    Context c ;
    int itemlayout;



    public InstructionAdapter(List<String>word,List<Integer> image, int resource,Context context) {
        super(context, resource,word);
        numbreWord=word;
        numberImage = image;
        itemlayout = resource;
        c= context ;
    }

    @Override
    public int getCount() {
        return numbreWord.size();
    }

    @Override
    public Object getItem(int position) {
        return numbreWord.get(position);
    }


    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemlayout,parent,false);
        }
        String word = numbreWord.get(position);
        Integer image = numberImage.get(position);

        final TextView textView = convertView.findViewById(R.id.txt_view);
        final ImageView imageView = convertView.findViewById(R.id.img_view);
        final Button opt1 = convertView.findViewById(R.id.opt1);
        final Button opt2 = convertView.findViewById(R.id.opt2);
        final TextView textViewFinish = convertView.findViewById(R.id.txt_view_finish);
        opt1.setText("True");
        opt2.setText("False");
        textViewFinish.setText("Well Done Swipe Down for the next question");
        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                opt1.setVisibility(View.INVISIBLE);
//                opt2.setVisibility(View.INVISIBLE);
                opt1.setVisibility(View.INVISIBLE);
                opt2.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                textViewFinish.setVisibility(View.VISIBLE);
                opt1.setBackgroundResource(R.drawable.back);

            }
        });
        textView.setText(word);
      //  imageView.setImageResource(image);

        return convertView ;

    }
}
