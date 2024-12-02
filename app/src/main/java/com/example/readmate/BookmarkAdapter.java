package com.example.readmate;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter{

    private final Context ctx;
    private final List<Bookmark> data;
    private DatabaseReference appDb;

    public BookmarkAdapter(Context ctx, List<Bookmark> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class BookmarkVh extends RecyclerView.ViewHolder {
        private final TextView judul;
        private final TextView waktu;
        private final ImageView gambarArtikel;
        private final ImageButton btDelete;
        private Bookmark bookmark;


        public BookmarkVh(@NonNull View itemView) {
            super(itemView);
            this.judul = itemView.findViewById(R.id.judulArtikel);
            this.waktu = itemView.findViewById(R.id.tanggal);
            this.gambarArtikel = itemView.findViewById(R.id.gambar);
            this.btDelete = itemView.findViewById(R.id.bookmarkBtnCard);

            this.btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appDb.child(bookmark.getId()).removeValue();
                }
            });
        }

        public void bind (Bookmark b){
            this.bookmark = b;
            this.judul.setText(b.getArtikel().getJudul());
            this.waktu.setText(b.getArtikel().getTanggal());

            if (b.getArtikel().getThumbnail() != null && !b.getArtikel().getThumbnail().isEmpty()) {
                Picasso.get()
                        .load(b.getArtikel().getThumbnail()) // asumsi `getProfileUrl` mengembalikan URL gambar profil
                        .placeholder(R.drawable.profile1) // Gambar sementara saat loading
                        .error(R.drawable.ic_launcher_background)       // Gambar jika terjadi error
                        .into(this.gambarArtikel);
            }

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
        BookmarkAdapter.BookmarkVh vh = (BookmarkAdapter.BookmarkVh) holder;
        vh.bind(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
    public void setAppDb(DatabaseReference appDb){
        this.appDb = appDb;
    }

}
