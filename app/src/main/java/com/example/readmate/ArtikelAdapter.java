package com.example.readmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private final List<Artikel> data;

    public ArtikelAdapter(Context ctx, List<Artikel> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class ArtikelVh extends RecyclerView.ViewHolder {

        private final TextView tvJudul;
        private final TextView tvTanggal;
        private final TextView tvTopik;

        public ArtikelVh(@NonNull View itemView) {
            super(itemView);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);
            this.tvTanggal = itemView.findViewById(R.id.tvTanggal);
            this.tvTopik= itemView.findViewById(R.id.tvTopik);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowview = LayoutInflater.from(this.ctx).inflate(R.layout.artikel_card_layout, parent, false);
        RecyclerView.ViewHolder vh = new ArtikelVh(rowview);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Artikel a = this.data.get(position);
        ArtikelVh vh = (ArtikelVh) holder;

        vh.tvJudul.setText(a.getJudul());
        vh.tvTanggal.setText(a.getTanggal());
        vh.tvTopik.setText(a.getTopik());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
