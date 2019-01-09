package com.example.edgar.project2;

/**
 * @author Edgar Martinez-Ayala
 * ImageAdaptor class - Class that adds the car picture and name into the gridView
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    // Getter that are used by the ImageAdaptor
    public int getCount() {
        return mThumbIds.length;
    }

    // Getter that are used by the ImageAdaptor
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    // Getter that are used by the ImageAdaptor
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridViewAndroid = new View(mContext);
            gridViewAndroid = inflater.inflate(R.layout.gridview_layout, null);
            ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.gridview_image);
            TextView textViewAndroid = (TextView) gridViewAndroid.findViewById(R.id.gridview_text);

            // Sets the particular car image into the imageView
            imageViewAndroid.setImageResource(mThumbIds[position]);

            // Sets the particular car name into the textView
            textViewAndroid.setText(carNames[position]);
        }
        else {
            gridViewAndroid = (View) convertView;
        }

        return gridViewAndroid;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.bmw, R.drawable.dodge,
            R.drawable.jeep, R.drawable.mustang,
            R.drawable.subaru, R.drawable.tesla,
    };

    // Array that holds the car names
    private String[] carNames = {
            "BMW M5", "Dodge Durango",
            "Jeep Grand Cherokee", "Ford Mustang",
            "Subaru Crosstrek", "Tesla Model X"
    };
}