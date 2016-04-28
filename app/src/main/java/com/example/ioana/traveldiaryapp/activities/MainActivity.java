package com.example.ioana.traveldiaryapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    ArrayList<Trip> tripsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button btnTrips = (Button) findViewById(R.id.TripsBtn);
        btnTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TripsActivity.class);
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
                    tripsArrayList = new GsonBuilder().create().fromJson(jsonFromFile, listType);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Service.addTripList(tripsArrayList);
                    Service.loadedJsonData = true;
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
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("Trips loading failed", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Trips loading failed", "Can not read file: " + e.toString());
        }
        return ret;
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
        System.out.println("### Trips saved to memorie ###");
        super.onStop();
    }
}
