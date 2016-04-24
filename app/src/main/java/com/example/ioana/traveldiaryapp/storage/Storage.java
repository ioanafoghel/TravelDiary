package com.example.ioana.traveldiaryapp.storage;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.widget.CheckBox;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.activities.TripsActivity;
import com.example.ioana.traveldiaryapp.model.Trip;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Storage {
    private ArrayList<Trip> trips = new ArrayList<>();
    private static Storage uniqueInstance;

    private Storage() {
    }

    public static Storage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Storage();
           // uniqueInstance.createSomeObjects();
        }
        return uniqueInstance;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void addTripList(ArrayList<Trip> t){
        if (t != null){
        for (Trip trip: t){
            trips.add(trip);
        }}
    }

    public void removeTrip(Trip trip) {
        trips.remove(trip);
    }

    public void updateTrip(Trip trip, String destinationImg, String destination, String address, String dateOfVisit, String description, Boolean yesCbx, Boolean noCbx) {
        trip.setDestinationImg(destinationImg);
        trip.setDestination(destination);
        trip.setAddress(address);
        trip.setDateOfVisit(dateOfVisit);
        trip.setDescription(description);
        trip.setYesCbx(yesCbx);
        trip.setNoCbx(noCbx);
    }

    public Trip createTrip(String destinationImg, String destination, String address, String dateOfVisit, String description, Boolean yesCbx, Boolean noCbx) {
        Trip trip = new Trip(destinationImg, destination, address, dateOfVisit, description, yesCbx, noCbx);
        uniqueInstance.addTrip(trip);
        return trip;
    }

   /* public void createSomeObjects() {

        Calendar date1 = GregorianCalendar.getInstance();

        date1.set(Calendar.DAY_OF_MONTH, 18);
        date1.set(Calendar.MONTH, 4);
        date1.set(Calendar.YEAR, 2016);

        Calendar date2 = GregorianCalendar.getInstance();

        date2.set(Calendar.DAY_OF_MONTH, 10);
        date2.set(Calendar.MONTH, 7);
        date2.set(Calendar.YEAR, 2011);

        Calendar date3 = GregorianCalendar.getInstance();

        date3.set(Calendar.DAY_OF_MONTH, 28);
        date3.set(Calendar.MONTH, 9);
        date3.set(Calendar.YEAR, 2015);

        Calendar date4 = GregorianCalendar.getInstance();

        date4.set(Calendar.DAY_OF_MONTH, 5);
        date4.set(Calendar.MONTH, 4);
        date4.set(Calendar.YEAR, 2013);

        Calendar date5 = GregorianCalendar.getInstance();

        date5.set(Calendar.DAY_OF_MONTH, 3);
        date5.set(Calendar.MONTH, 5);
        date5.set(Calendar.YEAR, 2010);

        Trip t1 = createTrip("", "Budapest", "Kossuth Lajos ter 1-3., Budapest 1055, Hungary", "13/04/2016", "A breath-taking building, one can stare at it forever and keep seeing something new. It is as amazing on the inside as it is on the outside.",true, false);
        Trip t2 = createTrip("", "London", "Elizabeth Tower - Houses of Parliament | Westminster, London SW1A 0AA, England", "13/04/2016", "Beautifully preserved building,full of history. I was looking forward to seeing it and wasn't disappointed. Next time we will reserve more time for it!", true, false);
        Trip t3 = createTrip("", "Moscow","Krasnaya Sq., 2, Moscow 109012, Russia", "13/04/2016","The St. Basil's Cathedral in Red Square Moscow has a very unusual architecture which is quite unlike anything that I have seen before. The building is straight out of a fairly tale and should be one of the top ranking in your list of things to in Moscow.", true, false);
        Trip t4 = createTrip("", "Paris", "5 avenue Anatole France, 75007 Paris, France", "13/04/2016", "I was on an exchange to France, and my host family took me to Paris. I was really excited to see the Eiffel Tower. However it was pretty over rated. It is super busy and overcrowded. It is for sure a place you have to visit when in Paris, but it didn't live up to its reputation.",
                false, true);
        Trip t5 = createTrip("", "Rome", "Piazza di Trevi, 00187 Rome, Italy", "13/04/2016", "Unmissable when you're in Rome - it's busy 24/7 but I'd recommend seeing it during both the day and night if you can. Throw a coin in the Trevi and you'll always come back. Watch out for the annoying men interrupting your photos to try to sell you a selfie stick! Great gelato places nearby too.", true, false);

    }*/

        public Trip getTripByIndex(int index){
            return trips.get(index);
        }
}
