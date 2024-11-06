package com.example.readmate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter{

    private final Context ctx;
    private final List<Bookmark> data;

    public BookmarkAdapter(Context ctx, List<Bookmark> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class BookmarkVh extends RecyclerView.ViewHolder {
        private final TextView judul;
        private final TextView waktu;
        private final ImageView gambarArtikel;


        public BookmarkVh(@NonNull View itemView) {
            super(itemView);
            this.judul = itemView.findViewById(R.id.judulArtikel);
            this.waktu = itemView.findViewById(R.id.tanggal);
            this.gambarArtikel = itemView.findViewById(R.id.gambar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowview = LayoutInflater.from(this.ctx).inflate(R.layout.bookmark_card_layout, parent, false);
        RecyclerView.ViewHolder vh = new BookmarkVh(rowview);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Bookmark b = this.data.get(position);
        BookmarkAdapter.BookmarkVh vh = (BookmarkAdapter.BookmarkVh) holder;

        vh.judul.setText(b.getJudul());
        vh.waktu.setText(b.getWaktu());
//        vh.gambarArtikel.setImageResource(b.gambarArtikel);

    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}
