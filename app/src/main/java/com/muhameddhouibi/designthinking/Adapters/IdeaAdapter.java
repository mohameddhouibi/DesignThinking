package com.muhameddhouibi.designthinking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.Idea;
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.R;

import java.util.List;

public class IdeaAdapter extends RecyclerView.Adapter<IdeaAdapter.ViewHolder> {

    public Context context ;
    public List<Idea>ideas ;

    private FirebaseUser firebaseUser ;


    public IdeaAdapter(Context context, List<Idea> ideas) {
        this.context = context;
        this.ideas = ideas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brain,parent,false);
        return new IdeaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Idea idea = ideas.get(i);


        publisherInfo(holder.userIV ,holder.userTx,idea.getUsername());
        holder.sector.setText(idea.getSector());
        holder.problems.setText(idea.getProblem());
        holder.idea.setText(idea.getIdea());
        holder.like_nbr.setText("dabegh");
        holder.viewAllComments.setText("view all coo");

    }

    @Override
    public int getItemCount() {
        return ideas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder   {

        public ImageView like , comment , save ,userIV ;
        public TextView sector , like_nbr, viewAllComments , problems,idea ,userTx;


        public ViewHolder(@NonNull View itemView) {
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
        }
    }

    private void publisherInfo(final ImageView UserIV , final TextView userTV, final String UserName)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UserName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                Glide.with(context).load(user.getUrl()).into(UserIV);
                userTV.setText(user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
