package com.liuyetech.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.MovieDetailActivity;
import com.liuyetech.myapplication.databinding.FragmentHomeBinding;
import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.viewmodel.CategoryViewModel;
import com.liuyetech.myapplication.viewmodel.MovieViewModel;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentHomeBinding binding;
    private CategoryViewModel categoryViewModel;
    private MovieViewModel movieViewModel;
    private final List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        this.movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        binding.homeSwiperRefresh.setOnRefreshListener(this);
        binding.homeSwiperRefresh.setRefreshing(true);
        onRefresh();
    }

    private void getBannerData() {
        movieViewModel.getMovieList().observe(getViewLifecycleOwner(), movieList -> {
            if (movieList != null) {
                binding.homeBanner.setAdapter(new BannerImageAdapter<Movie>((movieList.size() > 3 ? movieList.subList(0, 3) : movieList)) {

                            @Override
                            public void onBindView(BannerImageHolder holder, Movie data, int position, int size) {
                                Glide.with(holder.imageView)
                                        .load(RetrofitUtils.IMAGE_HOST + data.getMoviePoster())
                                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                        .into(holder.imageView);
                            }
                        }).setIndicator(new CircleIndicator(requireContext())).setBannerRound(20.0f)
                        .setOnBannerListener((data, position) -> {
                            Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
                            intent.putExtra("movie", movieList.get(position));
                            startActivity(intent);
                        });
            } else {
                Toast.makeText(requireContext(), "获取内容失败！请重启APP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllCategoryList() {
        for (Fragment fragment : fragments) {
            getParentFragmentManager().beginTransaction().remove(fragment).commit();
        }
        categoryViewModel.getAllCategory().observe(getViewLifecycleOwner(), categories -> {
            if (binding.homeSwiperRefresh.isRefreshing()) {
                binding.homeSwiperRefresh.setRefreshing(false);
            }
            if (categories == null) {
                Toast.makeText(requireContext(), "分类获取失败！", Toast.LENGTH_SHORT).show();
            } else {
                for (Category category : categories) {
                    CategoryFragment categoryFragment = new CategoryFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("cid", category.getCategoryId());
                    bundle.putString("cname", category.getCategoryName());
                    categoryFragment.setArguments(bundle);
                    fragments.add(categoryFragment);
                    getParentFragmentManager().beginTransaction().add(binding.homeCategoryLayout.getId(), categoryFragment).commit();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        getBannerData();
        getAllCategoryList();
    }
}