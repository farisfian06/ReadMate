package com.example.readmate;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add the SearchFragment to the layout
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new SearchFragment())
                    .commit();
        }
    }

    public void toggleSearchVisibility(View view) {
        // Get the SearchFragment instance
        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        if (searchFragment != null) {
            // Call the toggleSearchVisibility method in the SearchFragment
            searchFragment.toggleSearchVisibility();
        }
    }
}