package com.example.ioana.traveldiaryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import java.util.ArrayList;

/**
 * Created by Ioana on 20/04/2016.
 */
public class TripsAdapter extends ArrayAdapter<Trip> {
    LayoutInflater inflater;
    Context c;
    ArrayList<Trip> tripsArray;

    public TripsAdapter(Context context, int resource, ArrayList<Trip> trips) {
        super(context, resource, trips);
        this.c = context;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tripsArray= trips;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.trip_item, null);
            viewHolder = new ViewHolder();
            viewHolder.destinationImage = (ImageView) convertView.findViewById(R.id.destinationImage);
            viewHolder.destination = (TextView) convertView.findViewById(R.id.destinationLabelId);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
            viewHolder.destination.setText(tripsArray.get(position).getDestination());
            Bitmap bitmapFromString = Service.getBitmapFromString(tripsArray.get(position).getDestinationImg());
            viewHolder.destinationImage.setImageBitmap(bitmapFromString);
        return convertView;
    }

    static class ViewHolder {
        TextView destination;
        ImageView destinationImage;
    }

}