package com.example.ioana.traveldiaryapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.TripsAdapter;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Ioana on 15/04/2016.
 */
public class TripsActivity extends AppCompatActivity {
    final Context context = this;
    StringBuffer postList;
    ArrayList<Trip> tripstArrayList = new ArrayList<Trip>();
    ListView listView;
    TripsAdapter tripArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_activity);
        listView = (ListView) findViewById(R.id.list);
        tripArrayAdapter = new TripsAdapter(this, 0, Service.getTrips());
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

        if (!Service.loadedJsonData) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    Type listType = new TypeToken<ArrayList<Trip>>() {
                    }.getType();
                    String jsonFromFile = readFromFile();
                    if (jsonFromFile == null) {
                        jsonFromFile = loadJSONFromAsset();
                    }
                    tripstArrayList = new GsonBuilder().create().fromJson(jsonFromFile, listType);
                    postList = new StringBuffer();
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Service.addTripList(tripstArrayList);
                    tripArrayAdapter.notifyDataSetChanged();
                    Service.loadedJsonData = true;
                    //listView.notify();
                    //TripsAdapter tripArrayAdapter = new TripsAdapter(getBaseContext(), 0, Service.getTrips());
                    //listView.setAdapter(tripArrayAdapter);
                }
            }.execute();
        }
    }

    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("jsonData.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public String loadJSONFromAsset() {

        String json = "";
        try {
            InputStream stream = getAssets().open("tripsJSON");

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}