package com.asergian.rssconference;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity {
	GoogleMap mMap;

	public String[] location_names = {
			"Social Sciences and Humanities (Lower Courtyard)",
			"Bainer Lawn",
		    "Roessler 66",
		    "Bainer Hall",
		    "Grass South of Bainer"
		};
	
	public String[] location_snippets = {
			"Meetup",
		    "Welcome, Food, and More!",
		    "Professor Talks",
		    "Workshops",
		    "Workshops"
		};	
	
	public Double[] location_latitude = {
			38.54265,
			38.53743,
			38.53710,
			38.53737,
			38.53693
	};
	 
	public Double[] location_longitude = {
			238.25185,
			238.24813,
			238.2482,
			238.24765,
			238.24738
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mMap = ((SupportMapFragment)(getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
	    	
	    mMap.setMyLocationEnabled(true);

	    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miller, 13));

	    for (int event = 0; event < 5; event++) {
		    LatLng location = new LatLng(location_latitude[event], location_longitude[event]);
		    mMap.addMarker(new MarkerOptions()
		    	.title(location_names[event])
		        .snippet(location_snippets[event])
		        .position(location));
	    } // for: create markers for events
	} // onCreate()
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	} // onCreateOptionsMenu()
		 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;
		
		else if(id == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	} // onOptionsItemSelected()
} // class Map
