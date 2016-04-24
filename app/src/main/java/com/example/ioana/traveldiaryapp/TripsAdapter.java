package com.example.ioana.traveldiaryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Ioana on 20/04/2016.
 */
public class TripsAdapter extends ArrayAdapter<Trip> {
    LayoutInflater inflater;
    Context c;

    public TripsAdapter(Context context, int resource, List<Trip> trips) {
        super(context, resource, trips);
        this.c = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.trip_item, null);
            viewHolder = new ViewHolder();
            final ImageView destinationImage = (ImageView) convertView.findViewById(R.id.destinationImage);
            final TextView destination = (TextView) convertView.findViewById(R.id.destinationLabelId);


            viewHolder.destinationImage = destinationImage;
            viewHolder.destination = destination;

            convertView.setTag(viewHolder);
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));

        final ViewHolder holder = (ViewHolder) convertView.getTag();
        Trip tripItem = getItem(position);
        holder.destination.setText(tripItem.getDestination());
        imageLoader.displayImage("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png", holder.destinationImage);



        return convertView;
    }

    static class ViewHolder {
        TextView destination;
        ImageView destinationImage;
    }
}