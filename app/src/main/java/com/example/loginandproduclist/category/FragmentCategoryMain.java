package com.example.loginandproduclist.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginandproduclist.CategoryLink;
import com.example.loginandproduclist.R;
import com.example.loginandproduclist.RetrofitClass;
import com.example.loginandproduclist.SharedPreferenceKeys;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategoryMain extends Fragment {
    List<CategoryJsonget> categoryJsongets;
    GridLayoutManager gridLayoutManager;
    CategoryAdapter categoryAdapter;
    RecyclerView CategoryrecyclerView;

    private List<String> imgUrl = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity_category_main,container,false);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        CategoryrecyclerView = view.findViewById(R.id.CategoryrecyclerView);
        categoryJsongets = new ArrayList<>();//oluşturulan liste new lenir ama Arraylist olarak newlenir

        categoryAdapter = new CategoryAdapter(getContext(), categoryJsongets);//boş //adapter de içine liste atanır
        gridLayoutManager = new GridLayoutManager(getContext(), 1);
        CategoryrecyclerView.setLayoutManager(gridLayoutManager);
        CategoryrecyclerView.setAdapter(categoryAdapter); //sonra adapter recyclerview e bastırılır

        imgUrl.add(CategoryLink.categoryimage1);
        imgUrl.add(CategoryLink.categoryimage2);
        imgUrl.add(CategoryLink.categoryimage3);
        imgUrl.add(CategoryLink.categoryimage4);

        categoryLoadData();
        return view;
    }
    private void categoryLoadData() {
        //4 tane olduğu için dizi[] tanımladık
        Call<String[]> categoryurunler = RetrofitClass.GetService().GetCategories(SharedPreferenceKeys.categorilink);
        categoryurunler.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {//cevap geldiğinde yapacağım işlemler
             /*   for (String category : response.body()) {
                    CategoryJsonget categoryJsonget = new CategoryJsonget();
                    categoryJsonget.name = category;
                    categoryJsonget.image = imgUrl.get(3);
                    categoryJsongets.add(categoryJsonget);
                    categoryAdapter.notifyDataSetChanged();
                }*/
                for (int i = 0; i < response.body().length; i++) {
                    CategoryJsonget categoryJsonget = new CategoryJsonget();
                    categoryJsonget.name = response.body()[i];
                    categoryJsonget.image = imgUrl.get(i);
                    categoryJsongets.add(categoryJsonget);
                }
                categoryAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<String[]> call, Throwable t) {//cevap gelmediğinde yapacağım işlemler

            }
        });
    }
}
