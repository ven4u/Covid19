package com.android.covid19;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.covid19.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getName();
    public ArrayList<String> dataList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        GetObjects();
        readObject();

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void GetObjects(){
      //myArray = new String[]{};
        //final ListView listView = (ListView) findViewById(R.id.listviewA);
        Log.d(TAG,"GetObject executed");
        // Configure Query
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Coronaviruscases_Covid19Case");
        Log.d(TAG,"GetObject executed query ;-" + query.getClassName());


        // Sorts the results in ascending order by the itemName field


        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, final ParseException e) {
                Log.d(TAG, "GetObject executed query done :-");
                if (e == null){
                    // Adding objects into the Array
                    Log.d(TAG, "GetObject executed query done if:-");
                    for(int i= 0 ; i < objects.size(); i++){
                        String element = objects.get(i).getString("countryName");
                        dataList.add(element.toString());
                        Log.d(TAG, "countryName : - " + element);
                    }
                } else {
                    Log.d(TAG, "GetObject executed query done else :-");
                }

            }
        });
    }

    public void readObject() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Coronaviruscases_Covid19Case");
        Log.d(TAG, "readObject");
        // The query will search for a ParseObject, given its objectId.
        // When the query finishes running, it will invoke the GetCallback
        // with either the object, or the exception thrown
        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> result, ParseException e) {
                if (e == null) {
                    Log.d(TAG, "result : -"  + result.size());
                } else {
                    // something went wrong
                }
            }
        });
    }
}