package com.example.waltersmovieproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;

import com.example.waltersmovieproject.moviemodel.ListMovieModel;
import com.example.waltersmovieproject.moviemodel.ResultsItem;
import com.example.waltersmovieproject.restapi.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvListMovie)
    RecyclerView rvListMovie;

    private List<ResultsItem> listItem;
    private RecycleAdapter adapter;
    private final AppCompatActivity appCompatActivity = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        RestClient.getService().getList().enqueue(new Callback<ListMovieModel>() {
            @Override
            public void onResponse(Call<ListMovieModel> call, Response<ListMovieModel> response) {
                if (response.isSuccessful()){
                    listItem = response.body().getResults();

                    adapter = new RecycleAdapter(listItem, MainActivity.this);
                    rvListMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));;
                    rvListMovie.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ListMovieModel> call, Throwable t) {

            }
        });
    }

    public Activity activity(){
        Context context = this;
        while (context instanceof ContextWrapper){
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}