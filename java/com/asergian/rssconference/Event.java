package com.asergian.rssconference;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Event extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.event_container, new PlaceholderFragment()).commit();
		}	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		} 
		else if(id == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	} // onOptionsItemSelected()
	
	public void openMap(View view) {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
	} // openMap()
	
	public void openDirections(View view) {
		Intent intent = null;
		
		Bundle bundle = getIntent().getExtras();
    	String[] locations = bundle.getStringArray("locations");
    	int pos = bundle.getInt("position");
    	
    	if (locations[pos].equals("Social Sciences and Humanities, Rooms 70, 80, and 90"))
            intent = new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=Social+Sciences+and+Humanities&mode=driving"));
    	else if (locations[pos].equals("Roessler 66"))
            intent = new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=Roessler+Hall&mode=driving"));
    	else // locations[pos] == "Bainer Lawn" || locations[pos] == "Bainer 1060" || locations[pos] == "Bainer 1130" || locations[pos] == "Grassy South of Bainer"
            intent = new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=Bainer+Hall&mode=driving"));    	
    	
        startActivity(intent);
	} // openMap()	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			Bundle bundle = getActivity().getIntent().getExtras();
        	String[] events = bundle.getStringArray("events");
        	String[] times = bundle.getStringArray("times");
        	String[] locations = bundle.getStringArray("locations");
        	String[] details = bundle.getStringArray("details");
        	int pos = bundle.getInt("position");
        	
			View rootView = inflater.inflate(R.layout.fragment_event,container, false);
			
			ImageView mapImage = (ImageView) rootView.findViewById(R.id.map_image);
			
			if (events[pos].equals("Meet and Greet"))
				mapImage.setImageResource(R.drawable.socsci);
			else
				mapImage.setImageResource(R.drawable.bainer);
			
			/* Event Name, Time, and Location */ 
			TextView event_name = (TextView) rootView.findViewById(R.id.event_name);
			event_name.setText(events[pos]);

			TextView event_time = (TextView) rootView.findViewById(R.id.event_time);
			event_time.setText(times[pos]);

			TextView event_location = (TextView) rootView.findViewById(R.id.event_location);
			event_location.setText(locations[pos]);
			
			TextView event_details = (TextView) rootView.findViewById(R.id.event_details);
			event_details.setText(details[pos]);
			
			return rootView;
		}
	}
}
