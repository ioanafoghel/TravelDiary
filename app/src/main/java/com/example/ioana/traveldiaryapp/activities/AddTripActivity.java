package com.example.ioana.traveldiaryapp.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ioana.traveldiaryapp.R;
import com.example.ioana.traveldiaryapp.service.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ioana on 15/04/2016.
 */
public class AddTripActivity extends Activity {

    private static final int DIALOG_ID = 0;
    private static int RESULT_LOAD_IMAGE = 1;

    ImageView imageLoadImage;
    TextView destination, address, description, visitAgain;
    RadioButton yesCbx, noCbx;
    EditText dateText;
    Button SaveBtn;

    int visit_year, visit_month, visit_day;
    Bitmap pickedPicture;
    Uri pickedPictureUri;
    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_activity);

        destination = (TextView) findViewById(R.id.destinationTxtId);
        address = (TextView) findViewById(R.id.addressTxtId);
        dateText = (EditText) findViewById(R.id.dateOfVisitTxtId);
        description = (TextView) findViewById(R.id.descriptionTxtId);
        visitAgain = (TextView) findViewById(R.id.visitAgainLabelId);
        yesCbx = (RadioButton) findViewById(R.id.yesCbx);
        noCbx = (RadioButton) findViewById(R.id.noCbx);
        SaveBtn = (Button) findViewById(R.id.SaveBtn);

        final Calendar calendar = Calendar.getInstance();
        visit_year = calendar.get(Calendar.YEAR);
        visit_month = calendar.get(Calendar.MONTH);
        visit_day = calendar.get(Calendar.DAY_OF_MONTH);

        imageLoadImage = (ImageView) findViewById(R.id.destinationImage);
        imageLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Service.createTrip(pickedPictureUri.toString(), destination.getText().toString(), address.getText().toString(), dateText.getText().toString(), description.getText().toString(), yesCbx.isChecked(), noCbx.isChecked());
                Gson gson = new Gson();

                String serializedOutput = gson.toJson(Service.getTrips());
                WriteJsonToFile(serializedOutput);
                Intent intent = new Intent(context, TripsActivity.class);
                startActivity(intent);
            }
        });

        dateText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_UP == event.getAction()) {
                    showDialog(DIALOG_ID);
                }
                return true;
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            pickedPictureUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(pickedPictureUri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            pickedPicture = null;
            try {
                pickedPicture = getBitmapFromUri(pickedPictureUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageLoadImage.setImageBitmap(pickedPicture);
        }
    }


    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 16;
        Bitmap image =BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        parcelFileDescriptor.close();
        return image;
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
            String dateOfVisit= visit_day + "-" + visit_month + "-" + visit_year;
            dateText.setText(dateOfVisit);
            Toast.makeText(AddTripActivity.this, "Date of visit: " + visit_year + "-" + visit_month + "-" + visit_day, Toast.LENGTH_LONG).show();
        }
    };

// Replace this with a map button and a simple EditText!!!!!

    public void process(View view) {
        if (view.getId() == R.id.addressTxtId) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:19.076,72.8777"));
            Intent chooser = Intent.createChooser(intent, "Launch Map");
            startActivity(chooser);
        }
    }

    private void WriteJsonToFile(String json) {
        new File(context.getFilesDir(), Service.JSON_FILE_NAME);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(Service.JSON_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
            Log.d("Wrote to file","yes");
        } catch (Exception e) {
            Log.d("Wrote to file","no");
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        Gson gson = new Gson();
        String json = gson.toJson(Service.getTrips());
        new File(context.getFilesDir(), Service.JSON_FILE_NAME);
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(Service.JSON_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
            Log.d("Wrote to file","yes");
        } catch (Exception e) {
            Log.d("Wrote to file","no");
            e.printStackTrace();
        }
        super.onStop();
    }
}
