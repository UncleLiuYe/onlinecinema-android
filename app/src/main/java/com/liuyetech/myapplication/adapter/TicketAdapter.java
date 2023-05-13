package com.liuyetech.myapplication.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.PlayActivity;
import com.liuyetech.myapplication.databinding.NewsItemBinding;
import com.liuyetech.myapplication.databinding.OrderItemBinding;
import com.liuyetech.myapplication.databinding.TicketItemBinding;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.entity.Ticket;

import java.util.List;

public class TicketAdapter extends BaseAdapter {
    public List<Ticket> tickets;

    public TicketAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public int getCount() {
        return tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ticket ticket = tickets.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            TicketItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.ticket_item, parent, false);
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.binding.setTicket(ticket);

        viewHolder.binding.detailStatus.removeAllViews();

        if (ticket.getTicketStatus() == 0) {
            TextView textView = new TextView(parent.getContext());
            textView.setText("电影票已过期");
            textView.setTextColor(Color.RED);
            textView.setTextSize(parent.getContext().getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._8ssp));
            viewHolder.binding.detailStatus.addView(textView);
        } else if (ticket.getTicketStatus() == 1) {
            Button button = new Button(parent.getContext());
            button.setText("播放");
            button.setOnClickListener(v -> {
                Intent intent = new Intent(parent.getContext(), PlayActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("movie", ticket.getMovie());
                parent.getContext().startActivity(intent);
            });
            viewHolder.binding.detailStatus.addView(button);
        }
        return convertView;
    }

    static class ViewHolder {
        TicketItemBinding binding;

        public ViewHolder(@NonNull TicketItemBinding binding) {
            this.binding = binding;
        }
    }
}
