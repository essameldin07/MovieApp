package com.example.essam.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class   MainActivity extends AppCompatActivity implements MoviesFragment.Callback  {



    private boolean mTwoPane;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new DetailFragment(),
                                DetailFragment.TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }



    @Override
    public void onItemSelected(Movie movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_MOVIE, movie);

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment, DetailFragment.TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, DetailActivity.class)
                    .putExtra(DetailFragment.DETAIL_MOVIE, movie);
            startActivity(intent);
        }
    }
   /*public void viewDetails(Movie movie){
        if(tab){
            DetailFragment detailFragment = DetailFragment.getInstance(movie);
            getSupportFragmentManager().beginTransaction().replace(R.id.container,detailFragment).commit();
        }else{
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            startActivity(intent);
        }
    }*/

}

