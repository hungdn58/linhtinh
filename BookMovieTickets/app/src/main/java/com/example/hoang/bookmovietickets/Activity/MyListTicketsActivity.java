package com.example.hoang.bookmovietickets.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

public class MyListTicketsActivity extends AppCompatActivity {

    private RecyclerView myListTickets;
    private RVAdapter adapter;
    private List<MovieModel> arr;
    private ItemTouchHelper mItemTouchHelper;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_tickets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myListTickets = (RecyclerView) findViewById(R.id.myListTickets);
        dbHelper = new DBHelper(this);
        arr = dbHelper.getListBookedMovie(1);
        adapter = new RVAdapter(this, arr, 1);
        myListTickets.setAdapter(adapter);
        myListTickets.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        myListTickets.setLayoutManager(linearLayoutManager);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(myListTickets);

    }

    @Override
    protected void onResume() {
        super.onResume();
        arr.clear();
        List<MovieModel> list = new ArrayList<MovieModel>();
        list = dbHelper.getListBookedMovie(1);
        arr.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
