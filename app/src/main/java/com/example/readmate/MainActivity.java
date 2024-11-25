package com.example.readmate;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new SearchFragment())
                    .commit();
        }

        ImageButton searchButton = findViewById(R.id.imageButton);
        searchButton.setOnClickListener(v -> {
            SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);
            if (searchFragment != null) {
                searchFragment.toggleSearchVisibility();
            }
        });
    }
}
