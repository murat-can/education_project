package com.example.loginandproduclist.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.loginandproduclist.R;
import com.example.loginandproduclist.RetrofitClass;
import com.example.loginandproduclist.SharedPreferenceKeys;
import com.example.loginandproduclist.category.CategoryAdapter;
import com.example.loginandproduclist.category.CategoryJsonget;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryMain extends AppCompatActivity {
    List<CategoryJsonget> categoryJsongets;
    GridLayoutManager gridLayoutManager;
    CategoryAdapter categoryAdapter;
    RecyclerView CategoryrecyclerView;
  //  List<String> categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        CategoryrecyclerView = findViewById(R.id.CategoryrecyclerView);
        categoryJsongets = new ArrayList<>();//oluşturulan liste new lenir ama Arraylist olarak newlenir
       // categoryName = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(this, categoryJsongets);//boş //adapter de içine liste atanır
        gridLayoutManager = new GridLayoutManager(getBaseContext(), 1);
        CategoryrecyclerView.setLayoutManager(gridLayoutManager);
        CategoryrecyclerView.setAdapter(categoryAdapter); //sonra adapter recyclerview e bastırılır

        categoryLoadData();
    }
    private void categoryLoadData() {
        Call<String[]> categoryurunler = RetrofitClass.getLogin().categorygetdata(SharedPreferenceKeys.categorilink);
        categoryurunler.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                for (String category : response.body()) {
                    CategoryJsonget as = new CategoryJsonget();
                    as.name = category;
                    categoryJsongets.add(as);
                    categoryAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {

            }
        });
    }
}