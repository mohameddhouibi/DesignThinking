package com.muhameddhouibi.designthinking.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muhameddhouibi.designthinking.DesignIntroductionActivity;
import com.muhameddhouibi.designthinking.R;
import com.muhameddhouibi.designthinking.WorkshopActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button bt ;
    ImageView introduction,workshop,help,constribute;
    FirebaseDatabase database ;
    DatabaseReference playerRef ;
    FirebaseAuth mAuth ;
    String playerName ;
    DatabaseReference rooms ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);

        introduction=root.findViewById(R.id.introduction);
        workshop=root.findViewById(R.id.worksho);
        help = root.findViewById(R.id.hel);
        constribute=root.findViewById(R.id.constribute);
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        playerName = mAuth.getCurrentUser().getDisplayName();
        playerRef= database.getReference("Players/" + playerName);
        playerRef.setValue("");




        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DesignIntroductionActivity.class);
                root.getContext().startActivity(i);
            }
        });

        workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), WorkshopActivity.class);
                root.getContext().startActivity(i);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DesignIntroductionActivity.class);
                root.getContext().startActivity(i);
            }
        });

        //  final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //  textView.setText(s);
            }
        });
        return root;
    }

//    private void addEventListener() {
//        playerRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                startActivity(new);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        })
//    }
}
