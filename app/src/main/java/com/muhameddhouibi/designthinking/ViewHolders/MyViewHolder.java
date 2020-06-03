package com.muhameddhouibi.designthinking.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;


public class MyViewHolder extends RecyclerView.ViewHolder {

    public  TextView userName;
    public Button btn_invite;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.UserName_connected);
        btn_invite = itemView.findViewById(R.id.invite);
    }
}
