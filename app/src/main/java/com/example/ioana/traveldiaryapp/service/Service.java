package com.example.ioana.traveldiaryapp.service;

import android.util.Base64;
import android.widget.CheckBox;

import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.storage.Storage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

    public static void deleteTrip(Trip trip)
    {
        Storage.getInstance().removeTrip(trip);
    }

    public static void updateTrip(Trip trip, String destinationImg, String destination, String address, String dateOfVisit, String description, Boolean yesCbx, Boolean noCbx)
    {
        trip.setDestinationImg(destinationImg);
        trip.setDestination(destination);
        trip.setAddress(address);
        trip.setDateOfVisit(dateOfVisit);
        trip.setDescription(description);
        trip.setYesCbx(yesCbx);
        trip.setNoCbx(noCbx);
    }

    public static Trip getTripByIndex(int index){
        return Storage.getInstance().getTripByIndex(index);
    }
}
