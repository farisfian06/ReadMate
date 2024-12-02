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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BookmarkFragment extends Fragment {

    private List<Bookmark> data;
    private RecyclerView rvBookmark;
    private BookmarkAdapter bookmarkAdapter;
    private FirebaseDatabase db;
    private DatabaseReference appdb;


    public BookmarkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Bookmark> data = new ArrayList<>();
        this.rvBookmark = view.findViewById(R.id.rvBookmark);
        this.bookmarkAdapter = new BookmarkAdapter(getContext(), data);
        this.rvBookmark.setAdapter(bookmarkAdapter);
        this.rvBookmark.setLayoutManager(new LinearLayoutManager(getContext()));

        String firebaseUrl = "https://readmate-37771-default-rtdb.asia-southeast1.firebasedatabase.app/";

        this.db = FirebaseDatabase.getInstance(firebaseUrl);
        this.appdb = this.db.getReference("bookmarks");

        this.bookmarkAdapter.setAppDb(appdb);


        this.appdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@android.support.annotation.NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s: snapshot.getChildren()) {
                    Bookmark b = s.getValue(Bookmark.class);
                    data.add(b);
                }
                bookmarkAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@android.support.annotation.NonNull DatabaseError error) {

            }
        });



    }
}