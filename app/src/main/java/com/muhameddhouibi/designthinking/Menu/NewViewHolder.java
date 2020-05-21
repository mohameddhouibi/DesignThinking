package com.muhameddhouibi.designthinking.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;

public class NewViewHolder extends RecyclerView.ViewHolder {

    TextView userName;
    Button btn_invite;

    public NewViewHolder(@NonNull View itemView) {
        super(itemView);

        userName = itemView.findViewById(R.id.UserName_connected);
        btn_invite = itemView.findViewById(R.id.invite);
    }
}