package com.example.ioana.traveldiaryapp.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.model.Trip;
import com.example.ioana.traveldiaryapp.service.Service;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ioana on 17/04/2016.
 */
public class TravelNoteActivity extends AppCompatActivity {

    // editMode value enables(when true) or disables(false) editing of Trip object. Value is changed using MenuItems
    boolean editMode;

    int visit_year, visit_month, visit_day;

    private static final int DIALOG_ID = 0;
    private static int RESULT_LOAD_IMAGE = 1;

    MenuItem saveNoteTravel, editNoteTravel;
    ImageView destinationImage;
    TextView destination, address, dateOfVisit, description;
    RadioButton yesCbx, noCbx;
    int tripIndex;
    Trip trip;
    final Context context = this;

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_note_activity);
        destinationImage = (ImageView) findViewById(R.id.destinationImage);
        destination = (TextView) findViewById(R.id.destinationTxtId);
        address = (TextView) findViewById(R.id.addressTxtId);
        dateOfVisit = (TextView) findViewById(R.id.dateOfVisitTxtId);
        description = (TextView) findViewById(R.id.descriptionTxtId);
        yesCbx = (RadioButton) findViewById(R.id.yesCbx);
        noCbx = (RadioButton) findViewById(R.id.noCbx);
        if (savedInstanceState != null) {
            editMode = savedInstanceState.getBoolean("EDIT_MODE");
        }else{
            editMode = false;
        }
        setTripEditable(editMode);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tripIndex = extras.getInt("TripItemIndex");
            trip = Service.getTripByIndex(tripIndex);
            Bitmap image = Service.getBitmapFromString(trip.getDestinationImg());
            destinationImage.setImageBitmap(image);
            destination.setText(trip.getDestination());
            address.setText(trip.getAddress());
            dateOfVisit.setText(trip.getDateOfVisit());
            description.setText(trip.getDescription());
            yesCbx.setChecked(trip.getYesCbx());
            noCbx.setChecked(!trip.getYesCbx());
        }
        // Replace this with a Date.util
        final Calendar calendar = Calendar.getInstance();
        visit_year = calendar.get(Calendar.YEAR);
        visit_month = calendar.get(Calendar.MONTH);
        visit_day = calendar.get(Calendar.DAY_OF_MONTH);

        destinationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        dateOfVisit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if((MotionEvent.ACTION_UP == event.getAction())&& editMode) {
                    showDialog(DIALOG_ID);
                }
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        saveNoteTravel = menu.findItem(R.id.save);
        editNoteTravel = menu.findItem(R.id.edit);
        saveNoteTravel.setVisible(editMode);
        editNoteTravel.setVisible(!editMode);
        return true;
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, visit_year, visit_month, visit_day);
        return null;
    }


    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            visit_year = year;
            visit_month = monthOfYear + 1;
            visit_day = dayOfMonth;
            dateOfVisit.setText(visit_day + "-" + visit_month + "-" + visit_year);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit:
                editMode = true;
                invalidateOptionsMenu();
                setTripEditable(editMode);
                Toast.makeText(getApplicationContext(), "Editing enabled", Toast.LENGTH_LONG).show();
                break;
            case R.id.save:
                saveChangesMadeToTrip();
                editMode= false;
                invalidateOptionsMenu();
                setTripEditable(editMode);
                Toast.makeText(getApplicationContext(), "Saved, editing disabled", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    protected void onStop() {
        Gson gson = new Gson();
        String json = gson.toJson(Service.getTrips());
        new File(context.getFilesDir(), "jsonData.txt");
        String filename = "jsonData.txt";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
            Log.d("Wrote to file","yes");
        } catch (Exception e) {
            Log.d("Wrote to file","no");
            e.printStackTrace();
        }
        System.out.println("### Trips saved to memories ###");
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("EDIT_MODE", editMode);
        super.onSaveInstanceState(savedInstanceState);
    }

    // enables/disables editing of UI views
    private void setTripEditable(Boolean mode){
        if (mode){
            destination.setFocusableInTouchMode(true);
            address.setFocusableInTouchMode(true);
            dateOfVisit.setFocusableInTouchMode(true);
            description.setFocusableInTouchMode(true);
        }else{
            destination.setFocusable(mode);
            address.setFocusable(mode);
            dateOfVisit.setFocusable(mode);
            description.setFocusable(mode);
        }
        destinationImage.setEnabled(mode);
        yesCbx.setClickable(mode);
        noCbx.setClickable(mode);
    }

    private void saveChangesMadeToTrip(){
        Bitmap bitmap = ((BitmapDrawable)destinationImage.getDrawable()).getBitmap();
        String bitmapToString = Service.getStringFromBitmap(bitmap);
        trip.setDestinationImg(bitmapToString);
        trip.setDestination(destination.getText().toString());
        trip.setAddress(address.getText().toString());
        trip.setDateOfVisit(dateOfVisit.getText().toString());
        trip.setDescription(description.getText().toString());
        trip.setYesCbx(yesCbx.isChecked());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.destinationImage);

            Bitmap bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bmp);

        }
    }


    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

}