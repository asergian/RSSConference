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

public class WorkshopsListViewLoader extends ListFragment {
	public String[] name_snippets = {
			"Cultivating Your Club",
			"Back to Childhood",
		    "Smartphoneography",
		    "Go Abroad!",
		    "What's In Your (Digital) Bag?",
		    "A Lesson in Swing Dancing", 
		    "Cheese with Dr. Moshe Rosenberg",
		    "Combating the Stigma Dilemma",
		    "Learn Zumba!"
		};
	
	public String[] events = {
		"Cultivating Your Club",
		"Back to Childhood: An hour of Creation and Imagination",
	    "Intro to Smartphoneography",
	    "Go Abroad! (A Study/Volunteer Abroad Workshop)",
	    "What's In Your (Digital) Bag? (2.0) Apps, Websites, and Your Personal Workflow",
	    "A Lesson in Swing Dancing", 
	    "All about Cheese with Dr. Moshe Rosenberg",
	    "Combating the Stigma Dilemma",
	    "Learn Zumba!"
	};
	
	public String[] times = {
		"5/3, 2-2:45pm",
		"5/3, 2-2:45pm",
	    "5/3, 2-2:45pm",
	    "5/3, 2:50-3:45pm",
	    "5/3, 2:50-3:45pm",
	    "5/3, 2:50-3:45pm",  
	    "5/3, 3:00-4:30pm",
	    "5/3, 3:40-4:25pm",
	    "5/3, 3:40-4:25pm"
	};
	
	public String[] locations = {
			"Bainer 1060",
			"Bainer 1130",
		    "Grass South of Bainer",
		    "Bainer 1060",
		    "Bainer 1130",
		    "Grass South of Bainer",  
		    "Bainer 1060",
		    "Bainer 1130",
		    "Grass South of Bainer"
		};
	
	public String[] details = {
			"Hosted by UC Davis. Come learn how to improve your club with the vice president of Davis RSS. Bring your questions and learn how to communicate effectively, create a brand, and add value to your club events.",
			"Hosted by UC Berkeley. Make crafts and play music relating to our favorite old and new childhood movies!",			
			"Hosted by UCSD. No need for fancy DSLR's and tripods – a smartphone is all you need to jump into photography. We'll go over how to frame, light, edit and share your photos. Get your creative juices flowing with inspiration from Instagram and VSCO Cam. We'll conclude with a short photo walk on campus.",
			"Hosted by UCSD. Are you interested in traveling to another country while earning academic credit? Looking to impact the world through an eye-opening volunteer experience? Come learn about the UC Education Abroad Program, scholarships, international volunteer organizations, and even opportunities for graduate students. Make the most of your time abroad! Also, hear tips and tricks from UC returnees: If you have studied/volunteered abroad, come share your experience in a panel at the end.",
			"Hosted by UCLA. In the vast flurry of online and on-phone services, it's easy to get overwhelmed, confused, or at the very least distracted for hours. This workshop, revamped and reworked for 2014 aims to teach you about online services, applications, and workflows that can make you more efficient, more organized, and infinitely sexier.",  
			"Hosted by UC Davis. Learn the some basic swing dancing! Or, if you're already a pro, come out and strut your stuff!",
			"Hosted by UCLA. Want to learn about the science of cheese? Well, we have esteemed UC Davis Professor Dr. Moshe Rosenberg to teach us about the food science of cheese! There fine cheeses imported from various countries around the world for you to try for yourself! Because we are serving cheese, supplies are limited and only 25 individuals will be allowed to attend this workshop (this will be explained further during lunch). Due to the specialized nature of this workshop, it will last from 3:00 to 4:30, participants must be prepared to attend this and only one other workshop.",
			"Hosted by UC Berkeley. How to interact with the homeless, gay/transgender people, students in Greek life.",
			"Hosted by UC Davis. Zumba is a Latin-based dance fitness workout. No dance experience is necessary, so come out and have fun! This session is taught by a certified instructor; comfortable, loose clothing is recommended but not required."
		};		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < name_snippets.length; ++i) {
	      list.add(name_snippets[i]);
	    }
	    
	    MyArrayAdapter adapter = new MyArrayAdapter(inflater.getContext(), name_snippets, times);
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
