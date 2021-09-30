package com.example.waltersmovieproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.waltersmovieproject.moviemodel.ResultsItem;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{

    private List<ResultsItem> dataMovie;
    private Context mContext;

    public RecycleAdapter(List<ResultsItem> dataMovie, Context mContext){
        this.dataMovie = dataMovie;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.nameMovie.setText(dataMovie.get(i).getTitle());
        viewHolder.ratingMovie.setText(Integer.toString(dataMovie.get(i).getVoteAverage()));
        Glide.with(mContext).load(dataMovie.get(i).getPosterPath()).into(viewHolder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return dataMovie.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        @BindView(R.id.name_movie)
        TextView nameMovie;
        @BindView(R.id.rating_movie)
        TextView ratingMovie;
        @BindView(R.id.img_movie)
        ImageView imgMovie;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
