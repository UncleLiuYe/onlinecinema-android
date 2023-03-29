package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.repository.CategoryRepository;
import com.liuyetech.myapplication.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends ViewModel {

    public LiveData<News> getNewsById(Integer nid) {
        return NewsRepository.NEWS_REPOSITORY.getNewsByCid(nid);
    }

    public LiveData<List<News>> getAllNews() {
        return NewsRepository.NEWS_REPOSITORY.getAllNews();
    }
}
