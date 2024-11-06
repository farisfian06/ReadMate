package com.example.readmate;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private List<Artikel> data;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] thumbnail = {
                R.drawable.thumbnail1, R.drawable.thumbnail2, R.drawable.thumbnail3,
                R.drawable.thumbnail4, R.drawable.thumbnail5, R.drawable.thumbnail6, R.drawable.thumbnail7
        };

        // Initialize RecyclerView
        rvJudulArtikel = findViewById(R.id.rvJudulArtikel);

        // Initialize data list
        data = new ArrayList<>();

        // Initialize RequestQueue
        queue = Volley.newRequestQueue(this);

        // Set up Adapter
        artikelAdapter = new ArtikelAdapter(this, data);
        rvJudulArtikel.setAdapter(artikelAdapter);
        rvJudulArtikel.setLayoutManager(new LinearLayoutManager(this));

        // Define the URL and StringRequest
        String url = "http://10.0.2.2/myapi/artikel.json";
        StringRequest req = new StringRequest(
                Request.Method.GET, url,
                response -> {
                    Gson gson = new Gson();
                    Artikel[] array = gson.fromJson(response, Artikel[].class);
                    for (Artikel artikel : array) {
                        data.add(artikel);
                    }
                    artikelAdapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show()
        );

        // Add request to the queue
        queue.add(req);
    }
}
