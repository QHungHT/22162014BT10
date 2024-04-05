package com.example.bt_29_3_2024.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private final List<Category> categoryList;
    private final Context context;
    private OnClickListener onClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categoryName.setText(category.getName());
        Glide.with(context)
                .load(category.getImages())
                .into(holder.categoryImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, category);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList == null ? 0 : categoryList.size();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Category category);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView categoryImage;
        private final TextView categoryName;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.image_cate);
            categoryName = itemView.findViewById(R.id.tvNameCategory);
        }
    }
}
