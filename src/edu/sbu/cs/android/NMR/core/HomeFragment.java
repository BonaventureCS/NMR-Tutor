package edu.sbu.cs.android.NMR.core;



import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.touch.TouchImageView;
import edu.sbu.cs.android.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class HomeFragment extends Fragment {
//public static TouchImageView img;
WebView  w;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
     //  img = (TouchImageView) rootView.findViewById(R.id.imgNMR);
       //img.setMaxZoom(8.0f);
		w=(WebView)rootView.findViewById(R.id.imgView);
		w.loadUrl("file:///android_asset/index.html");
	  //w.getSettings().setBuiltInZoomControls(true);
		w.getSettings().setDisplayZoomControls(true);
		return rootView;
	}
}
