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
import java.util.Date;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Service {

    public static ArrayList<Trip> getTrips()
    {
        return Storage.getInstance().getTrips();
    }

    public static Trip createTrip(int destinationImg, String destination, String address, Date dateOfVisit, String description, CheckBox yesCbx, CheckBox noCbx)
    {
        Trip trip = new Trip(destinationImg, destination, address, dateOfVisit, description, yesCbx, noCbx);
        Storage.getInstance().addTrip(trip);
        return trip;
    }

    public static void deleteTrip(Trip trip)
    {
        Storage.getInstance().removeTrip(trip);
    }

    public static void updateTrip(Trip trip, int destinationImg, String destination, String address, Date dateOfVisit, String description, CheckBox yesCbx, CheckBox noCbx)
    {
        trip.setDestinationImg(destinationImg);
        trip.setDestination(destination);
        trip.setAddress(address);
        trip.setDateOfVisit(dateOfVisit);
        trip.setDescription(description);
        trip.setYesCbx(yesCbx);
        trip.setNoCbx(noCbx);
    }


    /** Read the object from Base64 string. */
    public static Object toObject(String base64){
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        Object object = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(data ));
            object = ois.readObject();
            ois.close();
            System.out.println("### Product lists loaded from memory ###");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    /** Write the object to a Base64 string. */
    public static String toString(Serializable object){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject(object);
            oos.close();
            System.out.println("##### Object saved: " + object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String( Base64.encode(baos.toByteArray(),Base64.DEFAULT));
    }
}
