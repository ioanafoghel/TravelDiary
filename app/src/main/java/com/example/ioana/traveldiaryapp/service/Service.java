package com.example.ioana.traveldiaryapp.service;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
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

    // converts Bitmap picture to a string which can be saved to json
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    public static String getStringFromBitmap(Bitmap bitmapPicture) {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.JPEG,50, baos);
        byte [] b=baos.toByteArray();
        String temp="";
        try{
            System.gc();
            temp=Base64.encodeToString(b, Base64.DEFAULT);
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp;
    }

    // converts String to Bitmap
    public static Bitmap getBitmapFromString(String jsonString) {
        BitmapFactory.Options opt= new BitmapFactory.Options();
        opt.inTempStorage = new byte[16 * 1024];
        opt.inSampleSize = 16;
        byte[] encodeByte = Base64.decode(jsonString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length,opt);
        return bitmap;
    }




}
