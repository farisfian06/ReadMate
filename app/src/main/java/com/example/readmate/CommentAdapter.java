package com.example.readmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private final List<Comment> data;

    public CommentAdapter(Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class CommentVh extends RecyclerView.ViewHolder {

        private final TextView tvNama;
        private final TextView tvIsi;
        private final ImageView imageView3;

        public CommentVh(@NonNull View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvIsi = itemView.findViewById(R.id.tvIsi);
            this.imageView3 = itemView.findViewById(R.id.imageView3);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowview = LayoutInflater.from(this.ctx).inflate(R.layout.comment_card_layout, parent, false);
        RecyclerView.ViewHolder vh = new CommentVh(rowview);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment c = this.data.get(position);
        CommentVh vh = (CommentVh) holder;

        vh.tvNama.setText(c.getNama());
        vh.tvIsi.setText(c.getIsi());
//        vh.imageView3.setImageResource(c.profile);
        if (c.getProfile() != null && !c.getProfile().isEmpty()) {
            Picasso.get()
                    .load(c.getProfile()) // asumsi `getProfileUrl` mengembalikan URL gambar profil
                    .placeholder(R.drawable.profile1) // Gambar sementara saat loading
                    .error(R.drawable.ic_launcher_background)       // Gambar jika terjadi error
                    .into(vh.imageView3);
        }
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


}
