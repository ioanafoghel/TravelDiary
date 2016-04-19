package com.example.ioana.traveldiaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ioana.traveldiaryapp.model.Trip;

import java.util.List;

/**
 * Created by Ioana on 15/04/2016.
 */
public class TripAdapter extends ArrayAdapter<String>{
    Context c;
    String[] destinations;
    int[] images;
    LayoutInflater inflater;

    public TripAdapter(Context context, String[] destinations, int[] images) {
        super(context, R.layout.trip_item, destinations);

        this.c = context;
        this.destinations = destinations;
        this.images = images;
    }
    public  class ViewHolder
    {
        TextView destinationTxt;
        ImageView destinationImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.trip_item, null);
        }

        ViewHolder holder = new ViewHolder();
        holder.destinationTxt = (TextView) convertView.findViewById(R.id.destinationTxtId);
        holder.destinationImage = (ImageView) convertView.findViewById(R.id.destinationImage);


        holder.destinationTxt.setText(destinations[position]);
        holder.destinationImage.setImageResource(images[position]);


        return convertView;
    }
}
