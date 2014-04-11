package edu.sbu.cs.android.NMR.core;



import com.example.touch.TouchImageView;

import edu.sbu.cs.android.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.TextView;

public class HomeFragment extends Fragment {
public static TouchImageView img;
TextView tv;
String jsondata, test;
int qLength;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       img = (TouchImageView) rootView.findViewById(R.id.imgNMR);
       img.setMaxZoom(8.0f);
       
		return rootView;
	}
}
