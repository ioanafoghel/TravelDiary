package com.example.ioana.traveldiaryapp.service;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.CheckBox;

import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Service {

    // name for a file which stores json data
    public static final String JSON_FILE_NAME = "jsonData.txt";

    public static boolean loadedJsonData;

    public static ArrayList<Trip> getTrips()
    {
        return Storage.getInstance().getTrips();
    }

    public static Trip createTrip(String destinationImg, String destination, String address, String dateOfVisit, String description, Boolean yesCbx, Boolean noCbx)
    {
        Trip trip = new Trip(destinationImg, destination, address, dateOfVisit, description, yesCbx, noCbx);
        Storage.getInstance().addTrip(trip);
        return trip;
    }

    public static void addTripList(ArrayList<Trip> trips){
       Storage.getInstance().addTripList(trips);
    }

    public static Trip getTripByIndex(int index){
        return Storage.getInstance().getTripByIndex(index);
    }

}
