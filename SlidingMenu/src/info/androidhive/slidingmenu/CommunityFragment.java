package info.androidhive.slidingmenu;



import com.example.touch.TouchImageView;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;


public class CommunityFragment extends Fragment{
	public CommunityFragment(){}

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        //TouchImageView img = (TouchImageView) rootView.findViewById(R.id.img2);
        //img.setMaxZoom(20.0f);
        return rootView;
    }

}
