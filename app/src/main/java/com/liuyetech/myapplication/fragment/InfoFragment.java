package com.liuyetech.myapplication.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.MovieDetailActivity;
import com.liuyetech.myapplication.adapter.NewsAdapter;
import com.liuyetech.myapplication.databinding.BuyBottomViewBinding;
import com.liuyetech.myapplication.databinding.FragmentInfoBinding;
import com.liuyetech.myapplication.databinding.NewsBottomViewBinding;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.listener.OnItemClickListener;
import com.liuyetech.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {
    private FragmentInfoBinding binding;
    private NewsViewModel newsViewModel;

    private List<News> news = new ArrayList<>();

    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsAdapter = new NewsAdapter(news);
        newsAdapter.setHasStableIds(true);
        binding.newsList.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickListener(data -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            NewsBottomViewBinding newsBottomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.news_bottom_view, null, false);
            bottomSheetDialog.setContentView(newsBottomViewBinding.getRoot());
            newsBottomViewBinding.contentWebview.loadDataWithBaseURL("", data.getNewsContent(), "text/html", "UTF-8", null);
            FrameLayout frameLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (frameLayout != null) {
                BottomSheetBehavior<View> bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
                bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
            newsBottomViewBinding.contentWebview.setOnTouchListener((v1, event) -> {
                if (!newsBottomViewBinding.contentWebview.canScrollVertically(-1)) {      //canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
                    newsBottomViewBinding.contentWebview.requestDisallowInterceptTouchEvent(false);
                } else {
                    newsBottomViewBinding.contentWebview.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            });
            bottomSheetDialog.show();
        });
    }

    private void loadNews() {
        newsViewModel.getAllNews().observe(getViewLifecycleOwner(), news1 -> {
            if (news1 != null) {
                int oldSize = news1.size();
                newsAdapter.notifyItemRangeRemoved(0, oldSize);
                news.addAll(news1);
                newsAdapter.notifyItemRangeInserted(0, news.size());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadNews();
    }
}