package com.example.readmate;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArtikelAdapter artikelAdapter;
    private RecyclerView rvJudulArtikel;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi RecyclerView
        rvJudulArtikel = findViewById(R.id.rvJudulArtikel);
        rvJudulArtikel.setLayoutManager(new LinearLayoutManager(this));

        // Data dummy untuk memastikan RecyclerView bekerja
        List<Artikel> data = new ArrayList<>();
        artikelAdapter = new ArtikelAdapter(this, data);
        rvJudulArtikel.setAdapter(artikelAdapter);

        // Inisialisasi SearchView
        searchView = findViewById(R.id.searchView);
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

        // Tombol untuk toggle visibilitas SearchView
        ImageButton searchButton = findViewById(R.id.imageButton);
        searchButton.setOnClickListener(v -> toggleSearchVisibility());

        // Inisialisasi Firebase Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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

        // Tambahkan data dummy ke Firestore
//        Artikel artikel = new Artikel("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "12 September 2024", "Politik", "https://akcdn.detik.net.id/visual/2024/09/10/menkominfo-budi-arie-sudah-pelajari-akun-fufufafa-sebut-bukan-gibran_169.jpeg?w=480&q=90");
//        Artikel artikel1 = new Artikel("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "08 Oktober 2024", "News", "https://asset.kompas.com/crops/ngAcP5P_rXuzIVwBCwbLLVVhvPA=/413x0:2033x1080/1200x800/data/photo/2024/10/08/6704caec37419.jpg");
//        Artikel artikel2 = new Artikel("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "08 Oktober 2024", "Global", "https://asset.kompas.com/crops/qgQsJlC_UpDyfIZCK2sx567KPSY=/0x0:1023x682/1200x800/data/photo/2024/10/04/66ff98ff9e1e0.jpg");
//        Artikel artikel3 = new Artikel("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "07 Oktober 2024", "Olahraga", "https://asset.kompas.com/crops/kOTV6RBOOg2P4u_3_YIiVd1q9tw=/0x0:698x465/1200x800/data/photo/2024/10/07/6703262bee534.jpeg");
//        Artikel artikel4 = new Artikel("Mobil Listrik Lamborghini Baru Muncul 2028", "08 Oktober 2024", "Otomotif", "https://asset.kompas.com/crops/dWBdKxhjp1zrAGxIsG6VacjhXRY=/60x0:924x576/1200x800/data/photo/2023/03/30/64253fe84cc8a.jpg");
//        Artikel artikel5 = new Artikel("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "08 Oktober 2024", "Edukasi", "https://asset.kompas.com/crops/w9RmP2kWqs_RDxvQIOjQPw4BgyQ=/205x478:1843x1570/1200x800/data/photo/2024/10/08/6704ca24948cc.jpg");
//        Artikel artikel6 = new Artikel("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "08 Oktober 2024", "Teknologi", "https://www.olx.co.id/news/wp-content/uploads/2024/10/kaspersky.webp");

//        //Menambahkan data dummy ke Firestore
//        db.collection("artikel").add(new Artikel("Menkominfo Segera Umumkan Pemilik Akun Fufufafa, Klaim Bukan Gibran", "12 September 2024", "Politik", "https://akcdn.detik.net.id/visual/2024/09/10/menkominfo-budi-arie-sudah-pelajari-akun-fufufafa-sebut-bukan-gibran_169.jpeg?w=480&q=90"));
//        db.collection("artikel").add(new Artikel("Momen Dasco Telepon Prabowo Saat Rapat dengan Solidaritas Hakim Indonesia", "08 Oktober 2024", "News", "https://asset.kompas.com/crops/ngAcP5P_rXuzIVwBCwbLLVVhvPA=/413x0:2033x1080/1200x800/data/photo/2024/10/08/6704caec37419.jpg"));
//        db.collection("artikel").add(new Artikel("Israel Serang Beirut Selatan, Hizbullah Tembakkan Roket Dekat Tel Aviv", "08 Oktober 2024", "Global", "https://asset.kompas.com/crops/qgQsJlC_UpDyfIZCK2sx567KPSY=/0x0:1023x682/1200x800/data/photo/2024/10/04/66ff98ff9e1e0.jpg"));
//        db.collection("artikel").add(new Artikel("Shin Tae-yong: Maarten Paes Agak Terlambat Datang", "07 Oktober 2024", "Olahraga", "https://asset.kompas.com/crops/kOTV6RBOOg2P4u_3_YIiVd1q9tw=/0x0:698x465/1200x800/data/photo/2024/10/07/6703262bee534.jpeg"));
//        db.collection("artikel").add(new Artikel("Mobil Listrik Lamborghini Baru Muncul 2028", "08 Oktober 2024", "Otomotif", "https://asset.kompas.com/crops/dWBdKxhjp1zrAGxIsG6VacjhXRY=/60x0:924x576/1200x800/data/photo/2023/03/30/64253fe84cc8a.jpg"));
//        db.collection("artikel").add(new Artikel("AHY Berhasil Lulus Doktor di Unair, Ini Isi Disertasinya", "08 Oktober 2024", "Edukasi", "https://asset.kompas.com/crops/w9RmP2kWqs_RDxvQIOjQPw4BgyQ=/205x478:1843x1570/1200x800/data/photo/2024/10/08/6704ca24948cc.jpg"));
//        db.collection("artikel").add(new Artikel("Google Hapus Antivirus Kaspersky dari Play Store, Kenapa?", "08 Oktober 2024", "Teknologi", "https://www.olx.co.id/news/wp-content/uploads/2024/10/kaspersky.webp"));
    }

    // Toggle SearchView visibility
    private void toggleSearchVisibility() {
        if (searchView.getVisibility() == SearchView.GONE) {
            Log.d("MainActivity", "SearchView: Ditampilkan.");
            searchView.setVisibility(SearchView.VISIBLE);
            searchView.setIconified(false); // Fokuskan pada SearchView
        } else {
            Log.d("MainActivity", "SearchView: Disembunyikan.");
            searchView.setVisibility(SearchView.GONE);
            artikelAdapter.getFilter().filter(""); // Reset pencarian
        }
    }
}
