package com.example.readmate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private List<Artikel> data;
    private RequestQueue queue;
    private SearchView searchView;
    private ImageButton searchButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = view.findViewById(R.id.searchView);
        searchButton = view.findViewById(R.id.imageButton);

        searchButton.setOnClickListener(v -> toggleSearchVisibility());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                artikelAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                artikelAdapter.getFilter().filter(newText);
                return false;
            }
        });

        int[] thumbnail = {
                R.drawable.thumbnail1, R.drawable.thumbnail2, R.drawable.thumbnail3,
                R.drawable.thumbnail4, R.drawable.thumbnail5, R.drawable.thumbnail6, R.drawable.thumbnail7
        };

        rvJudulArtikel = view.findViewById(R.id.rvJudulArtikel);
        data = new ArrayList<>();
        queue = Volley.newRequestQueue(requireContext());

        artikelAdapter = new ArtikelAdapter(requireContext(), data);
        rvJudulArtikel.setAdapter(artikelAdapter);
        rvJudulArtikel.setLayoutManager(new LinearLayoutManager(requireContext()));

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
                error -> Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_LONG).show()
        );

        queue.add(req);

        return view;
    }

    public void toggleSearchVisibility() {
        if (searchView.getVisibility() == View.GONE) {
            searchView.setVisibility(View.VISIBLE);
            searchView.setIconified(false); // Opens the input field
        } else {
            searchView.setVisibility(View.GONE);
            if (artikelAdapter != null) {
                artikelAdapter.getFilter().filter(""); // Resets the filter when search is closed
            }
        }
    }
}