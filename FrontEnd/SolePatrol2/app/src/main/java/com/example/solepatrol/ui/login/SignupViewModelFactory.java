package com.example.solepatrol.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.solepatrol.data.LoginDataSource;
import com.example.solepatrol.data.LoginRepository;
import com.example.solepatrol.data.SignedupRepository;
import com.example.solepatrol.data.SignupDataSource;
/**
 * The SignupViewModelFactory was based off the LoginViewModelFactory class that was generated automatically
 *  using Android Studio and later altered to meet the needs of our Sole Patrol app.
 **/

public class SignupViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SignupViewModel.class)) {
            return (T) new SignupViewModel(SignedupRepository.getInstance(new SignupDataSource()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}