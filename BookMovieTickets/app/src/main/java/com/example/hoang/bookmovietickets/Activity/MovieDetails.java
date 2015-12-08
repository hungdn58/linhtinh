package com.example.hoang.bookmovietickets.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.R;

public class MovieDetails extends AppCompatActivity {

    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieDes;
    private Button btnBook;
    private ToggleButton isFavorite;
    private RatingBar ratingBar;
    private DBHelper dbHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControls();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        id = bundle.getInt("ID");
        MovieModel model = dbHelper.getMovie(id);
        movieTitle.setText(model.getTitle());
        int imageResource = this.getResources().getIdentifier(model.getImage(), null, this.getPackageName());
        Drawable drawable = this.getResources().getDrawable(imageResource);
        movieImage.setImageDrawable(drawable);
        movieDes.setText(model.getDescription());
        isFavorite.setChecked(model.isFavorited());
        ratingBar.setRating(model.getRating());
        addEvents();
    }

    public void getControls(){
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieDes = (TextView) findViewById(R.id.movieDes);
        movieImage = (ImageView) findViewById(R.id.movieImage);
        isFavorite = (ToggleButton) findViewById(R.id.btnFavorite);
        btnBook = (Button) findViewById(R.id.btnBookTicket);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        DrawableCompat.setTint(ratingBar.getProgressDrawable(), Color.YELLOW);
        dbHelper = new DBHelper(this);
    }

    public void addEvents(){
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this, BookTicketsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                intent.putExtra("DATA", bundle);
                startActivity(intent);
            }
        });

        isFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MovieModel model = dbHelper.getMovie(id);
                    model.setFavorited(true);
                    dbHelper.updateMovie(model);
                } else {
                    MovieModel model = dbHelper.getMovie(id);
                    model.setFavorited(false);
                    dbHelper.updateMovie(model);
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                MovieModel model = dbHelper.getMovie(id);
                model.setRating(rating);
                dbHelper.updateRatingMovie(model);
            }
        });
    }

}
