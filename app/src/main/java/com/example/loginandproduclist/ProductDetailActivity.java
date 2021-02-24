package com.example.loginandproduclist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    TextView CategoryText;
    TextView urunadi;
    TextView urunfiyati;
    ImageView imageDetay;
    LinearLayout line1Urunaciklama;
    RecyclerviewAdapter recyclerviewAdapter;
    DecimalFormat punctuation;

    LocalDataManager localDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);

        CategoryText = findViewById(R.id.CategoryText);
        urunadi = findViewById(R.id.urunadi);
        urunfiyati = findViewById(R.id.urunfiyati);
        imageDetay = findViewById(R.id.imageDetay);
        line1Urunaciklama = findViewById(R.id.line1Urunaciklama);
        localDataManager = new LocalDataManager();

        Intent mIntent = getIntent(); // anladım

        int idsi = mIntent.getIntExtra("gonderilenIddegeri", 0);

        DataRetrievalMethod(idsi);

        line1Urunaciklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductDetailBottomSheet productDetailBottomSheet = new ProductDetailBottomSheet();

                productDetailBottomSheet.show(getSupportFragmentManager(), "urunDetayi");//gösterildi
            }
        });
    }
    
    private void DataRetrievalMethod(int urunidsigelen) {
        punctuation = new DecimalFormat("###,###.00");
        String incomingId = String.valueOf(urunidsigelen);
        String url = "products/" + incomingId;//açıklaması devamına geliyor 1,2,3... hangisine tıklarsak o sayının açıklaması geliyor

        Call<ProductDetail> urunler = RetrofitClass.GetService().GetProductDetail(url);
        urunler.enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {

                urunadi.setText(response.body().getTitle());
                CategoryText.setText(response.body().getCategory());

                urunfiyati.setText(punctuation.format(response.body().getPrice()) + " tl");
                localDataManager.setSharedPreference(getApplicationContext(), SharedPreferenceKeys.Description, response.body().getDescription());
                //Shared prefencens a Description atanır. dönen responce dan yapıldı
                Glide.with(ProductDetailActivity.this).load(response.body().getImage()).into(imageDetay);
            }
            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
            }
        });
    }
}