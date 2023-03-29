package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.Video;
import com.liuyetech.myapplication.network.CategoryApi;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.network.VideoApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    public static final CategoryRepository CATEGORY_REPOSITORY = new CategoryRepository();

    private CategoryApi categoryApi;

    private CategoryRepository() {
        categoryApi = RetrofitUtils.getInstance().create(CategoryApi.class);
    }

    public LiveData<Category> getCategoryByCid(Integer cid) {
        MutableLiveData<Category> data = new MutableLiveData<>();
        categoryApi.getCategoryByCid(cid).enqueue(new Callback<Result<Category>>() {
            @Override
            public void onResponse(@NonNull Call<Result<Category>> call, @NonNull Response<Result<Category>> response) {
                if (response.isSuccessful()) {
                    Result<Category> categorys = response.body();
                    if (categorys != null && categorys.getCode() == 200) {
                        data.setValue(categorys.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<Category>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<List<Category>> getAllCategory() {
        MutableLiveData<List<Category>> data = new MutableLiveData<>();
        categoryApi.getAllCategory().enqueue(new Callback<Result<List<Category>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Category>>> call, @NonNull Response<Result<List<Category>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Category>> categorys = response.body();
                    if (categorys != null && categorys.getCode() == 200) {
                        data.setValue(categorys.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Category>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }
}