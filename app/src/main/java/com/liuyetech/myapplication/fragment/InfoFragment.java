package com.liuyetech.myapplication.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.adapter.CommentAdapter;
import com.liuyetech.myapplication.adapter.NewsAdapter;
import com.liuyetech.myapplication.databinding.FragmentInfoBinding;
import com.liuyetech.myapplication.databinding.NewsBottomViewBinding;
import com.liuyetech.myapplication.entity.Comment;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.viewmodel.CommentViewModel;
import com.liuyetech.myapplication.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentInfoBinding binding;
    private NewsViewModel newsViewModel;

    private CommentViewModel commentViewModel;

    private final List<Comment> comments = new ArrayList<>();

    private final List<News> news = new ArrayList<>();

    private NewsAdapter newsAdapter;

    private CommentAdapter commentAdapter;

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
        commentViewModel = new ViewModelProvider(this).get(CommentViewModel.class);
        newsAdapter = new NewsAdapter(news);
        binding.newsList.setAdapter(newsAdapter);

        commentAdapter = new CommentAdapter(comments);

        binding.newsList.setOnItemClickListener((parent, view1, position, id) -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
            NewsBottomViewBinding newsBottomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.news_bottom_view, null, false);
            bottomSheetDialog.setContentView(newsBottomViewBinding.getRoot());
            newsBottomViewBinding.contentWebview.loadDataWithBaseURL("", news.get(position).getNewsContent(), "text/html", "UTF-8", null);
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

            newsBottomViewBinding.commentList.setAdapter(commentAdapter);
            commentViewModel.getCommentByNewsId(news.get(position).getNewsId()).observe(getViewLifecycleOwner(), new Observer<List<Comment>>() {
                @Override
                public void onChanged(List<Comment> comments1) {
                    if (comments1 != null) {
                        comments.clear();
                        commentAdapter.notifyDataSetChanged();
                        comments.addAll(comments1);
                        commentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "获取评论失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            bottomSheetDialog.show();
        });

        binding.infoSwiperLayout.setOnRefreshListener(this);
        binding.infoSwiperLayout.setRefreshing(true);
        onRefresh();
    }

    private void loadNews() {
        newsViewModel.getAllNews().observe(getViewLifecycleOwner(), news1 -> {
            binding.infoSwiperLayout.setRefreshing(false);
            if (news1 != null) {
                news.clear();
                newsAdapter.notifyDataSetChanged();
                news.addAll(news1);
                newsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh() {
        loadNews();
    }
}