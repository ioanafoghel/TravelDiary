package com.example.ioana.traveldiaryapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.TripAdapter;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;

/**
 * Created by Ioana on 15/04/2016.
 */
public class TripsActivity extends ListActivity {
    String[] destinations = {"Rome","Paris","Moscow","London","Budapest"};
    int[] images = {R.drawable.rome, R.drawable.paris, R.drawable.mini_moscow, R.drawable.london, R.drawable.budapest};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);

       TripAdapter adapter = new TripAdapter(this, destinations, images);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {

        super.onListItemClick(listView, view, position, id);
        Intent newActivity = new Intent(view.getContext(), TravelNoteActivity.class);
        startActivity(newActivity);
    }


       /* super.onListItemClick(listView, view, position, id);
        Intent intent = new Intent("activities.TripsActivity");
        startActivity(intent);*/

}