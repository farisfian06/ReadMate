package com.example.readmate;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArtikelDao {
    @Insert
    void insert(Artikel artikel);

    @Query("SELECT * FROM artikel_table ORDER BY tanggal DESC")
    LiveData<List<Artikel>> getAllArtikel();
}

