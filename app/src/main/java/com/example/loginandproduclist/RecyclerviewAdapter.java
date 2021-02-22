package com.example.loginandproduclist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.Rowholder> {

    Context context;
    DecimalFormat punctuation;
    private List<UrunleriGosterenSayfa> urunleriGosterenSayfalarRCVA;

    public RecyclerviewAdapter(Context context, List<UrunleriGosterenSayfa> urunleriGosterenSayfalarRCVA) {

        this.context = context;
        this.urunleriGosterenSayfalarRCVA = urunleriGosterenSayfalarRCVA;
        punctuation = new DecimalFormat("###,###.00");
    }

    @NonNull
    @Override
    public Rowholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rowurunler, parent, false);
        return new Rowholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Rowholder holder, int position) {//description yok, category yok
        UrunleriGosterenSayfa model = urunleriGosterenSayfalarRCVA.get(position);
        holder.title.setText(model.getTitle());
        holder.price.setText(punctuation.format(model.getPrice()) + " TL");
        // holder.price.setText(model.getPrice()+" TL");
        Glide.with(context).load(model.image).into(holder.image1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {//resime tıkladığımda
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UrunDetayActivity.class);
                intent.putExtra("gonderilenIddegeri", model.getId());
       /* intent.putExtra("gonderilenismi",model.getTitle());
        intent.putExtra("gonderilenfiyatı",model.getPrice());*/

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urunleriGosterenSayfalarRCVA.size();
    }

    public class Rowholder extends RecyclerView.ViewHolder {
        TextView id;
        TextView title;
        TextView price;
        TextView description;
        TextView category;
        TextView image;
        ImageView image1;
        public Rowholder(@NonNull View itemView) {
            super(itemView);
            //  id=itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            //  description=itemView.findViewById(R.id.description);
            //  category=itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.image);
            image1 = itemView.findViewById(R.id.image1);

        }
    }


}
