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

public class ContactsListViewLoader extends ListFragment {
	public String[] names = {
	    "Aneil Dhillon",
	    "Ethan Palm",
	    "Katie Green",
	    "Alex Blee"
	};
	
	public String[] titles = {
	    "General Questions?",
	    "General Questions?",
	    "General Questions?",
	    "Hosting Questions?"  
	};
	
	public String[] numbers = {
			"916-642-6929",
			"408-427-1413",
			"310-427-4641",
			"530-591-3975"
		};	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < names.length; ++i) {
	      list.add(names[i]);
	    }
	    
	    MyArrayAdapter adapter = new MyArrayAdapter(inflater.getContext(), names, titles);
		setListAdapter(adapter);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	} // onCreateView()
/*	
	@Override
	public void onStart() {
		ListView lv = getListView();
	    ColorDrawable divider = new ColorDrawable(this.getResources().getColor(R.color.white));
	    lv.setDivider(divider);
	    
	    super.onStart();
	} // onStart()
*/	
    @Override
	public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
/*
        Log.d("Pos", Integer.toString(v.getId()));
        
        if (Integer.toString((int) id) == "number")
        	Log.d("Pos", Integer.toString(position));*/
        
        Intent intent = new Intent(getActivity(), Contact.class);
        intent.putExtra("position", position);
        intent.putExtra("names", names);
        intent.putExtra("titles", titles);
        intent.putExtra("numbers", numbers);
        startActivity(intent);
    } // onListItemClick()
	
	public class MyArrayAdapter extends ArrayAdapter<String> {
		private final Context context;
		private final String[] names;
		private final String[] numbers;
	
		public MyArrayAdapter(Context context, String[] names, String[] numbers) {
		    super(context, R.layout.list_item, names);
		    this.context = context;
		    this.names = names;
		    this.numbers = numbers;
		}
	
		@Override
	  	public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) context
			    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.list_item, parent, false);
			TextView textView = (TextView) rowView.findViewById(R.id.name);
			TextView textView2 = (TextView) rowView.findViewById(R.id.title);
			
			textView.setText(names[position]);
			textView2.setText(numbers[position]);
			
			return rowView;
		}
	} // class MyArrayAdapter
}
