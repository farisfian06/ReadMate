package com.example.readmate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private FirebaseFirestore db;
    private ImageButton searchButton;
    private SearchView searchView;

    private List<Artikel> data;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rvJudulArtikel = view.findViewById(R.id.rvJudulArtikel);
        this.data = new ArrayList<>();
        this.artikelAdapter = new ArtikelAdapter(getContext(), data);
        this.rvJudulArtikel.setAdapter(this.artikelAdapter);
        this.rvJudulArtikel.setLayoutManager(new LinearLayoutManager(getContext()));

        db = FirebaseFirestore.getInstance();
        db.collection("artikel").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                for (DocumentChange dc: value.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        data.add(dc.getDocument().toObject(Artikel.class));
                    }
                    artikelAdapter.notifyDataSetChanged();
                }
            }
        });

        searchButton = view.findViewById(R.id.searchButton);
        searchView = view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        searchButton.setOnClickListener(v -> {
            searchView.setVisibility(View.VISIBLE); // Tampilkan SearchView
            searchView.requestFocus(); // Fokus pada SearchView
        });

        searchView.setOnCloseListener(() -> {
            searchView.setVisibility(View.GONE); // Sembunyikan SearchView
            return false;
        });

    }

    private void filterList(String text) {
        List<Artikel> filteredList = new ArrayList<>();
        for (Artikel artikel : data) {
            if (artikel.getJudul().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(artikel);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "Artikel tidak ditemukan", Toast.LENGTH_SHORT).show();
        } else{
            artikelAdapter.setFilteredList(filteredList);
        }
    }
}