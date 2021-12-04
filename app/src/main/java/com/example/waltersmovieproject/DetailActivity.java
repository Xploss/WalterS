package com.example.waltersmovieproject;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private String movId;
    private final Map<String, String> movDetail = new HashMap<String, String>();
    private static String url = "https://api.themoviedb.org/3/movie/popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US&page=1";
    private static String url_img = "https://image.tmdb.org/t/p/w500";
    Toolbar tbMvDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tbMvDetail = findViewById(R.id.tbDetailMovie);
        tbMvDetail.setTitle("Detail");
        setSupportActionBar(tbMvDetail);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        HashMap<String,String> movieDetail = (HashMap<String, String>) intent.getSerializableExtra("mapDetail");
        this.movId = id;


        TextView title = findViewById(R.id.title);
        TextView overview = findViewById(R.id.overview);
        TextView rating = findViewById(R.id.rating);
        TextView vote = findViewById(R.id.vote);
        TextView release = findViewById(R.id.release);
        ImageView movieBackdrop = findViewById(R.id.movie_backdrop);
        ImageView moviePoster = findViewById(R.id.movie_poster);


        title.setText(movieDetail.get("title"));
        rating.setText(movieDetail.get("rating"));
        vote.setText(movieDetail.get("votes"));
        release.setText(movieDetail.get("date"));
        overview.setText(movieDetail.get("overview"));

        String urlBackdrop = movieDetail.get("backdrop");
        String urlPoster = movieDetail.get("poster");
        Glide.with(movieBackdrop.getContext())
                .load(urlBackdrop)
                .into(movieBackdrop);
        Glide.with(moviePoster.getContext())
                .load(urlPoster)
                .into(moviePoster);
    }


}



