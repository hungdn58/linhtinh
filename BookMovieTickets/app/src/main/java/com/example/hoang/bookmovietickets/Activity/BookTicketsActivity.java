package com.example.hoang.bookmovietickets.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.hoang.bookmovietickets.Helper.DBHelper;
import com.example.hoang.bookmovietickets.Model.MovieModel;
import com.example.hoang.bookmovietickets.R;

public class BookTicketsActivity extends AppCompatActivity {

    MovieModel model;
    DBHelper dbHelper;
    Button btnCancel, btnSubmit;
    Spinner cinema, date, time, tickets;
    String[] arrCinema, arrDate, arrTime, arrTickets;
    ArrayAdapter<String> adapterCinema, adapterDate, adapterTime, adapterTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tickets);
        getControls();
        addEvents();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        int id = bundle.getInt("ID");
        model = dbHelper.getMovie(id);
    }

    public void getControls(){

        dbHelper = new DBHelper(this);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnSubmit = (Button) findViewById(R.id.submit);

        cinema = (Spinner) findViewById(R.id.cinema);
        date = (Spinner) findViewById(R.id.date);
        tickets = (Spinner) findViewById(R.id.tickets);
        time = (Spinner) findViewById(R.id.time);

        arrCinema = getResources().getStringArray(R.array.cinema);
        arrDate = getResources().getStringArray(R.array.date);
        arrTime = getResources().getStringArray(R.array.time);
        arrTickets = getResources().getStringArray(R.array.ticket);

        adapterCinema = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrCinema);
        adapterDate = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrDate);
        adapterTime = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrTime);
        adapterTickets = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrTickets);

        adapterCinema.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterDate.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterTime.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapterTickets.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        cinema.setAdapter(adapterCinema);
        date.setAdapter(adapterDate);
        time.setAdapter(adapterTime);
        tickets.setAdapter(adapterTickets);
    }

    public void addEvents(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something here
                model.setBooked(true);
                dbHelper.updateMovie(model);
                Intent intent = new Intent(BookTicketsActivity.this, MyListTicketsActivity.class);
                startActivity(intent);
            }
        });
    }
}
