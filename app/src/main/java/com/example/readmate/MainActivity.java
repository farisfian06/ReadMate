package com.example.readmate;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.readmate.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvJudulArtikel;
    private ArtikelAdapter artikelAdapter;
    private List<Artikel> data;
    private ImageButton bookmarkBtn;

    private FirebaseFirestore db;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeBottom){
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.bookmarkBottom){
                replaceFragment(new BookmarkFragment());
            }
            return true;
        });

//        this.bookmarkBtn = findViewById(R.id.bookmarkBtn);

//        this.rvJudulArtikel = this.findViewById(R.id.rvJudulArtikel);
//        List<Artikel> data = new ArrayList<>();
//        this.artikelAdapter = new ArtikelAdapter(this, data);
//        this.rvJudulArtikel.setAdapter(this.artikelAdapter);
//        this.rvJudulArtikel.setLayoutManager(
//                new LinearLayoutManager(this)
//        );
//
//        db = FirebaseFirestore.getInstance();
//        db.collection("artikel").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for (DocumentChange dc: value.getDocumentChanges()) {
//                    if (dc.getType() == DocumentChange.Type.ADDED){
//                        data.add(dc.getDocument().toObject(Artikel.class));
//                    }
//                artikelAdapter.notifyDataSetChanged();
//                }
//            }
//        });

//        bookmarkBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Intent untuk membuka CommentActivity
//                Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
//                startActivity(intent);  // Memulai aktivitas baru
//            }
//        });

    }
    public void replaceFragment(Fragment fragmen){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmen);
        fragmentTransaction.commit();

    }
}