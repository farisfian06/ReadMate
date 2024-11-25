package com.example.readmate;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ArtikelRepository {
    private final ArtikelDao artikelDao;
    private final LiveData<List<Artikel>> allArtikel;

    public ArtikelRepository(Application application) {
        ArtikelDatabase database = ArtikelDatabase.getInstance(application);
        artikelDao = database.artikelDao();
        allArtikel = artikelDao.getAllArtikel();
    }

    public LiveData<List<Artikel>> getAllArtikel() {
        Log.d("ArtikelRepository", "Mengambil semua artikel dari database.");
        return allArtikel;
    }
}
