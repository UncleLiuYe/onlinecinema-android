package com.liuyetech.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.adapter.OrderDetailAdapter;
import com.liuyetech.myapplication.databinding.FragmentMyBinding;
import com.liuyetech.myapplication.databinding.OrderBottomViewBinding;
import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.entity.PayResult;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.viewmodel.OrderViewModel;
import com.liuyetech.myapplication.viewmodel.PayViewModel;
import com.liuyetech.myapplication.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MyFragment extends Fragment {
    private FragmentMyBinding binding;
    private BottomSheetDialog bottomSheetDialog;
    private OrderViewModel orderViewModel;
    private PayViewModel payViewModel;

    private UserViewModel userViewModel;

    private OrderDetailAdapter orderDetailAdapter;
    private List<OrderResult> orderResults = new ArrayList<>();

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 101) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                Log.e("handler", "handleMessage: " + payResult.getMemo());
                Log.e("handler", "handleMessage: " + payResult.getResultStatus());
                Log.e("handler", "handleMessage: " + payResult.getResult());
                if ("9000".equals(payResult.getResultStatus()) || "8000".equals(payResult.getResultStatus())) {
                    Toast.makeText(requireContext(), "支付成功", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                } else {
                    Toast.makeText(requireContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            }
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        payViewModel = new ViewModelProvider(this).get(PayViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        orderDetailAdapter = new OrderDetailAdapter(orderResults);

        orderDetailAdapter.setRequestPayOrderListener(orderResult -> {
            try {
                RequestBody requestBody = RequestBody.create(new ObjectMapper().writeValueAsString(orderResult), MediaType.parse("application/json;charset=utf-8"));
                payViewModel.rePay(requestBody).observe(getViewLifecycleOwner(), s -> {
                    if (!TextUtils.isEmpty(s)) {
                        Runnable payRunnable = () -> {
                            PayTask payTask = new PayTask(requireActivity());
                            Map<String, String> payResult = payTask.payV2(s, true);
                            Message msg = new Message();
                            msg.what = 101;
                            msg.obj = payResult;
                            handler.sendMessage(msg);
                        };
                        new Thread(payRunnable).start();
                    } else {
                        Toast.makeText(requireContext(), "订单失效！", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                Toast.makeText(requireContext(), "支付失败！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.setUser(State.user);

        binding.myOrder.setOnClickListener(v -> {

            User user = State.user;
            if (user != null && user.getUserId() != null) {
                orderViewModel.getOrderListByUid(user.getUserId()).observe(getViewLifecycleOwner(), orderResult -> {
                    if (orderResult != null) {
                        bottomSheetDialog = new BottomSheetDialog(requireContext());
                        OrderBottomViewBinding orderBottomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()), R.layout.order_bottom_view, null, false);
                        bottomSheetDialog.setContentView(orderBottomViewBinding.getRoot());

                        orderBottomViewBinding.orderListview.setAdapter(orderDetailAdapter);

                        orderBottomViewBinding.orderListview.setOnTouchListener((v1, event) -> {
                            if (!orderBottomViewBinding.orderListview.canScrollVertically(-1)) {      //canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
                                orderBottomViewBinding.orderListview.requestDisallowInterceptTouchEvent(false);
                            } else {
                                orderBottomViewBinding.orderListview.requestDisallowInterceptTouchEvent(true);
                            }
                            return false;
                        });

                        orderResults.clear();
                        orderResults.addAll(orderResult);
                        orderDetailAdapter.notifyDataSetChanged();
                        bottomSheetDialog.show();
                    } else {
                        Toast.makeText(requireContext(), "获取失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(requireContext(), "请登陆后查看", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        userViewModel.userCheckToken();
    }
}