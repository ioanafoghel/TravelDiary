package com.example.ioana.traveldiaryapp.model;

import android.widget.CheckBox;

import java.util.Date;

/**
 * Created by Ioana on 15/04/2016.
 */
public class Trip {

    private String destination;
    private String address;
    private Date dateOfVisit;
    private String description;
    private CheckBox yesCbx;
    private CheckBox noCbx;
    private int destinationImg;

    public Trip() {
    }

    public Trip(int destinationImg, String destination, String address, Date dateOfVisit, String description, CheckBox yesCbx, CheckBox noCbx) {
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

    public int getDestinationImg(){
        return  destinationImg;
    }
    public void setDestinationImg(int destinationImg){
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
    public Date getDateOfVisit(){
        return dateOfVisit;
    }
    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public CheckBox getYesCbx(){return yesCbx;}
    public void setYesCbx(CheckBox yesCbx){this.yesCbx=yesCbx;}
    public CheckBox getNoCbx(){return  noCbx;}
    public void setNoCbx(CheckBox noCbx){this.noCbx=noCbx;}


    @Override
    public String toString() {
        return  destination + "\t " + description + "\n" + dateOfVisit +"\t";
    }
}
