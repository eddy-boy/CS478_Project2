package com.example.edgar.project2;

/**
 * @author Edgar Martinez-Ayala
 * ListViewActivity class - Class that is called by the mainActivity when the user wants to see
 *                          dealerships that sell that car. It displays the name and address
 *                          of the dealerships in a listView.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private String dealershipName [] = {
            "Patrick BMW", "BMW of Orland Park",
            "Elmhurst BMW", "Sherman Dodge Chrysler Jeep",
            "Larry Roesch Chrysler Jeep Dodge RAM",
            "Zeigler Chrysler Dodge Jeep RAM of Schaumburg",
            "Bettenhausen Chrysler Dodge Jeep RAM",
            "Napletons Arlington Heights Chrysler Dodge Jeep RAM",
            "Marino Chrysler Jeep Dodge", "Bob Rohrman Schaumburg Ford",
            "Arlington Heights Ford", "Zeigler Ford North Riverside",
            "Grand Subaru", "Berman Subaru of Chicago",
            "Evanston Subaru In Skokie", "Chicago-West Grand Avenue",
            "Chicago-Gold Coast", "Oak Brook-Oakbrook Center"
    };

    private String dealershipAddress [] = {
            "700 East Golf Road Schaumburg, IL 60173",
            "11030 W 159th St. ORLAND PARK, IL 60467",
            "466 West Lake St. Elmhurst, IL 60126",
            "7601 N. Skokie Blvd. Skokie, IL 60077",
            "200 W GRAND AVE. ELMHURST, IL 60126",
            "208 W. Golf Road Schaumburg, IL 60195",
            "8355 WEST 159TH STREET Tinley Park, IL 60477",
            "1155 W. Dundee Road Arlington Heights, IL 60004",
            "5133 W Irving Park Rd Chicago, IL 60641",
            "815 E. Golf Road Schaumburg, IL 60173",
            "801 W Dundee Rd. Arlington Heights, IL 60004",
            "2100 HARLEM AVE RIVERSIDE, IL 60546",
            "125 West Grand Avenue Bensenville, IL 60106",
            "4330 W. Irving Park Chicago, IL 60641",
            "3340 Oakton St. Skokie, IL 60076",
            "1053 W. Grand Avenue Chicago, IL 60642",
            "901 North Rush Street Chicago, IL 60611",
            "58 Oakbrook Center Oak Brook, IL 60523"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gets the intent used to start this activity
        final Intent ID = getIntent();

        // Gets the image position that was clicked
        int pos = ID.getIntExtra("ID", 0);

        // Declare a new listView for this activity
        ListView listView = new ListView(this);

        // Hashmap that will hold the name and address of it of dealer
        HashMap<String, String> nameAddresses = new HashMap<>();

        // Inserts the name and address of dealer into hashmap
        insertInfo(nameAddresses, pos);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.activity_list_view,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.listview_text1, R.id.listview_text2});

        // Inserts name and address of dealership into listView by
        // grabbing the info from the nameAddresses hashmap
        Iterator it = nameAddresses.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);   // add HashMap into listItem
        }

        listView.setAdapter(adapter);

        // Display the listView
        setContentView(listView);
    }

    // Inserts the names and address of the dealerships for the specific
    // car image that was clicked by the user.
    private HashMap<String, String> insertInfo(HashMap<String, String> nameAddresses, int pos){
        int val =  pos * 3;   // we multiply the image value by 3

        // adds name and address from name and address array
        for(int i = val; i < val + 3; i++){
            nameAddresses.put(dealershipName[i], dealershipAddress[i]);
        }

        return nameAddresses;
    }
}
