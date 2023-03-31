package com.liuyetech.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.ActivityLoginBinding;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.utils.SharedPreferencesUtils;
import com.liuyetech.myapplication.viewmodel.UserViewModel;

import java.util.Optional;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.loginLoginButton.setOnClickListener(v -> {
            String username = binding.loginUserName.getText().toString().trim();
            String password = binding.loginUserPassword.getText().toString().trim();

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                userViewModel.userLogin(username, password).observe(this, result -> {
                    Optional.of(result).ifPresent(userResult -> {
                        if (userResult.getCode() == 200) {
                            User user = userResult.getData();
                            Optional.ofNullable(user).ifPresent(user1 -> {
                                State.user = user1;
                                State.token = userResult.getMsg();
                                Log.e("token", State.token);
                                SharedPreferencesUtils.setParam(LoginActivity.this, "token", userResult.getMsg());
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            });
                        } else {
                            Toast.makeText(LoginActivity.this, userResult.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    });
                });
            } else {
                Toast.makeText(this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.imageBack.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }
}