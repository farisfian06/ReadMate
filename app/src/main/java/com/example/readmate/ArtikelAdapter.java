package com.example.readmate;

import android.content.Context;
import android.content.Intent;
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
    private List<Artikel> dataFiltered; // Backup list for filtering

    public ArtikelAdapter(Context ctx, List<Artikel> data) {
        this.ctx = ctx;
        this.data = data;
        this.dataFiltered = new ArrayList<>(data); // Initialize backup list
    }

    public class ArtikelVh extends RecyclerView.ViewHolder {
        private final TextView tvJudul;
        private final TextView tvTanggal;
        private final TextView tvTopik;
        private final ImageView thumbnail;

        public ArtikelVh(@NonNull View itemView) {
            super(itemView);
            this.tvJudul = itemView.findViewById(R.id.tvJudul);
            this.tvTanggal = itemView.findViewById(R.id.tvTanggal);
            this.tvTopik= itemView.findViewById(R.id.tvTopik);
            this.thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @NonNull
    @Override
    public ArtikelVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowview = LayoutInflater.from(this.ctx).inflate(R.layout.artikel_card_layout, parent, false);
        return new ArtikelVh(rowview);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtikelVh holder, int position) {
        Artikel a = dataFiltered.get(position);
        holder.tvJudul.setText(a.getJudul());
        holder.tvTanggal.setText(a.getTanggal());
        holder.tvTopik.setText(a.getTopik());

        int imageResource = this.ctx.getResources().getIdentifier(a.thumbnail, "drawable", this.ctx.getPackageName());
        holder.thumbnail.setImageResource(imageResource);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, DetailArtikel.class);
            intent.putExtra("judul", a.getJudul());
            intent.putExtra("tanggal", a.getTanggal());
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
                List<Artikel> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(data); // Show all data if no query
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Artikel artikel : data) {
                        if (artikel.getJudul().toLowerCase().contains(filterPattern)) {
                            filteredList.add(artikel); // Add matching articles to filtered list
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataFiltered.clear();
                dataFiltered.addAll((List) results.values); // Update filtered data
                notifyDataSetChanged(); // Refresh RecyclerView
            }
        };
    }
}
