package com.example.readmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

    public class CommentActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Comment> comments;
    private RecyclerView rvComment;
    private CommentAdapter commentAdapter;
    private EditText commentForm;
    private ImageButton btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageButton backBtn = findViewById(R.id.backBtn);

        this.commentForm = findViewById(R.id.commentForm);

        int[] profile = {R.drawable.profile1, R.drawable.profile2, R.drawable.profile3, R.drawable.profile4, R.drawable.profile5};

        List<Comment> data = new ArrayList<>();
        this.rvComment = findViewById(R.id.rvComment);
        this.btSend = findViewById(R.id.sendBtn);
        this.btSend.setOnClickListener(this);

        data.add(new Comment("Berita informatif", "user 1", profile[0]));
        data.add(new Comment("Woww", "user 2", profile[1]));
        data.add(new Comment("Ooouww", "user 3", profile[2]));
        data.add(new Comment("Keren banget", "user 4", profile[3]));
        data.add(new Comment("Siapa ya kira-kira fufufafa nya", "user 5", profile[4]));
        data.add(new Comment("Menkominfo ", "user 6", profile[1]));
        this.comments = data;

        this.commentAdapter = new CommentAdapter(this, data);
        this.rvComment.setAdapter(commentAdapter);
        this.rvComment.setLayoutManager(new LinearLayoutManager(this));


        // Navigasi ke Activity lain
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ganti MainActivity dengan activity yang kamu tuju
                Intent intent = new Intent(CommentActivity.this, DetailArtikel.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendBtn) {
        String comment = commentForm.getText().toString();
        if (!comment.isEmpty()) {
            Toast.makeText(CommentActivity.this, "Comment berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            // Buat dan tambahkan CardView baru untuk menampilkan komentar
            int index = comments.size()+1;
            comments.add(new Comment(comment, "User "+index, R.drawable.profile1));
            this.commentAdapter.notifyDataSetChanged();
            commentForm.setText(""); // Kosongkan form setelah submit
        }
        }

    }
}