package com.asergian.rssconference;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EventsListViewLoader extends ListFragment {
	public String[] events = {
		"Meet and Greet",
	    "Breakfast",
	    "Welcome",
	    "Professor Bernard Molyneux",
	    "Ice Breakers", 
	    "Professor Thomas Famula",
	    "Lunch",
	    "Workshops",
	    "Breakfast and Wrap-up"
	};
	
	public String[] times = {
		"5/2, 6-11pm",
	    "5/3, 9-9:45am",
	    "5/3, 9-9:45am",
	    "5/3, 10-11am",
	    "5/3, 11am-12pm",  
	    "5/3, 12-1pm",
	    "5/3, 1-2pm",
	    "5/3, 2-4:30pm",
	    "5/4, 9am"
	};
	
	public String[] locations = {
			"Social Sciences and Humanities, Rooms 70, 80, and 90",
		    "Bainer Lawn",
		    "Bainer Lawn",
		    "Roessler 66",
		    "Bainer Lawn",  
		    "Roessler 66",
		    "Bainer Lawn",
		    "Bainer 1060/1130 & Grass South of Bainer",
		    "Bainer Lawn"
		};
	
	public String[] details = {
			"Come meet up with fellow scholars for an evening of fun and games!",
			"Complimentary Breakfast hosted by Davis RSS.",
			"Welcoming remarks by UC Davis RSS Officers.",
			"Dr. Molyneux is an Associate Professor of Philosophy. His work focuses on human intuition and the problem of consciousness.",
			"Get to know your fellow Regents Scholars!",  
			"Dr. Famula is a Professor of Animal Science. His research focuses on the statistical elements surrounding genetics and animal breeding.",
			"Complimentary lunch hosted by Davis RSS.",
			"Choose between 3 choices for each time slot. Click on Workshops tab of the Menu Drawer (click on top left icon twice) for extended descriptions.",
			"Come out for food, pictures, and some closing remarks before heading home."
		};		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < events.length; ++i) {
	      list.add(events[i]);
	    }
	    
	    MyArrayAdapter adapter = new MyArrayAdapter(inflater.getContext(), events, times);
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	} // onCreateView()

    @Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        Intent intent = new Intent(getActivity(), Event.class);
        intent.putExtra("position", position);
        intent.putExtra("events", events);
        intent.putExtra("times", times);
        intent.putExtra("locations", locations);
        intent.putExtra("details", details);
        startActivity(intent);
    } // onListItemClick()
	
	public class MyArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final String[] events;
		private final String[] times;
	
		public MyArrayAdapter(Context context, String[] events, String[] times) {
		    super(context, R.layout.list_item, events);
		    this.context = context;
		    this.events = events;
		    this.times = times;
		}
	
		@Override
	  	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
			    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.list_item, parent, false);
			
			TextView textView = (TextView) rowView.findViewById(R.id.name);
			TextView textView2 = (TextView) rowView.findViewById(R.id.title);
			
			textView.setText(events[position]);
			textView2.setText(times[position]);
			
			return rowView;
		}
	} // class MyArrayAdapter
}
