package com.example.ioana.traveldiaryapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.TripsAdapter;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;
import com.example.ioana.traveldiaryapp.storage.Storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ioana on 17/04/2016.
 */
public class TravelNoteActivity extends AppCompatActivity {
    Button saveNoteTravel, editNoteTravel;
    ImageView destinationImage;
    TextView destination, address, dateOfVisit, description;
    RadioButton yesCbx, noCbx;
    int tripIndex;
    Trip trip;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_note_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home_icon);


        destinationImage = (ImageView) findViewById(R.id.destinationImage);
        destination = (TextView) findViewById(R.id.destinationTxtId);
        address = (TextView) findViewById(R.id.addressTxtId);
        dateOfVisit = (TextView) findViewById(R.id.dateOfVisitTxtId);
        description = (TextView) findViewById(R.id.descriptionTxtId);
        yesCbx = (RadioButton) findViewById(R.id.yesCbx);
        noCbx = (RadioButton) findViewById(R.id.noCbx);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tripIndex = extras.getInt("TripItemIndex");
            trip = Service.getTripByIndex(tripIndex);

            destination.setText(trip.getDestination());
            address.setText(trip.getAddress());
            dateOfVisit.setText(trip.getDateOfVisit());
            description.setText(trip.getDescription());
            yesCbx.setChecked(trip.getYesCbx());
            noCbx.setChecked(!trip.getYesCbx());

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        saveNoteTravel = (Button) findViewById(R.id.saveNoteBtnId);
        editNoteTravel = (Button) findViewById(R.id.edit);
        switch (item.getItemId()) {
            case R.id.edit:
                if (item.isChecked()) {
                    saveNoteTravel.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Som", Toast.LENGTH_LONG).show();
                }
        }
        return true;
    }

}