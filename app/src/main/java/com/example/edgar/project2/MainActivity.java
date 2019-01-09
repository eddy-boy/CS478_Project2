package com.example.edgar.project2;

/**
 * @author Edgar Martinez-Ayala
 * MainActivity class - Class that calls the viewCar activity if the image is pressed
 *                      and also the listviewActivity if that option is selected from
 *                      within the context menu. Contextmenu is also made in this class
 *                      and handels all options that the user selects.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    ImageAdapter carImages = new ImageAdapter(this);

    // String array that holds links to that particular cars manufacture
    private String[] carManufactureLinks = {
            "https://www.bmwusa.com/vehicles/m-models/m5.html",
            "https://www.dodge.com/durango.html",
            "https://www.jeep.com/grand-cherokee.html",
            "https://www.ford.com/cars/mustang/",
            "https://www.subaru.com/vehicles/crosstrek/index.html",
            "https://www.tesla.com/modelx"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(carImages);

        registerForContextMenu(gridview);   // allows for app to recognize a long click

        // Starts carView activity that opens the image clicked onto a
        // new activity that displays a bigger, better image of that car
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent carIntent = new Intent(MainActivity.this, ViewCarActivity.class);
                carIntent.putExtra("PicID", (int) carImages.getItem(position));
                carIntent.putExtra("URL", (String) carManufactureLinks[position]);
                startActivity(carIntent);
            }
        });
    }

    // Makes a context menu with three items/options that the user can choose
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "View Picture");
        menu.add(0, v.getId(), 0, "Car Manufacturer");
        menu.add(0, v.getId(), 0, "Car Dealerships");
    }

    // Handel's when the user long clicks and selects an option in the context
    // menu and performs that action
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        // Starts activity with a bigger picture of car and holds a link
        if(item.getTitle() == "View Picture"){
            Intent carIntent = new Intent(MainActivity.this, ViewCarActivity.class);
            carIntent.putExtra("PicID", (int) carImages.getItem(info.position));
            carIntent.putExtra("URL", (String) carManufactureLinks[info.position]);
            startActivity(carIntent);
            return true;
        }
        // Opens the browser of user's choice and sends them to the manufactures website
        else if(item.getTitle() == "Car Manufacturer"){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(carManufactureLinks[info.position]));
            startActivity(browserIntent);
            return true;
        }
        // Opens an activity that list dealerships that sell that vehicle
        else if(item.getTitle() == "Car Dealerships"){
            Intent dealershipIntent = new Intent(MainActivity.this, ListViewActivity.class);
            dealershipIntent.putExtra("ID", info.position);
            startActivity(dealershipIntent);
            return true;
        }
        return false;
    }


}
