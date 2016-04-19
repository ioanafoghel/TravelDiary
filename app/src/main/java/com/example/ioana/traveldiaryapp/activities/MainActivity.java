package com.example.ioana.traveldiaryapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ioana.traveldiaryapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button btnTrips = (Button) findViewById(R.id.TripsBtn);
        btnTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TravelNoteActivity.class);
                startActivity(intent);
            }
        });
        Button btnAddTrips = (Button) findViewById(R.id.AddBtn);
        btnAddTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTripActivity.class);
                startActivity(intent);
            }
        });

    }
}
