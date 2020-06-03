package com.muhameddhouibi.designthinking.ViewHolders;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;

public class MyIdeaViewHolder extends RecyclerView.ViewHolder{


    public  ImageView like , comment , save ,userIV , useriv  ;
    public TextView sector , like_nbr, viewAllComments , problems,idea ,userTx,date;
    public EditText comment1 ;

    public MyIdeaViewHolder(@NonNull View itemView) {

        super(itemView);

        userIV = itemView.findViewById(R.id.user_IV);
        userTx = itemView.findViewById(R.id.user_TV);
        like = itemView.findViewById(R.id.like);
        comment=itemView.findViewById(R.id.comment);
        save=itemView.findViewById(R.id.save);
        sector= itemView.findViewById(R.id.sector);
        problems=itemView.findViewById(R.id.problems);
        idea =itemView.findViewById(R.id.idea);
        like_nbr= itemView.findViewById(R.id.like_nbr);
        viewAllComments =itemView.findViewById(R.id.comments);
        date=itemView.findViewById(R.id.date);
        useriv = itemView.findViewById(R.id.user_IV2);
        comment1 = itemView.findViewById(R.id.cooEt);

    }
}
