package com.example.ioana.traveldiaryapp.model;

import java.util.Calendar;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Trip {

    @SerializedName("Destination")
    private String destination;
    @SerializedName("Address")
    private String address;
    @SerializedName("Date_of_visit")
    private String dateOfVisit;
    @SerializedName("Description")
    private String description;
    @SerializedName("Visit_again")
    private Boolean yesCbx;
    private Boolean noCbx;
    @SerializedName("ImageDestination")
    private String destinationImg;


    public Trip() {
    }

    public Trip(String destinationImg, String destination, String address, String dateOfVisit, String description, Boolean yesCbx, Boolean noCbx) {
        this.destinationImg = destinationImg;
        this.destination = destination;
        this.address = address;
        this.dateOfVisit = dateOfVisit;
        this.description = description;
        this.yesCbx = yesCbx;
        this.noCbx = noCbx;
    }

    public void update(Trip trip) {
        destinationImg = trip.destinationImg;
        destination = trip.destination;
        address = trip.address;
        dateOfVisit = trip.dateOfVisit;
        description = trip.description;
        yesCbx = trip.yesCbx;
        noCbx = trip.noCbx;
    }

    public Trip copy() {
        Trip trip = new Trip();
        trip.destinationImg = destinationImg;
        trip.destination = destination;
        trip.address = address;
        trip.dateOfVisit = dateOfVisit;
        trip.description = description;
        trip.yesCbx = yesCbx;
        trip.noCbx = noCbx;
        return trip;
    }

    public String getDestinationImg(){
        return  destinationImg;
    }
    public void setDestinationImg(String destinationImg){
        this.destinationImg = destinationImg;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getDateOfVisit(){
        return dateOfVisit;
    }
    public void setDateOfVisit(String dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public Boolean getYesCbx(){return yesCbx;}
    public void setYesCbx(Boolean yesCbx){this.yesCbx=yesCbx;}
    public Boolean getNoCbx(){return  noCbx;}
    public void setNoCbx(Boolean noCbx){this.noCbx=noCbx;}


    @Override
    public String toString() {
        return  destination + "\t " + description + "\n" + dateOfVisit +"\t";
    }
}
