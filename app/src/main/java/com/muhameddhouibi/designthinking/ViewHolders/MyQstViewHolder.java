package com.muhameddhouibi.designthinking.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.muhameddhouibi.designthinking.R;

public class MyQstViewHolder extends RecyclerView.ViewHolder {


    TextView qst_txte,qst_nbre;
    Button btn_yes , btn_no;
    View view ;

    public MyQstViewHolder(@NonNull View itemView) {
        super(itemView);

        qst_txte = itemView.findViewById(R.id.qst11);
        qst_nbre = itemView.findViewById(R.id.qst1);
        btn_yes = itemView.findViewById(R.id.yesbtn);
        btn_no = itemView.findViewById(R.id.nobtn);
        view = itemView ;

    }
}
