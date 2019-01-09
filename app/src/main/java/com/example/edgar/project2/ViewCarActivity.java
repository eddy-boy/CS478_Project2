package com.example.edgar.project2;

/**
 * @author Edgar Martinez-Ayala
 * ViewCarActivity class - Class that is called by the mainActivity and has an imageView
 *                         that displays a bigger,better quality picture from the one
 *                         that was clicked from the main activity. Also handles the event
 *                         from when the image is clicked to open that car manufacture
 *                         webpage.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ViewCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gets the intent used to start this activity
        final Intent carID = getIntent();

        // Makes a new ImageView
        ImageView carPic = new ImageView(getApplicationContext());

        // Puts picture in center of screen
        carPic.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // Get ID of the image to display and sets it as the image for this ImageView
        carPic.setImageResource(carID.getIntExtra("PicID",0));

        // OnClickListener that opens the web browser and displays the manufactures
        // website when the user clicks on the image
        carPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(carID.getStringExtra("URL")));
                startActivity(browserIntent);
            }
        });

        // Display the ImageView
        setContentView(carPic);
    }
}
