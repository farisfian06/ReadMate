package com.example.readmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CommentActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Comment> comments;
    private RecyclerView rvComment;
    private CommentAdapter commentAdapter;
    private EditText commentForm;
    private ImageButton btSend;
    private Handler handler;
    private FirebaseDatabase db;
    private DatabaseReference appdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ImageButton backBtn = findViewById(R.id.backBtn);
        this.commentForm = findViewById(R.id.commentForm);

        this.btSend = findViewById(R.id.sendBtn);
        this.btSend.setOnClickListener(this);

        this.comments = new ArrayList<Comment>();

        this.commentAdapter = new CommentAdapter(this, comments);
        this.rvComment = findViewById(R.id.rvComment);
        this.rvComment.setLayoutManager(new LinearLayoutManager(this));
        this.rvComment.setAdapter(commentAdapter);

        String firebaseUrl = "https://readmate-37771-default-rtdb.asia-southeast1.firebasedatabase.app/";

        this.db = FirebaseDatabase.getInstance(firebaseUrl);
        this.appdb = this.db.getReference("comments");

        this.commentAdapter.setAppDb(appdb);

        this.appdb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.clear();
                for (DataSnapshot s: snapshot.getChildren()) {
                    Comment c = s.getValue(Comment.class);
                    comments.add(c);
                }
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendBtn) {
            String comment = commentForm.getText().toString();
            if (!comment.isEmpty()){
                String id = this.appdb.push().getKey();
                Comment c = new Comment(comment, "user", "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
                c.setId(id);
                this.appdb.child(id).setValue(c);
                Toast.makeText(this, "Komentar berhasil dikirim", Toast.LENGTH_SHORT).show();
            }
            commentForm.setText("");
        }

    }
}