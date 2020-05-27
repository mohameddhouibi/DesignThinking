package com.muhameddhouibi.designthinking.GlobalBrainstorming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.ImageView;
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
import com.muhameddhouibi.designthinking.Entity.Comment;

import com.muhameddhouibi.designthinking.Entity.User;
import com.muhameddhouibi.designthinking.R;

import java.util.HashMap;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    EditText write ;
    ImageButton send ;
    FirebaseAuth mAuth;
    TextView back ;
    private FirebaseUser firebaseUser;

    private FirebaseRecyclerOptions<Comment> options;
    private FirebaseRecyclerAdapter<Comment, MyCommentViewHolder> adapter;
    DatabaseReference Reference ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        recyclerView = findViewById(R.id.recyclerBC);
        send = findViewById(R.id.send_btn);
        write = findViewById(R.id.messageEt);
        back= findViewById(R.id.txback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (CommentsActivity.this,GloablBrainStorming.class);
                startActivity(i);
            }
        });

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        final String idea_id = getIntent().getStringExtra("idea_id");
        Reference = FirebaseDatabase.getInstance().getReference("Comments").child(idea_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =new FirebaseRecyclerOptions.Builder<Comment>().setQuery(Reference, Comment.class).build();
        adapter= new FirebaseRecyclerAdapter<Comment, MyCommentViewHolder>(options) {

            @NonNull
            @Override
            public MyCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false);
                return new MyCommentViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull final MyCommentViewHolder holder, int position, @NonNull Comment model) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(model.getPublisher());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);
                        Glide.with(getApplicationContext()).load(user.getUrl()).into(holder.useriv);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                holder.userTx.setText(model.getPublisher());
                holder.comment.setText(model.getComment());

            }
        };

        adapter.startListening();

        recyclerView.setAdapter(adapter);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(write.getText().toString().equals(""))
                {
                    Toast.makeText(CommentsActivity.this,"can't send empty comment",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String comment = write.getText().toString();
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Comments").child(idea_id);
                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("comment",comment);
                    hashMap.put("publisher",firebaseUser.getDisplayName());
                    reference.push().setValue(hashMap);
                }


            }
        });


    }

    private void publisherInfo(final ImageView UserIV , final TextView userTV, final String UserName)
    {

    }
}
