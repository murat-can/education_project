package com.example.loginandproduclist.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginandproduclist.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryRowHolder> {

    Context context;
    private List<CategoryJsonget> categoryJsongetList;

    public CategoryAdapter(Context context, List<CategoryJsonget> categoryJsongetList) {
        this.context = context;
        this.categoryJsongetList = categoryJsongetList;
    }
    @NonNull
    @Override
    public CategoryRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.category_row,parent,false);
        return new CategoryRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRowHolder holder, int position) {
        CategoryJsonget model=categoryJsongetList.get(position);
        holder.categoryText.setText(model.getName());
    }
    @Override
    public int getItemCount() {

        return categoryJsongetList.size();
    }

    public class CategoryRowHolder extends RecyclerView.ViewHolder {
        TextView categoryText;
        public CategoryRowHolder(@NonNull View itemView) {
            super(itemView);
            categoryText=itemView.findViewById(R.id.categoryText);
        }
    }
}

