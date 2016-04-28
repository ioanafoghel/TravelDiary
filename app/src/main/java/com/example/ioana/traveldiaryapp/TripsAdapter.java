package com.example.ioana.traveldiaryapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
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

import java.io.FileDescriptor;
import java.io.IOException;
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
        Bitmap bitmapFromString = null;
        try {
            Uri uri= Uri.parse(tripsArray.get(position).getDestinationImg());
            bitmapFromString = getBitmapFromUri(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewHolder.destinationImage.setImageBitmap(bitmapFromString);
        return convertView;
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                c.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        Bitmap image =BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        parcelFileDescriptor.close();
        return image;
    }

    static class ViewHolder {
        TextView destination;
        ImageView destinationImage;
    }



}