package com.example.readmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class DetailArtikel extends AppCompatActivity implements View.OnClickListener {

    private TextView tvJudul;
    private TextView tvTanggal;
    private TextView tvIsi;
    private ImageView ivThumbnail;

    private Artikel artikel;
    private FirebaseDatabase db;
    private DatabaseReference appdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        ImageButton backBtn = findViewById(R.id.backBtn);
        ImageButton bookmarkBtn = findViewById(R.id.bookmarkBtn);
        ImageButton commentBtn = findViewById(R.id.commentBtn);

        this.artikel = (Artikel) getIntent().getSerializableExtra("artikel");

        this.ivThumbnail = findViewById(R.id.imageView5);
        this.tvJudul = findViewById(R.id.tvJudul);
        this.tvTanggal = findViewById(R.id.tvTanggal);
        this.tvIsi = findViewById(R.id.tvIsiArtikel);
        this.tvIsi.setText(artikel.getIsi());
        this.tvTanggal.setText(artikel.getTanggal());
        this.tvJudul.setText(artikel.getJudul());

        if (artikel.getThumbnail() != null && !artikel.getThumbnail().isEmpty()) {
            Picasso.get()
                    .load(artikel.getThumbnail()) // asumsi `getProfileUrl` mengembalikan URL gambar profil
                    .placeholder(R.drawable.thumbnail1) // Gambar sementara saat loading
                    .error(R.drawable.ic_launcher_background)
                    .into(this.ivThumbnail);
        }

        String firebaseUrl = "https://readmate-37771-default-rtdb.asia-southeast1.firebasedatabase.app/";

        this.db = FirebaseDatabase.getInstance(firebaseUrl);
        this.appdb = this.db.getReference("bookmarks");

        bookmarkBtn.setOnClickListener(this);

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk membuka CommentActivity
                Intent intent = new Intent(DetailArtikel.this, CommentActivity.class);
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
         String id = this.appdb.push().getKey();
            Bookmark bookmark = new Bookmark(id, artikel);

            // Simpan bookmark ke Firebase
            this.appdb.child(id).setValue(bookmark)
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