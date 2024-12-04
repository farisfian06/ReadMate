package com.example.readmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter {

    private final Context ctx;
    private final List<Comment> data;
    private DatabaseReference appDb;

    public CommentAdapter(Context ctx, List<Comment> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class CommentVh extends RecyclerView.ViewHolder {

        private final TextView tvNama;
        private final TextView tvIsi;
        private final ImageView imageView3;
        private final ImageButton btDelete;
        private Comment comment;

        public CommentVh(@NonNull View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvIsi = itemView.findViewById(R.id.tvIsiKomentar);
            this.imageView3 = itemView.findViewById(R.id.ivKomentar);
            this.btDelete = itemView.findViewById(R.id.deleteBtn);

            this.btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appDb.child(comment.getId()).removeValue();
                }
            });
        }

        public void bind (Comment c){
            this.comment = c;
            String replace = c.getIsi().replace("\\n", "\n");
            this.tvIsi.setText(replace);
            this.tvNama.setText(c.getNama());

            if (c.getProfile() != null && !c.getProfile().isEmpty()) {
                Picasso.get()
                        .load(c.getProfile()) // asumsi `getProfileUrl` mengembalikan URL gambar profil
                        .placeholder(R.drawable.profile1) // Gambar sementara saat loading
                        .error(R.drawable.ic_launcher_background)       // Gambar jika terjadi error
                        .into(this.imageView3);
            }

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
        CommentVh vh = (CommentVh) holder;
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
