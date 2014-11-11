package com.asergian.rssconference;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    /**
     * Create a new instance of ContentFragment, initialized to
     * show the text at 'index'.
     */
    public ContentFragment newInstance(int index) {
    	ContentFragment f = new ContentFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }		
    
    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = null;
		
		if (getShownIndex() == 0) {
			rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
			home(rootView);
		} // if: home page

		else if(getShownIndex() == 1) {
			rootView = inflater.inflate(R.layout.fragment_events_listview, container, false);
			events(rootView);
		}
		
		else if(getShownIndex() == 2) {
			rootView = inflater.inflate(R.layout.fragment_workshop_listview, container, false);
			workshops(rootView);
		} // if: contacts page
		
		else if(getShownIndex() == 3) {
			//rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
			rootView = inflater.inflate(R.layout.fragment_contact_listview, container, false);
			contacts(rootView);
		} // if: workshops page		
		
		return rootView;
	}
	
	void home(View rootView) {
		TextView updates_link = (TextView) rootView.findViewById(R.id.updates_link);
		updates_link.setClickable(true);
		updates_link.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://signup.regentsscholarsconference.org/updates'>Updates</a>";
		updates_link.setText(Html.fromHtml(text));
		
		TextView website_link = (TextView) rootView.findViewById(R.id.website_link);
		website_link.setClickable(true);
		website_link.setMovementMethod(LinkMovementMethod.getInstance());
		text = "<a href='http://signup.regentsscholarsconference.org/home'>website</a>";
		website_link.setText(Html.fromHtml(text));
	} // home(): code for home page
	
	void events(View rootView) {
		Fragment fragment = new EventsListViewLoader();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.events_fragment, fragment);
		ft.commit();
	} // events(): code for events page
	
	
	void workshops(View rootView) {
		Fragment fragment = new WorkshopsListViewLoader();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.workshops_fragment, fragment);
		ft.commit();
	} // events(): code for events page	
	
	void contacts(View rootView) {
		Fragment fragment = new ContactsListViewLoader();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.contacts_fragment, fragment);
		ft.commit();
	} // contacts(): code for contacts page
}