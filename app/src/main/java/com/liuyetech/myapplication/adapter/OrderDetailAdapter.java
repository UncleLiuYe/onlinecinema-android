package com.liuyetech.myapplication.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.OrderBottomViewBinding;
import com.liuyetech.myapplication.databinding.OrderItemBinding;
import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.listener.RequestPayOrderListener;

import java.util.List;

public class OrderDetailAdapter extends BaseAdapter {
    private List<OrderResult> orderDetails;

    private RequestPayOrderListener requestPayOrderListener;

    public void setRequestPayOrderListener(RequestPayOrderListener requestPayOrderListener) {
        this.requestPayOrderListener = requestPayOrderListener;
    }

    public OrderDetailAdapter(List<OrderResult> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public int getCount() {
        return orderDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return orderDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderResult orderResult = orderDetails.get(position);
        OrderDetailAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            OrderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.order_item, parent, false);
            convertView = binding.getRoot();
            viewHolder = new OrderDetailAdapter.ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderDetailAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.binding.setMovie(orderResult.getOrderDetail().getMovie());

        viewHolder.binding.detailStatus.removeAllViews();

        if (orderResult.getStatus() == 0) {
            Button button = new Button(parent.getContext());
            button.setText("支付");
            button.setOnClickListener(v -> requestPayOrderListener.payOrder(orderResult));
            viewHolder.binding.detailStatus.addView(button);
        } else if (orderResult.getStatus() == 1) {
            TextView textView = new TextView(parent.getContext());
            textView.setText("订单已关闭");
            textView.setTextColor(Color.RED);
            textView.setTextSize(parent.getContext().getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._8ssp));
            viewHolder.binding.detailStatus.addView(textView);
        } else {
            TextView textView = new TextView(parent.getContext());
            textView.setText("订单已完成");
            textView.setTextColor(Color.GREEN);
            textView.setTextSize(parent.getContext().getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._8ssp));
            viewHolder.binding.detailStatus.addView(textView);
        }
        return convertView;
    }

    static class ViewHolder {
        OrderItemBinding binding;

        public ViewHolder(OrderItemBinding binding) {
            this.binding = binding;
        }
    }
}
