package com.liuyetech.myapplication.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.ActivitySignUpBinding;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.SignUpVo;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.network.OKHttpClientUtil;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.viewmodel.UserViewModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String avatarImgPath = "";
    private UserViewModel userViewModel;

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 101:
                    loading(false);
                    Toast.makeText(SignUpActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    break;
                case 102:
                    loading(false);
                    reloadCaptcha();
                    Toast.makeText(SignUpActivity.this, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
                case 103:
                    loading(false);
                    reloadCaptcha();
                    Toast.makeText(SignUpActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.avatar.setOnClickListener(v -> {
            openAlbum();
        });

        binding.signup.setOnClickListener(v -> {
            String username = binding.username.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String repassword = binding.repassword.getText().toString().trim();
            String captchaCode = binding.captchaCode.getText().toString().trim();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword) || TextUtils.isEmpty(captchaCode) || TextUtils.isEmpty(avatarImgPath)) {
                Toast.makeText(this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!repassword.equals(password)) {
                Toast.makeText(this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
                return;
            }
            loading(true);
            SignUpVo signUpVo = new SignUpVo();
            signUpVo.setUsername(username);
            signUpVo.setPassword(password);
            signUpVo.setAvatarImgPath(avatarImgPath);
            signUpVo.setCaptchaCode(captchaCode);

            userViewModel.userRegister(signUpVo).observe(this, userResult -> {
                loading(false);
                Message message = Message.obtain();
                if (userResult != null) {
                    if (userResult.getCode() == 200) {
                        message.what = 101;
                    } else if (userResult.getCode() == 500) {
                        message.what = 102;
                        message.obj = userResult.getMsg();
                    } else {
                        message.what = 103;
                    }
                } else {
                    message.what = 103;
                }
                handler.sendMessage(message);
            });
        });

        binding.signin.setOnClickListener(v -> onBackPressed());
        binding.captcha.setOnClickListener(v -> reloadCaptcha());
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadCaptcha();
    }

    private final ActivityResultLauncher<Intent> pickImage =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        if (result.getData() != null) {
                            Uri uri = result.getData().getData();
                            try {
                                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String path = cursor.getString(columnIndex);  //获取照片路径
                                cursor.close();

                                Bitmap bitmap = BitmapFactory.decodeFile(path);
                                binding.avatarImg.setImageBitmap(bitmap);
                                binding.addAvatarText.setVisibility(View.INVISIBLE);

                                File file = new File(path);

                                RequestBody fileRQ = RequestBody.create(file, MediaType.parse("image/*"));
                                MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), fileRQ);

                                userViewModel.userAvatarUpload(part).observe(SignUpActivity.this, s -> {
                                    if (!TextUtils.isEmpty(s)) {
                                        avatarImgPath = s;
                                    } else {
                                        Toast.makeText(SignUpActivity.this, s == null ? "上传失败" : s, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.signup.setVisibility(View.INVISIBLE);
            binding.progressbar.setVisibility(View.VISIBLE);
        } else {
            binding.signup.setVisibility(View.VISIBLE);
            binding.progressbar.setVisibility(View.INVISIBLE);
        }
    }

    private void openAlbum() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(this, "请授权储存文件读取权限以选择头像", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void reloadCaptcha() {
        Glide.with(SignUpActivity.this).load(RetrofitUtils.HOST + "user/captcha").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(binding.captcha);
    }
}