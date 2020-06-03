package com.muhameddhouibi.designthinking.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.muhameddhouibi.designthinking.R;

public class MyGameViewHolder extends RecyclerView.ViewHolder {
    TextView GameName;
    Button btn_invite;
    View view ;

    public MyGameViewHolder(@NonNull View itemView) {

        super(itemView);
        GameName = itemView.findViewById(R.id.UserName_connected);
        btn_invite = itemView.findViewById(R.id.join);
        view = itemView ;

    }

}
