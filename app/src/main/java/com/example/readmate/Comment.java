package com.example.readmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Comment extends AppCompatActivity {

    private EditText commentForm;
    private LinearLayout commentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageButton backBtn = findViewById(R.id.backBtn);
        ImageButton sendBtn = findViewById(R.id.sendBtn);

        commentForm = findViewById(R.id.commentForm);
        commentContainer = findViewById(R.id.commentContainer);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentForm.getText().toString();
                if (!comment.isEmpty()) {
                    // Buat dan tambahkan CardView baru untuk menampilkan komentar
                    addCommentCard(comment, "User 1"); // Gantikan "User 1" dengan user sesungguhnya jika diperlukan
                    commentForm.setText(""); // Kosongkan form setelah submit
                }
            }
        });


        // Navigasi ke Activity lain
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ganti MainActivity dengan activity yang kamu tuju
                Intent intent = new Intent(Comment.this, DetailArtikel.class);
                startActivity(intent);
            }
        });


    }

    private void addCommentCard(String comment, String userName) {
        // Inflate layout CardView dari XML
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View commentCardView = inflater.inflate(R.layout.comment_card_layout, null);

        // Update konten di dalam CardView
        TextView userTextView = commentCardView.findViewById(R.id.textView5);
        TextView commentTextView = commentCardView.findViewById(R.id.textView6);
        userTextView.setText(userName);
        commentTextView.setText(comment);

        // Tambahkan CardView baru ke container
        commentContainer.addView(commentCardView);
    }
}