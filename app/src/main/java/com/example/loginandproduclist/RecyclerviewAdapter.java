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
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.Rowholder> {
    Context context;
    DecimalFormat punctuation;
    private List<Product> urunleriGosterenSayfalarRCVA;

    public RecyclerviewAdapter(Context context, List<Product> urunleriGosterenSayfalarRCVA) {
        this.context = context;
        this.urunleriGosterenSayfalarRCVA = urunleriGosterenSayfalarRCVA;
        punctuation = new DecimalFormat("###,###.00");
    }
    @NonNull
    @Override
    public Rowholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_product, parent, false);
        return new Rowholder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Rowholder holder, int position) {//description yok, category yok
        Product model = urunleriGosterenSayfalarRCVA.get(position);
        holder.title.setText(model.getTitle());
        holder.price.setText(punctuation.format(model.getPrice()) + " TL");

        Glide.with(context).load(model.image).into(holder.main_image_onclck);
        holder.itemView.setOnClickListener(new View.OnClickListener() {//resime tıkladığımda//main_image_onclck yerine itemView de kullanılır hepsini alır
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
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
        ImageView main_image_onclck;

        public Rowholder(@NonNull View itemView) {
            super(itemView);
            //  id=itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            //  description=itemView.findViewById(R.id.description);
            //  category=itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.image);
            main_image_onclck = itemView.findViewById(R.id.main_image_onclck);
        }
    }
}
