package edu.sbu.cs.android.NMR.core;



import edu.sbu.cs.android.drawing.DrawingView;
import info.androidhive.tabsswipe.R;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DrawFragment extends Fragment implements OnClickListener {
	private ImageView hexdraw;
	private DrawingView drawView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_draw, container, false);
		drawView = (DrawingView)rootView.findViewById(R.id.drawing);
		hexdraw=(ImageView)rootView.findViewById(R.id.imgB1);
		hexdraw.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.imgB1 :
			hexdraw.setBackgroundColor(Color.GREEN);
			drawView.drawBenezene();
		break;
		}
	}

}
