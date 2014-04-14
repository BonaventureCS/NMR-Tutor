package edu.sbu.cs.android.NMR.core;



import edu.sbu.cs.android.drawing.DrawingView;
import edu.sbu.cs.android.R;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class DrawFragment extends Fragment implements OnClickListener {
private ImageView singleBond,doubleBond,tripleBond;
private DrawingView drawView;
  

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_draw, container, false);
		drawView = (DrawingView)rootView.findViewById(R.id.drawing);
		singleBond=(ImageView)rootView.findViewById(R.id.imgbSingeBond);
		singleBond.setOnClickListener(this);
		doubleBond=(ImageView)rootView.findViewById(R.id.imgbDoubleBond);
		doubleBond.setOnClickListener(this);
		setRetainInstance(true);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.imgbSingeBond :
			drawView.setMode(0);
			break;
		case R.id.imgbDoubleBond:
			drawView.setMode(1);
			drawView.unDo();
			break;
		
		}
	}
	// have a mode swithcer the returns a number 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		//Toast.makeText(getActivity(), "is destoryed",Toast.LENGTH_SHORT).show();
		
		super.onDestroyView();
	}

}
