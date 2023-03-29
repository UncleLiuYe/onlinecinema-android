package com.liuyetech.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.MovieDetailActivity;
import com.liuyetech.myapplication.adapter.CategoryAdapter;
import com.liuyetech.myapplication.databinding.FragmentCategoryBinding;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private FragmentCategoryBinding binding;
    private MovieViewModel movieViewModel;

    private Integer cid;
    private String cname;

    private final List<Movie> movies = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.cid = bundle.getInt("cid");
            this.cname = bundle.getString("cname");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.categoryTitle.moduleTitle.setText(this.cname);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        categoryAdapter = new CategoryAdapter(movies, requireContext());
        binding.movieGridView.setAdapter(categoryAdapter);

        binding.movieGridView.setOnItemClickListener((parent, view1, position, id) -> {
            Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
            intent.putExtra("movie", movies.get(position));
            requireContext().startActivity(intent);
        });

        getCategoryData();
    }

    private void getCategoryData() {
        movieViewModel.getMovieListByType(cid).observe(getViewLifecycleOwner(), movieList -> {
            if (movieList != null) {
                movies.clear();
                movies.addAll(movieList);
                categoryAdapter.notifyDataSetChanged();
            }
        });
    }
}