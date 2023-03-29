package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    public LiveData<Category> getCategoryByCid(Integer cid) {
        return CategoryRepository.CATEGORY_REPOSITORY.getCategoryByCid(cid);
    }

    public LiveData<List<Category>> getAllCategory() {
        return CategoryRepository.CATEGORY_REPOSITORY.getAllCategory();
    }
}
