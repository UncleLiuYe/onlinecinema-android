package com.liuyetech.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationBarView;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.adapter.MainViewPagerAdapter;
import com.liuyetech.myapplication.databinding.ActivityMainBinding;
import com.liuyetech.myapplication.fragment.HomeFragment;
import com.liuyetech.myapplication.fragment.InfoFragment;
import com.liuyetech.myapplication.fragment.MyFragment;
import com.liuyetech.myapplication.fragment.RoomFragment;
import com.liuyetech.myapplication.state.State;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MainViewPagerAdapter mainViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new RoomFragment());
        fragments.add(new InfoFragment());
        fragments.add(new MyFragment());

        mainViewPagerAdapter = new MainViewPagerAdapter(this, fragments);

        View childAt = binding.mainViewPager.getChildAt(0);
        if (childAt instanceof RecyclerView) {
            childAt.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        binding.mainViewPager.setAdapter(mainViewPagerAdapter);

        binding.mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.mainBottomNav.getMenu().getItem(position).setChecked(true);
            }
        });

        binding.mainBottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_nav_home) {
                binding.mainViewPager.setCurrentItem(0, true);
                return true;
            }
            if (item.getItemId() == R.id.bottom_nav_show) {
                binding.mainViewPager.setCurrentItem(1, true);
                return true;
            }
            if (item.getItemId() == R.id.bottom_nav_info) {
                binding.mainViewPager.setCurrentItem(2, true);
                return true;
            }
            if (item.getItemId() == R.id.bottom_nav_my) {
                if (TextUtils.isEmpty(State.token)) {
                    startActivity(new Intent(this, SignInActivity.class));
                } else {
                    binding.mainViewPager.setCurrentItem(3, true);
                    return true;
                }
            }
            return false;
        });

        splashScreen.setKeepOnScreenCondition(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        });
    }
}