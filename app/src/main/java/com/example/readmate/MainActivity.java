package com.example.readmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private List<Artikel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] thumbnail = {R.drawable.thumbnail1, R.drawable.thumbnail2, R.drawable.thumbnail3, R.drawable.thumbnail4, R.drawable.thumbnail5, R.drawable.thumbnail6, R.drawable.thumbnail7 };

        this.rvJudulArtikel = this.findViewById(R.id.rvJudulArtikel);

        List<Artikel> data = new ArrayList<>();
        data.add(new Artikel("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "12 September 2024", "Politik", thumbnail[0]));
        data.add(new Artikel("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "08 Oktober 2024", "News", thumbnail[1]));
        data.add(new Artikel("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "08 Oktober 2024", "Global", thumbnail[2]));
        data.add(new Artikel("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "07 Oktober 2024", "Olahraga", thumbnail[3]));
        data.add(new Artikel("Mobil Listrik Lamborghini Baru Muncul 2028", "08 Oktober 2024", "Otomotif", thumbnail[4]));
        data.add(new Artikel("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "08 Oktober 2024", "Edukasi", thumbnail[5]));
        data.add(new Artikel("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "08 Oktober 2024", "Teknologi", thumbnail[6]));
        this.data = data;

        this.artikelAdapter = new ArtikelAdapter(this, data);
        this.rvJudulArtikel.setAdapter(this.artikelAdapter);
        this.rvJudulArtikel.setLayoutManager(
                new LinearLayoutManager(this)
        );
    }
}