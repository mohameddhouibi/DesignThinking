package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.muhameddhouibi.designthinking.Entity.Game;
import com.muhameddhouibi.designthinking.Entity.Idea;
import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.Menu.NewViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class GloablBrainStorming extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private IdeaAdapter ideaAdapter ;
    public List<Idea> ideaList ;
    private FirebaseUser firebaseUser;

    private FirebaseRecyclerOptions<Idea> options;
    private FirebaseRecyclerAdapter<Idea,MyIdeaViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gloabl_brain_storming);


        recyclerView = findViewById(R.id.recyclerBrains);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Ideas");

        options =new FirebaseRecyclerOptions.Builder<Idea>().setQuery(reference,Idea.class).build();

        adapter=new FirebaseRecyclerAdapter<Idea, MyIdeaViewHolder>(options) {


            @NonNull
            @Override
            public MyIdeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brain,parent,false);

                return new MyIdeaViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull final MyIdeaViewHolder holder, int position, @NonNull final Idea model) {

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                publisherInfo(holder.userIV,holder.userTx,model.getUsername());
                holder.sector.setText(model.getSector());
                holder.problems.setText(model.getProblem());
                holder.idea.setText(model.getIdea());
                holder.like_nbr.setText("520");
                holder.viewAllComments.setText("view all comments");
                holder.date.setText(model.getDate());
                isLike(model.getId() , holder.like );
                nbrLike(holder.like_nbr , model.getId());

                holder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (holder.like.getTag().equals("like"))
                        {
                            FirebaseDatabase.getInstance().getReference("IdeaReactions").child("likes").child(model.getId())
                                    .child(firebaseUser.getUid()).setValue(true);

                        }
                        else
                            {
                                FirebaseDatabase.getInstance().getReference("IdeaReactions").child("likes").child(model.getId())
                                        .child(firebaseUser.getUid()).removeValue();
                        }
                    }
                });
            }
        };




        adapter.startListening();

        recyclerView.setAdapter(adapter);

    }
    private void publisherInfo(final ImageView UserIV , final TextView userTV, final String UserName)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UserName);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                Glide.with(getApplicationContext()).load(user.getUrl()).into(UserIV);
                userTV.setText(user.getUserName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void isLike(String Idea_id , final ImageView imageView )
    {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("IdeaReactions").child("likes").child(Idea_id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(firebaseUser.getUid()).exists())
                {
                    imageView.setImageResource(R.drawable.ic_favorite_black_24dp);
                    imageView.setTag("liked");
                }
                else
                {
                    imageView.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    imageView.setTag("like");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

private void nbrLike(final TextView likes , String Idea_id) {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("IdeaReactions").child("likes").child(Idea_id);
    reference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            likes.setText(dataSnapshot.getChildrenCount()+"likes");

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}



}
