package com.example.hoang.bookmovietickets.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoang.bookmovietickets.Activity.MovieDetails;
import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 11/16/2015.
 */
public class MyPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<MovieModel> movieList = new ArrayList<MovieModel>();
    private MovieModel model;
    private int index;

    public MyPagerAdapter(Context context, List<MovieModel> arr ){
        this.mContext = context;
        this.movieList = arr;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.header_movie_item, container, false);

        TextView movieTitle = (TextView) view.findViewById(R.id.itemTitle);
        final ImageView movieImage = (ImageView) view.findViewById(R.id.itemImage);

        model = movieList.get(position);
        movieTitle.setText(model.getTitle());
        int imageResource = mContext.getResources().getIdentifier(model.getImage(), null, mContext.getPackageName());
        Drawable drawable = mContext.getResources().getDrawable(imageResource);
        movieImage.setImageDrawable(drawable);

        index = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetails.class);
                Bundle bundle = new Bundle();

                if(index == 0){
                    bundle.putInt("ID", model.getId());
                } else if(index < movieList.size()) {
                    bundle.putInt("ID", model.getId()-1);
                } else {
                    bundle.putInt("ID",model.getId());
                }
                intent.putExtra("DATA", bundle);
                mContext.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CardView) object);
    }

}
