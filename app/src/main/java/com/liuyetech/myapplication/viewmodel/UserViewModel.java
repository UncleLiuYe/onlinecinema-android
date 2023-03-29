package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.repository.UserRepository;

public class UserViewModel extends ViewModel {
    public LiveData<Result<User>> userLogin(String username, String password) {
        return UserRepository.USER_REPOSITORY.userLogin(username, password);
    }

    public LiveData<Result<User>> userRegister(String username, String password) {
        return UserRepository.USER_REPOSITORY.userRegister(username, password);
    }

    public LiveData<Boolean> userCheckToken() {
        return UserRepository.USER_REPOSITORY.userTokenCheck();
    }
}
