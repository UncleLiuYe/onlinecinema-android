package com.liuyetech.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.ActivitySignInBinding;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.SignInVo;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.network.OKHttpClientUtil;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.utils.SharedPreferencesUtils;
import com.liuyetech.myapplication.viewmodel.UserViewModel;

import java.io.IOException;
import java.util.Optional;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private UserViewModel userViewModel;
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
                    loading(false);
                    State.token = msg.obj.toString();
                    SharedPreferencesUtils.setParam(SignInActivity.this, "token", msg.obj.toString());
                    userViewModel.getUserInfo().observe(SignInActivity.this, userResult -> {
                        if (userResult != null && userResult.getCode() == 200 && userResult.getData() != null) {
                            State.user = userResult.getData();
                        }
                    });
                    Toast.makeText(SignInActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                case 102:
                    loading(false);
                    reloadCaptcha();
                    Toast.makeText(SignInActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    break;
                case 103:
                    loading(false);
                    reloadCaptcha();
                    Toast.makeText(SignInActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.signin.setOnClickListener(v -> {
            String username = binding.username.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String captchaCode = binding.captchaCode.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(captchaCode)) {
                Toast.makeText(this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
                return;
            }
            loading(true);
            SignInVo signInVo = new SignInVo();
            signInVo.setUsername(username);
            signInVo.setPassword(password);
            signInVo.setCaptchaCode(captchaCode);

            userViewModel.userLogin(signInVo).observe(this, userResult -> {
                loading(false);
                Message message = Message.obtain();
                if (userResult != null) {
                    if (userResult.getCode() == 200) {
                        message.what = 101;
                        message.obj = userResult.getData();
                    } else if (userResult.getCode() == 500) {
                        message.what = 103;
                        message.obj = userResult.getMsg();
                    } else {
                        message.what = 102;
                    }
                } else {
                    message.what = 102;
                }
                handler.sendMessage(message);
            });
        });

        binding.signup.setOnClickListener(a -> startActivity(new Intent(this, SignUpActivity.class)));
        binding.captcha.setOnClickListener(b -> reloadCaptcha());
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadCaptcha();
    }

    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.signin.setVisibility(View.INVISIBLE);
            binding.progressbar.setVisibility(View.VISIBLE);
        } else {
            binding.signin.setVisibility(View.VISIBLE);
            binding.progressbar.setVisibility(View.INVISIBLE);
        }
    }

    private void reloadCaptcha() {
        Glide.with(SignInActivity.this).load(RetrofitUtils.HOST + "user/captcha").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.captcha);
    }
}