package edu.sbu.cs.android.NMR.core;

import edu.sbu.cs.android.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class HomeFragment extends Fragment {

static WebView  w;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
		w=(WebView)rootView.findViewById(R.id.imgView);
		w.loadUrl("file:///android_asset/nmr.html");
		w.getSettings().setBuiltInZoomControls(true);
		w.getSettings().setDisplayZoomControls(false);
		return rootView;
	}
	
}



