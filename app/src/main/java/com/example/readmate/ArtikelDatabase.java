package com.example.readmate;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Artikel.class}, version = 1, exportSchema = false)
public abstract class ArtikelDatabase extends RoomDatabase {
    private static ArtikelDatabase instance;
    private static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(1);

    public abstract ArtikelDao artikelDao();

    public static synchronized ArtikelDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d("ArtikelDatabase", "Inisialisasi database dimulai.");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ArtikelDatabase.class, "artikel_database")
                    .addCallback(roomCallback)
                    .fallbackToDestructiveMigration()
                    .build();
            Log.d("ArtikelDatabase", "Database berhasil diinisialisasi.");
        }
        return instance;
    }


    private static final Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("ArtikelDatabase", "Room callback: Memasukkan data.");
            databaseWriteExecutor.execute(() -> {
                ArtikelDao artikelDao = instance.artikelDao();

                int[] thumbnail = {R.drawable.thumbnail1, R.drawable.thumbnail2, R.drawable.thumbnail3, R.drawable.thumbnail4, R.drawable.thumbnail5, R.drawable.thumbnail6, R.drawable.thumbnail7};
                artikelDao.insert(new Artikel("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "12 September 2024", "Politik", thumbnail[0]));
                artikelDao.insert(new Artikel("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "08 Oktober 2024", "News", thumbnail[1]));
                artikelDao.insert(new Artikel("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "08 Oktober 2024", "Global", thumbnail[2]));
                artikelDao.insert(new Artikel("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "07 Oktober 2024", "Olahraga", thumbnail[3]));
                artikelDao.insert(new Artikel("Mobil Listrik Lamborghini Baru Muncul 2028", "08 Oktober 2024", "Otomotif", thumbnail[4]));
                artikelDao.insert(new Artikel("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "08 Oktober 2024", "Edukasi", thumbnail[5]));
                artikelDao.insert(new Artikel("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "08 Oktober 2024", "Teknologi", thumbnail[6]));

                Log.d("ArtikelDatabase", "Room callback: Data berhasil dimasukkan.");
            });
        }
    };

}
