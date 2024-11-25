package com.example.readmate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ArtikelVh> implements Filterable {
    private final Context ctx;
    private final List<Artikel> data;
    private List<Artikel> dataFiltered;

    public ArtikelAdapter(Context ctx, List<Artikel> data) {
        this.ctx = ctx;
        this.data = data;
        this.dataFiltered = new ArrayList<>(data);
    }

    public static class ArtikelVh extends RecyclerView.ViewHolder {
        private final TextView tvJudul;
        private final TextView tvTanggal;
        private final TextView tvTopik;
        private final ImageView thumbnail;

        public ArtikelVh(@NonNull View itemView) {
            super(itemView);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);
            this.tvTanggal = itemView.findViewById(R.id.tvTanggal);
            this.tvTopik = itemView.findViewById(R.id.tvTopik);
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @NonNull
    @Override
    public ArtikelVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(ctx).inflate(R.layout.artikel_card_layout, parent, false);
        return new ArtikelVh(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelVh holder, int position) {
        Artikel artikel = dataFiltered.get(position);
        holder.tvJudul.setText(artikel.getJudul());
        holder.tvTanggal.setText(artikel.getTanggal());
        holder.tvTopik.setText(artikel.getTopik());
        holder.thumbnail.setImageResource(artikel.getThumbnail());

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(ctx, DetailArtikel.class);
            intent.putExtra("judul", artikel.getJudul());
            intent.putExtra("tanggal", artikel.getTanggal());

            // Start activity
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Log.d("ArtikelAdapter", "Filter diterapkan: " + constraint);
                List<Artikel> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(data);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Artikel artikel : data) {
                        if (artikel.getJudul().toLowerCase().contains(filterPattern)) {
                            filteredList.add(artikel);
                        }
                    }
                    Log.d("ArtikelAdapter", "Artikel yang cocok: " + filteredList.size());
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataFiltered.clear();
                dataFiltered.addAll((List<Artikel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
