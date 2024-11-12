package com.example.readmate;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


interface Request{
    @GET("comment.json")
    Call<List<Comment>> getComment();
}

public class CommentFragment extends Fragment {
    private List<Comment> comments;
    private RecyclerView rvComment;
    private CommentAdapter commentAdapter;

    public CommentFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.comments = new ArrayList<>();
        dataInitialize();
        this.rvComment = view.findViewById(R.id.rvComment);
        this.rvComment.setLayoutManager(new LinearLayoutManager(getContext()));

        this.commentAdapter = new CommentAdapter(getContext(), comments);
        this.rvComment.setAdapter(commentAdapter);
        this.commentAdapter.notifyDataSetChanged();
    }

    private void dataInitialize() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1/myapi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommentActivity.Request request = retrofit.create(CommentActivity.Request.class);

        Call<List<Comment>> call = request.getComment();
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    List<Comment> data = response.body();
                    commentAdapter = new CommentAdapter(getContext(), data);
                    rvComment.setAdapter(commentAdapter);
                    comments = data;
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable throwable) {
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
                Log.e("MainActivity", "Error: " + throwable.getMessage());
            }
        });

    }
}