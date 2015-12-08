package com.example.hoang.bookmovietickets.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.Helper.SimpleItemTouchHelperCallback;
import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.R;
import com.example.hoang.bookmovietickets.Adapter.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView myHistory;
    private ItemTouchHelper mItemTouchHelper;
    private RVAdapter adapter;
    private List<MovieModel> arr = new ArrayList<MovieModel>();
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myHistory = (RecyclerView) findViewById(R.id.myHistory);
        dbHelper = new DBHelper(this);
        arr = dbHelper.getListFavoriteMovie(1);
        adapter = new RVAdapter(this, arr, 2);
        myHistory.setAdapter(adapter);
        myHistory.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myHistory.setLayoutManager(gridLayoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(myHistory);

    }

    @Override
    protected void onResume() {
        super.onResume();
        arr.clear();
        List<MovieModel> list = new ArrayList<MovieModel>();
        list = dbHelper.getListFavoriteMovie(1);
        arr.addAll(list);
        adapter.notifyDataSetChanged();
    }

}
