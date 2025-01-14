package com.example.bt_29_3_2024.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bt_29_3_2024.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    private final List<SubCategory> subCategories;
    private final Context mContext;

    public SubCategoryAdapter(Context context, List<SubCategory> subCategories) {
        this.mContext = context;
        this.subCategories = subCategories;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_sub_category, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        SubCategory subCategory = subCategories.get(position);
        if (subCategory == null) {
            return;
        }
        Glide.with(mContext)
                .load(subCategory.getStrMealThumb())
                .into(holder.imgCategory);
        holder.tvCategory.setText(subCategory.getStrMeal());

        // Xử lý sự kiện khi người dùng nhấp vào một mục danh sách con
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang ProductDetailActivity và truyền ID của sản phẩm
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra("product_id", subCategory.getId());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (subCategories != null) {
            return subCategories.size();
        }
        return 0;
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

        TextView tvCategory;
        CircleImageView imgCategory;

        public SubCategoryViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.tv_category);
            imgCategory = itemView.findViewById(R.id.img_category);
        }
    }

}
