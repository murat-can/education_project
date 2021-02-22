package com.example.loginandproduclist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UrunDetayActivity extends AppCompatActivity {
    TextView textViewgosterilen;
    TextView urunadi;
    TextView urunfiyati;
    ImageView imageDetay;
    LinearLayout line1Urunaciklama;
    RecyclerviewAdapter recyclerviewAdapter;
    DecimalFormat punctuation;
    List<UrunlerinDetaylariniGosterici> urunleriGosterenSayfalar;
    Context context;

    LocalDataManager localDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);

        textViewgosterilen = findViewById(R.id.textViewgosterilen);
        urunadi = findViewById(R.id.urunadi);
        urunfiyati = findViewById(R.id.urunfiyati);
        imageDetay = findViewById(R.id.imageDetay);
        line1Urunaciklama = findViewById(R.id.line1Urunaciklama);
        localDataManager=new LocalDataManager();

        Intent mIntent = getIntent(); //tam anlamadım

        int idsi = mIntent.getIntExtra("gonderilenIddegeri", 0);
        //   String ismi= String.valueOf(mIntent.getIntExtra("gonderilenismi",0));
        //    double fiyati=mIntent.getIntExtra("gonderilenfiyatı",0);

        //   textViewgosterilen.setText(String.valueOf(idsi));
        //    urunadi.setText(ismi);
        //    urunfiyati.setText(String.valueOf(fiyati));

        line1Urunaciklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UrunDetayBottomSheet urunDetayBottomSheet = new UrunDetayBottomSheet();

                urunDetayBottomSheet.show(getSupportFragmentManager(), "urunDetay");
            }
        });
        verialmaMetodu2(idsi);
    }
    private void verialmaMetodu2(int urunidsi) {
        punctuation =new DecimalFormat("###,###.00");
        String id = String.valueOf(urunidsi);
        String url = "products/" + id;

        Call<UrunlerinDetaylariniGosterici> urunler = RetrofitClass.getLogin().getirilenurundatalar(url);
        urunler.enqueue(new Callback<UrunlerinDetaylariniGosterici>() {
            @Override
            public void onResponse(Call<UrunlerinDetaylariniGosterici> call, Response<UrunlerinDetaylariniGosterici> response) {

                urunadi.setText(response.body().getTitle());
                textViewgosterilen.setText(response.body().getCategory());

                urunfiyati.setText(punctuation.format(response.body().getPrice())+" tl");

                localDataManager.setSharedPreference(getApplicationContext(), SharedPreferenceKeys.Description, response.body().getDescription());

                Glide.with(UrunDetayActivity.this).load(response.body().getImage()).into(imageDetay);
            }
            @Override
            public void onFailure(Call<UrunlerinDetaylariniGosterici> call, Throwable t) {
            }
        });

    }
}