package info.androidhive.tabsswipe;

import com.example.touch.TouchImageView;

import info.androidhive.tabsswipe.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements OnClickListener {
Button btChange;
boolean isNMR=true;
boolean isIR=false;
TouchImageView img;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       img = (TouchImageView) rootView.findViewById(R.id.imgNMR);
        btChange=(Button)rootView.findViewById(R.id.btChange);
        btChange.setOnClickListener(this);
//        if(MainActivity.state){
//        	 img.setImageResource(R.drawable.blacknmr);
//        }
//        else{
//        	 img.setImageResource(R.drawable.nmrlabels);
//        }
       
        img.setMaxZoom(8.0f);
		return rootView;
	}
	@Override
	public void onClick(View v) {
		if(isNMR){
			img.setImageResource(R.drawable.nmrlabels);
			
		}
		
	}
}
