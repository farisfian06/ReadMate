package com.example.readmate;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArtikelViewModel extends AndroidViewModel {
    private final ArtikelRepository repository;
    private final LiveData<List<Artikel>> allArtikel;

    public ArtikelViewModel(@NonNull Application application) {
        super(application);
        repository = new ArtikelRepository(application);
        allArtikel = repository.getAllArtikel();
    }

    public LiveData<List<Artikel>> getAllArtikel() {
        Log.d("ArtikelViewModel", "Artikel diterima oleh ViewModel.");
        return allArtikel;
    }
}
