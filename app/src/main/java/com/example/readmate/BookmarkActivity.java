package com.example.readmate;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
private List<Bookmark> data;
    private RecyclerView rvBookmark;
    private BookmarkAdapter bookmarkAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        List<Bookmark> data = new ArrayList<>();
        this.rvBookmark = findViewById(R.id.rvBookmark);
        data.add(new Bookmark("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "Sabtu, 12 September 2024", R.drawable.thumbnail1));
        data.add(new Bookmark("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "Jum'at, 08 Oktober 2024", R.drawable.thumbnail2));
        data.add(new Bookmark("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "Jum'at, 08 Oktober 2024", R.drawable.thumbnail3));
        data.add(new Bookmark("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "Senin, 07 Oktober 2024",  R.drawable.thumbnail4));
        data.add(new Bookmark("Mobil Listrik Lamborghini Baru Muncul 2028", "Senin, 08 Oktober 2024", R.drawable.thumbnail5));
        data.add(new Bookmark("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "Senin, 08 Oktober 2024", R.drawable.thumbnail6));
        data.add(new Bookmark("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "Senin, 08 Oktober 2024", R.drawable.thumbnail7));

        this.data = data;
        this.bookmarkAdapter = new BookmarkAdapter(this, data);
        this.rvBookmark.setAdapter(bookmarkAdapter);
        this.rvBookmark.setLayoutManager(
                new LinearLayoutManager(this)
        );

    }
}