package com.muhameddhouibi.designthinking.GlobalBrainstorming;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;

public class MyCommentViewHolder extends RecyclerView.ViewHolder {

    ImageView  useriv  ;
    TextView comment , userTx ;

    public MyCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        useriv = itemView.findViewById(R.id.Cophoto);
        userTx = itemView.findViewById(R.id.comment_user);
        comment = itemView.findViewById(R.id.commentitself);

    }
}
