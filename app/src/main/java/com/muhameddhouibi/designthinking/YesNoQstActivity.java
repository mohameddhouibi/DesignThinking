package com.muhameddhouibi.designthinking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.Entity.Question;
import com.muhameddhouibi.designthinking.ViewHolders.MyQstViewHolder;

public class YesNoQstActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    RecyclerView recyclerView ;

    private FirebaseRecyclerOptions<Question> options;
    private FirebaseRecyclerAdapter<Question, MyQstViewHolder> adapter;
    DatabaseReference questions ;
    FirebaseDatabase firebaseDatabase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no_qst);

        firebaseDatabase = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();

        questions=FirebaseDatabase.getInstance().getReference("Questions");

        recyclerView = findViewById(R.id.listquestions);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options =new FirebaseRecyclerOptions.Builder<Question>().setQuery(questions,Question.class).build();

        adapter=new FirebaseRecyclerAdapter<Question, MyQstViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final MyQstViewHolder holder, int position, @NonNull Question model) {

                holder.qst_nbre.setText( model.getQstnbr());
                holder.qst_txte.setText(model.getQuestion());
                holder.btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     //   holder.btn_no.setBackground(Drawable.createFromPath("@drawable/cornerbutton4"));
                        holder.btn_no.setBackgroundDrawable(getResources().getDrawable(R.drawable.cornerbutton4));
                        holder.btn_no.setEnabled(false);
                        holder.btn_yes.setEnabled(false);
                    }
                });
                holder.btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.btn_yes.setBackgroundDrawable(getResources().getDrawable(R.drawable.cornerbutton4));
                        holder.btn_no.setEnabled(false );
                        holder.btn_yes.setEnabled(false);
                    }
                });
            }
            @NonNull
            @Override
            public MyQstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qst,parent,false);

                return new MyQstViewHolder(v);
            }
        };

        adapter.startListening();

        recyclerView.setAdapter(adapter);

    }
}
