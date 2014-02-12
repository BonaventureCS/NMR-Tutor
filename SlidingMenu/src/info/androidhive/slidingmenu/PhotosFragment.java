package info.androidhive.slidingmenu;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhotosFragment extends Fragment{
	

	@Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	
       View rootView = inflater.inflate(R.layout.fragment_photos, container, false);
       //TouchImageView img = (TouchImageView) rootView.findViewById(R.id.img2);
       //img.setMaxZoom(20.0f);
       return rootView;
   }
}
