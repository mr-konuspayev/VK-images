package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemGridBinding;
import com.example.myapplication.ui.grid.GridItem;

import org.jetbrains.annotations.NotNull;

public class GridRVA extends ListAdapter<GridItem, GridRVA.ViewHolder> {
    private final OnClick<GridItem> listener;

    public GridRVA(OnClick<GridItem> listener) {
        super(new GridItemDiffCallback());
        this.listener = listener;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemGridBinding.inflate(LayoutInflater.from(parent.getContext())));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        GridItem item = getCurrentList().get(position);
        holder.binding.setItem(item);
        holder.binding.getRoot().setOnClickListener(v -> listener.onClick(item));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemGridBinding binding;

        public ViewHolder(@NotNull ItemGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class GridItemDiffCallback extends DiffUtil.ItemCallback<GridItem> {

        @Override
        public boolean areItemsTheSame(@NonNull GridItem oldItem, @NonNull GridItem newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull GridItem oldItem, @NonNull GridItem newItem) {
            return oldItem.equals(newItem);
        }
    }

    public interface OnClick<T> {
        void onClick(T t);
    }
}