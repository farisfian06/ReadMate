package com.example.readmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DetailArtikel extends AppCompatActivity {

    private TextView tvJudul;
    private TextView tvTanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        ImageButton commentBtn = findViewById(R.id.commentBtn);

        Intent i = getIntent();

        String judul = i.getStringExtra("judul");
        String tanggal = i.getStringExtra("tanggal");

        this.tvJudul = findViewById(R.id.tvJudul);
        this.tvTanggal = findViewById(R.id.tvTanggal);
        this.tvTanggal.setText(tanggal);
        this.tvJudul.setText(judul);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new CommentFragment());
        fragmentTransaction.commit();

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk membuka CommentActivity
                Intent intent = new Intent(DetailArtikel.this, CommentActivity.class);
                startActivity(intent);  // Memulai aktivitas baru
            }
        });
    }
}