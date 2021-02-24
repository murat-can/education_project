package com.example.loginandproduclist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_mainActivity2 extends Fragment {
    List<Product> urunleriGosterenSayfalar;
    GridLayoutManager gridLayoutManager;
    LinearLayout linearclick1;

    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_main2, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        urunleriGosterenSayfalar = new ArrayList<>();

        recyclerviewAdapter = new RecyclerviewAdapter(getContext(), urunleriGosterenSayfalar);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerviewAdapter);
        DataFromRequest();
        linearclick1 = view.findViewById(R.id.linearclick1);

        linearclick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,
                                (LinearLayout) view.findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        return view;
    }
    private void DataFromRequest() {
        Call<List<Product>> urunler = RetrofitClass.GetService().GetProducts();//"products" link tarafı falan hepsi var
        urunler.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                urunleriGosterenSayfalar.addAll(response.body());
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
    }


}
