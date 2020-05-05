package com.muhameddhouibi.designthinking.ui.slideshow;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.muhameddhouibi.designthinking.LoginActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class SlideshowViewModel extends ViewModel {
    FirebaseAuth mAuth;
    FirebaseUser currentUser ;
    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FirebaseAuth.getInstance().signOut();


    }

    public LiveData<String> getText() {
        return mText;
    }
}