package com.muhameddhouibi.designthinking;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.muhameddhouibi.designthinking.Entity.ChatGroup;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AdapterGroupChat extends  RecyclerView.Adapter<AdapterGroupChat.HolderGroupChat>{

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;

    private Context context ;
    private ArrayList<ChatGroup> chatGroupList  ;
    private FirebaseAuth mauth ;
    public AdapterGroupChat(Context context, ArrayList<ChatGroup> chatGroupList) {
        this.context = context;
        this.chatGroupList = chatGroupList;
        mauth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public HolderGroupChat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.row_groupchat_right,parent,false);
            return new HolderGroupChat(view);
        }else
        {
            View view = LayoutInflater.from(context).inflate(R.layout.row_groupchat_left,parent,false);
            return new HolderGroupChat(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull HolderGroupChat holder, int position) {

        ChatGroup chatGroup = chatGroupList.get(position);
        String message = chatGroup.getMessage();
        String sender_name = chatGroup.getSender();
        String timestamp = chatGroup.getTimestamp();

        Calendar cal = Calendar.getInstance(Locale.FRANCE);
        cal.setTimeInMillis(Long.parseLong(timestamp));
        String dateTime = DateFormat.format("dd/MM/yyyy hh:mm aa",cal).toString();

        holder.NameTv.setText(sender_name);
        holder.messageTv.setText(message);
        holder.timetv.setText(dateTime);


    }



    @Override
    public int getItemCount() {
        return chatGroupList.size();
    }


    @Override
    public int getItemViewType(int position) {
         if (chatGroupList.get(position).getSender().equals(mauth.getCurrentUser().getDisplayName()))
         {
            return MSG_TYPE_RIGHT ;
         }else
         {
             return MSG_TYPE_LEFT ;
         }
    }


    class HolderGroupChat extends RecyclerView.ViewHolder {

        TextView NameTv,messageTv,timetv;
        public HolderGroupChat(@NonNull View itemView) {
            super(itemView);
            NameTv =itemView.findViewById(R.id.NameTv);
            messageTv=itemView.findViewById(R.id.messageTv);
            timetv=itemView.findViewById(R.id.timetv);
        }
    }
}
