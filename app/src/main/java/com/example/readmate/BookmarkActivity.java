package com.example.readmate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
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
//        data.add(new Bookmark("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "Sabtu, 12 September 2024", R.drawable.thumbnail1));
//        data.add(new Bookmark("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "Jum'at, 08 Oktober 2024", R.drawable.thumbnail2));
//        data.add(new Bookmark("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "Jum'at, 08 Oktober 2024", R.drawable.thumbnail3));
//        data.add(new Bookmark("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "Senin, 07 Oktober 2024",  R.drawable.thumbnail4));
//        data.add(new Bookmark("Mobil Listrik Lamborghini Baru Muncul 2028", "Senin, 08 Oktober 2024", R.drawable.thumbnail5));
//        data.add(new Bookmark("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "Senin, 08 Oktober 2024", R.drawable.thumbnail6));
//        data.add(new Bookmark("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "Senin, 08 Oktober 2024", R.drawable.thumbnail7));
//
//        this.data = data;
        this.bookmarkAdapter = new BookmarkAdapter(this, data);
        this.rvBookmark.setAdapter(bookmarkAdapter);
        this.rvBookmark.setLayoutManager(
                new LinearLayoutManager(this)
        );
        new FetchBookmarksTask().execute("http://192.168.3.108/myapi/bookmark.json");

    }
    private class FetchBookmarksTask extends AsyncTask<String, Void, List<Bookmark>> {
        @Override
        protected List<Bookmark> doInBackground(String... urls) {
            List<Bookmark> bookmarks = new ArrayList<>();
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                // Membuat URL dan membuka koneksi
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Membaca respons dari server
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                // Jika tidak ada respons, return null
                if (buffer.length() == 0) {
                    return null;
                }

                // Parsing JSON response ke dalam List Bookmark menggunakan GSON
                String jsonResponse = buffer.toString();
                Type listType = new TypeToken<ArrayList<Bookmark>>() {}.getType();
                bookmarks = new Gson().fromJson(jsonResponse, listType);

            } catch (Exception e) {
                Log.e("BookmarkActivity", "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception e) {
                        Log.e("BookmarkActivity", "Error closing stream", e);
                    }
                }
            }
            return bookmarks;
        }

        @Override
        protected void onPostExecute(List<Bookmark> bookmarks) {
            if (bookmarks != null && !bookmarks.isEmpty()) {
                // Mengatur Adapter dengan data dari API
                bookmarkAdapter = new BookmarkAdapter(BookmarkActivity.this, bookmarks);
                rvBookmark.setAdapter(bookmarkAdapter);
            } else {
                Toast.makeText(BookmarkActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}