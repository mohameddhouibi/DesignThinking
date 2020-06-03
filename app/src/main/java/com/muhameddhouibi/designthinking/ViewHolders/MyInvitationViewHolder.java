package com.muhameddhouibi.designthinking.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;

public class MyInvitationViewHolder extends RecyclerView.ViewHolder {


    public TextView descri ;
    public Button btn_invite ;
    public Button decline ;


    public MyInvitationViewHolder(@NonNull View itemView) {

        super(itemView);


        descri = itemView.findViewById(R.id.Desc);
        btn_invite = itemView.findViewById(R.id.acc_invitation);
        decline = itemView.findViewById(R.id.decline_invitation);
    }
}
