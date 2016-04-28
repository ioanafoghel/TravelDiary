package com.example.ioana.traveldiaryapp.activities;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.TripsAdapter;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ioana on 15/04/2016.
 */
public class TripsActivity extends AppCompatActivity {

    // variable marks the position of element that is selected in context menu
    int selectedPosition;

    final Context context = this;
    ArrayList<Trip> filteredTripsArrayList;
    ListView listView;
    TripsAdapter tripArrayAdapter;
    EditText editSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);
        listView = (ListView) findViewById(R.id.list);
        editSearch = (EditText) findViewById(R.id.search);
        filteredTripsArrayList = new ArrayList<>();
        filteredTripsArrayList.addAll(Service.getTrips());
        tripArrayAdapter = new TripsAdapter(this, 0, filteredTripsArrayList);
        listView.setAdapter(tripArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                Intent tripIndex = new Intent(getApplicationContext(), TravelNoteActivity.class);
                tripIndex.putExtra("TripItemIndex", position);
                startActivity(tripIndex);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                registerForContextMenu(parent);
                openContextMenu(parent);
                return true;
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                String charText = editSearch.getText().toString().toLowerCase();
                filteredTripsArrayList.clear();
                if (charText.length() == 0) {
                    filteredTripsArrayList.addAll(Service.getTrips());
                } else {
                    for (Trip trip : Service.getTrips()) {
                        if (trip.getDestination().toLowerCase().contains(charText)) {
                            filteredTripsArrayList.add(trip);
                        }
                    }
                }
                tripArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home_icon);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.trip_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.delete:
                Trip tripToDelete= filteredTripsArrayList.get(selectedPosition);
                Service.getTrips().remove(tripToDelete);
                filteredTripsArrayList.remove(tripToDelete);
                Toast.makeText(this, "Trip removed",
                        Toast.LENGTH_SHORT).show();
                tripArrayAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onContextMenuClosed(Menu menu){
        ( (TripsAdapter)listView.getAdapter()).notifyDataSetChanged();
    }


    @Override
    protected void onStop() {
        Gson gson = new Gson();
        String json = gson.toJson(Service.getTrips());
        new File(context.getFilesDir(), "jsonData.txt");
        String filename = "jsonData.txt";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
            Log.d("Wrote to file","yes");
        } catch (Exception e) {
            Log.d("Wrote to file","no");
            e.printStackTrace();
        }
        System.out.println("### Trips saved to memories ###");
        super.onStop();
    }
}