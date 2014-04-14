package edu.sbu.cs.android.drawing;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.Region;
import android.view.MotionEvent;
import android.widget.Toast;

public class DrawingView extends View {
	private Path path ;
	private Paint drawPaint, canvasPaint, paint;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	List<Point> points = new ArrayList<Point>();
	Point tPoint, startPos;  
	int Xs, Ys, mode;

	public DrawingView(Context context, AttributeSet attrs){
	    super(context, attrs);
	    setupDrawing();
	}
	private void setupDrawing(){
		path = new Path();
		drawPaint = new Paint();
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(4.0f);
		drawPaint.setStyle(Paint.Style.STROKE);
		canvasPaint = new Paint(Paint.DITHER_FLAG);  
		tPoint=new Point();
		startPos=new Point(400,600);
		path.moveTo(startPos.x, startPos.y);
		mode=0;
		paint = new Paint(); 
		}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		
		paint.setColor(Color.GRAY); 
		paint.setTextSize(64); 
		canvas.drawText("X: "+Xs+" Y: "+Ys, 600, 50, paint);
		//path.moveTo(Xs, Ys);
		
		canvas.drawPath(path, drawPaint);
		canvas.drawCircle(startPos.x, startPos.y, 8, paint);
		canvas.drawText("start", 410f, 60f, paint);
		//canvas.draw

		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		 final int action = event.getAction();
	        switch (action) {
	        case MotionEvent.ACTION_DOWN: {
	        	Xs=(int) event.getX();
	        	Ys=(int) event.getY();
	        	moveToSelectedPoint(Xs,Ys);
	        	//Toast.makeText(this.getContext(), "onDown Point"+Xs+": "+Ys+"added",Toast.LENGTH_SHORT).show();
	            break;
	        }

	        case MotionEvent.ACTION_MOVE: {
	        	
	        	points.add(new Point(Xs,Ys));
	        	
	        	//invalidate();
	        	//Toast.makeText(this.getContext(), "Action Move",Toast.LENGTH_SHORT).show();

	            break;
	        }
	        case MotionEvent.ACTION_UP: {
	        	//Toast.makeText(this.getContext(), "Action Up",Toast.LENGTH_SHORT).show();
	        	switch(getMode()){
	        	case 0:
	        		singleBond();
	        		break;
	        	case 1:
	        		
	        		break;
	        	}
	            break;
	        }
	      }
		 return true;
	}
	private void moveToSelectedPoint(int x, int y){
		Path rgnPath=new Path();
		rgnPath.addCircle(Xs, Ys, 60f, Direction.CW);
		 Region rgn = new Region();
//       rgn.setBounds();
//		 Region clip = new Region();
		 rgn.setPath(rgnPath,rgn);
		 if(!points.isEmpty()){
			 for(int i=0;i<points.size();i++){
					if(rgn.contains(points.get(i).x, points.get(i).y))
					{
						Toast.makeText(this.getContext(), "contians the point",Toast.LENGTH_SHORT).show();
						path.moveTo(points.get(i).x, points.get(i).y);
					}
				}
		 }
	}
	private void singleBond(){
		//path.addCircle(Xs, Ys, 40f, Direction.CW);
		
    	path.lineTo(Xs, Ys);
    	invalidate();
	}
	public void unDo(){
		path.reset();
		path.moveTo(startPos.x,startPos.y);
		if(!points.isEmpty()){
			for(int i=0;i<points.size();i++){
				//path.moveTo(points.get(i-1).x, points.get(i-1).y);
				//path.lineTo(points.get(i-1).x, points.get(i-1).y);
				//Toast.makeText(this.getContext(), "last point ->"+points.get(points.size()-1).x+": "+points.get(points.size()-1).y,Toast.LENGTH_SHORT).show();
			}
		}
		
		invalidate();
	}

	public int getMode(){
		return mode;
	}
	public void setMode(int m) {
		mode=m;
		
	}
}
