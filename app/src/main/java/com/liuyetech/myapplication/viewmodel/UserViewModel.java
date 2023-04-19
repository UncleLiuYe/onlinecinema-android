package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.SignInVo;
import com.liuyetech.myapplication.entity.SignUpVo;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.repository.UserRepository;

import okhttp3.MultipartBody;

public class UserViewModel extends ViewModel {
    public LiveData<Result<String>> userLogin(SignInVo signInVo) {
        return UserRepository.USER_REPOSITORY.userLogin(signInVo);
    }

    public LiveData<Result<User>> userRegister(SignUpVo signUpVo) {
        return UserRepository.USER_REPOSITORY.userRegister(signUpVo);
    }

    public LiveData<Boolean> userCheckToken() {
        return UserRepository.USER_REPOSITORY.userTokenCheck();
    }

    public LiveData<String> userAvatarUpload(MultipartBody.Part avatarFile) {
        return UserRepository.USER_REPOSITORY.userAvatarUpload(avatarFile);
    }

    public LiveData<Result<User>> getUserInfo() {
        return UserRepository.USER_REPOSITORY.getUserInfo();
    }
}
