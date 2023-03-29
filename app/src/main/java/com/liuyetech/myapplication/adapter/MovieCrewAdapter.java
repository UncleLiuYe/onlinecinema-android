package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.CrewItemBinding;
import com.liuyetech.myapplication.entity.Crew;

import java.util.List;

public class MovieCrewAdapter extends RecyclerView.Adapter<MovieCrewAdapter.ViewHolder> {

    private List<Crew> crews;

    public MovieCrewAdapter(List<Crew> crews) {
        this.crews = crews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.crew_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setCrew(crews.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return crews.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CrewItemBinding binding;

        public ViewHolder(@NonNull CrewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
