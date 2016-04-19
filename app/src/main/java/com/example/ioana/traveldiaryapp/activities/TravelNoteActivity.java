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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;

/**
 * Created by Ioana on 17/04/2016.
 */
public class TravelNoteActivity extends AppCompatActivity {
     Button saveNoteTravel;
     Button editNoteTravel;
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_note_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home_icon);



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
        switch (item.getItemId()){
            case R.id.edit:
                if (item.isChecked()){
                    saveNoteTravel.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Som",Toast.LENGTH_LONG).show();
                }
    }
        return true;
    }

}