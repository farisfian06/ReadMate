package com.example.readmate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private ArtikelViewModel artikelViewModel;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SearchFragment", "onCreateView dipanggil");
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Inisialisasi RecyclerView
        rvJudulArtikel = view.findViewById(R.id.rvJudulArtikel);
        rvJudulArtikel.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Data dummy untuk memastikan RecyclerView bekerja
        List<Artikel> data = new ArrayList<>();

        artikelAdapter = new ArtikelAdapter(requireContext(), data);
        rvJudulArtikel.setAdapter(artikelAdapter);

        // Inisialisasi SearchView
        searchView = view.findViewById(R.id.searchView);
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

        // Menggunakan ViewModel untuk mendapatkan data dari database
        artikelViewModel = new ViewModelProvider(this).get(ArtikelViewModel.class);
        artikelViewModel.getAllArtikel().observe(getViewLifecycleOwner(), artikelList -> {
            Log.d("SearchFragment", "Artikel diterima: " + artikelList.size());
            data.clear();
            data.addAll(artikelList);
            artikelAdapter.notifyDataSetChanged();
        });

        return view;
    }

    // Toggle SearchView visibility
    public void toggleSearchVisibility() {
        if (searchView.getVisibility() == View.GONE) {
            Log.d("SearchFragment", "SearchView: Ditampilkan.");
            searchView.setVisibility(View.VISIBLE);
            searchView.setIconified(false); // Fokuskan pada SearchView
        } else {
            Log.d("SearchFragment", "SearchView: Disembunyikan.");
            searchView.setVisibility(View.GONE);
            if (artikelAdapter != null) {
                artikelAdapter.getFilter().filter(""); // Reset pencarian
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
