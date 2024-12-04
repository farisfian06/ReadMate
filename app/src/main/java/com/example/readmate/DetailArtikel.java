package com.example.readmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class DetailArtikel extends AppCompatActivity implements View.OnClickListener {

    private TextView tvJudul;
    private TextView tvTanggal;
    private TextView tvIsi;
    private ImageView ivThumbnail;
    private Artikel artikel;
    private FirebaseDatabase db;
    private DatabaseReference appdbBookmark;
    private DatabaseReference appdbComment;
    private Comment komentar;
    private TextView tvNama;
    private TextView tvIsiKomentar;
    private ImageView ivKomentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        ImageButton backBtn = findViewById(R.id.backBtn);
        ImageButton bookmarkBtn = findViewById(R.id.bookmarkBtn);
        ImageButton commentBtn = findViewById(R.id.commentBtn);
        
        this.tvNama = findViewById(R.id.tvNama);
        this.tvIsiKomentar = findViewById(R.id.tvIsiKomentar);
        this.ivKomentar = findViewById(R.id.ivKomentar);

        this.artikel = (Artikel) getIntent().getSerializableExtra("artikel");

        this.ivThumbnail = findViewById(R.id.imageView5);
        this.tvJudul = findViewById(R.id.tvJudul);
        this.tvTanggal = findViewById(R.id.tvTanggal);
        this.tvIsi = findViewById(R.id.tvIsiArtikel);
        String isiArtikel = artikel.getIsi();
        if (isiArtikel!= null && !isiArtikel.isEmpty()){
            isiArtikel = isiArtikel.replace("\\n", "\n");
        }

        this.tvIsi.setText(isiArtikel);
        this.tvTanggal.setText(artikel.getTanggal());
        this.tvJudul.setText(artikel.getJudul());
        if (artikel.getThumbnail() != null && !artikel.getThumbnail().isEmpty()) {
            Picasso.get()
                    .load(artikel.getThumbnail())
                    .placeholder(R.drawable.thumbnail1) // Gambar sementara saat loading
                    .error(R.drawable.ic_launcher_background)
                    .into(this.ivThumbnail);
        }

        String firebaseUrl = "https://readmate-37771-default-rtdb.asia-southeast1.firebasedatabase.app/";

        this.db = FirebaseDatabase.getInstance(firebaseUrl);
        this.appdbBookmark = this.db.getReference("bookmarks");
        this.appdbComment = this.db.getReference("comments");

        this.appdbComment.orderByChild("artikelId").equalTo(artikel.getId())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            DataSnapshot firstSnapshot = snapshot.getChildren().iterator().next();
                            Comment c = firstSnapshot.getValue(Comment.class);
                            Log.e("komen" ,c.getIsi());
                            komentar = c;
                            if (c != null) {
                                // Atur nilai komentar ke TextView setelah data tersedia
                                tvIsiKomentar.setText(c.getIsi());
                                tvNama.setText(c.getNama());

                                if (c.getProfile() != null && !c.getProfile().isEmpty()) {
                                    Picasso.get()
                                            .load(c.getProfile())
                                            .placeholder(R.drawable.profile1) // Gambar sementara saat loading
                                            .error(R.drawable.ic_launcher_background)
                                            .into(ivKomentar);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });

//        if (komentar!= null){
//            this.tvIsiKomentar.setText(komentar.getIsi());
//            this.tvNama.setText(komentar.getNama());
//            if (komentar.getProfile() != null && !komentar.getProfile().isEmpty()) {
//                Picasso.get()
//                        .load(komentar.getProfile())
//                        .placeholder(R.drawable.profile1) // Gambar sementara saat loading
//                        .error(R.drawable.ic_launcher_background)
//                        .into(this.ivKomentar);
//            }
//        }

        bookmarkBtn.setOnClickListener(this);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk membuka CommentActivity
                Intent intent = new Intent(DetailArtikel.this, CommentActivity.class);
                intent.putExtra("artikel", artikel);
                startActivity(intent);  // Memulai aktivitas baru
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bookmarkBtn) {
         String id = this.appdbBookmark.push().getKey();
            Bookmark bookmark = new Bookmark(id, artikel);

            // Simpan bookmark ke Firebase
            this.appdbBookmark.child(id).setValue(bookmark)
                    .addOnSuccessListener(aVoid -> {
                        // Tampilkan pesan sukses
                        Toast.makeText(DetailArtikel.this, "Artikel berhasil ditambahkan ke bookmark", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // Tampilkan pesan gagal
                        Toast.makeText(DetailArtikel.this, "Gagal menambahkan ke bookmark", Toast.LENGTH_SHORT).show();
                    });

        }
    }
}