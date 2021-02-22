package com.example.loginandproduclist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.loginandproduclist.category.CategoryMain;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    List<UrunleriGosterenSayfa> urunleriGosterenSayfalar;
    GridLayoutManager gridLayoutManager;
    LinearLayout linearclick1;
    LinearLayout categoryCick;
    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerView);
        urunleriGosterenSayfalar = new ArrayList<>();

        recyclerviewAdapter = new RecyclerviewAdapter(this, urunleriGosterenSayfalar);
        gridLayoutManager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recyclerviewAdapter);
        verialmaMetodu();
        linearclick1 = findViewById(R.id.linearclick1);
        categoryCick = findViewById(R.id.categoryCick);
        linearclick1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        MainActivity2.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.layout_bottom_sheet,
                                (LinearLayout) findViewById(R.id.bottomSheetContainer)
                        );
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        categoryCick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotocategorypage=new Intent(MainActivity2.this, CategoryMain.class);
                startActivity(gotocategorypage);
            }
        });
    }

    private void verialmaMetodu() {
        Call<List<UrunleriGosterenSayfa>> urunler = RetrofitClass.getLogin().getirilendatalar();
        urunler.enqueue(new Callback<List<UrunleriGosterenSayfa>>() {
            @Override
            public void onResponse(Call<List<UrunleriGosterenSayfa>> call, Response<List<UrunleriGosterenSayfa>> response) {
                urunleriGosterenSayfalar.addAll(response.body());
                recyclerviewAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<UrunleriGosterenSayfa>> call, Throwable t) {

            }
        });
    }
}