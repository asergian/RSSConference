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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Contact extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		
/*        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);*/
        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
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
	}

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
        	String[] names = bundle.getStringArray("names");
        	//String[] titles = bundle.getStringArray("titles");
        	int pos = bundle.getInt("position");
        	
			View rootView = inflater.inflate(R.layout.fragment_contact,container, false);

			/* Contact Name */
			TextView contact_name = (TextView) rootView.findViewById(R.id.contact_name);
			contact_name.setText(names[pos]);
			
			/* Contact Image */
			ImageView contact_pic = (ImageView) rootView.findViewById(R.id.contact_picture);
			
			if (pos == 0)
				contact_pic.setImageResource(R.drawable.obama_brush);
			else if (pos == 1)
				contact_pic.setImageResource(R.drawable.seal);
			else if (pos == 2)
				contact_pic.setImageResource(R.drawable.llama);
			else
				contact_pic.setImageResource(R.drawable.wolf);
			
			/* Call/Text code */
			TextView call = (TextView) rootView.findViewById(R.id.contact_call);
			TextView message = (TextView) rootView.findViewById(R.id.contact_text);
			
			call.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	Bundle bundle = getActivity().getIntent().getExtras();
		        	String[] numbers = bundle.getStringArray("numbers");
		        	int pos = bundle.getInt("position");
		        	
		        	String uri = "tel:" + numbers[pos].trim();
		        	Intent intent = new Intent(Intent.ACTION_DIAL);
		        	intent.setData(Uri.parse(uri));
		        	startActivity(intent);
		        }
		    });
			
			message.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	Bundle bundle = getActivity().getIntent().getExtras();
		        	String[] numbers = bundle.getStringArray("numbers");
		        	int pos = bundle.getInt("position");
		        	String body;
		        	
		        	if (pos == 3)
		        		body = "Hosting Questions?";
		        	else
		        		body = "General Questions?";
		        	
		        	String uri = "sms:" + numbers[pos].trim();
		        	Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
		        	sendIntent.setData(Uri.parse(uri));
		        	sendIntent.putExtra("sms_body", body);
		        	startActivity(sendIntent);
		        }
		    });	
			
			return rootView;
		}
	}

}
