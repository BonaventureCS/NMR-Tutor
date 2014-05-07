package edu.sbu.cs.android.drawing;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.MotionEvent;
import android.widget.Toast;


public class DrawingView extends View {
	private Path path ,p;
	private Paint drawPaint, canvasPaint, paint;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	List<Point> points = new ArrayList<Point>();
	Point startPos;  
	int Xs, Ys, mode;
	Region r;
	boolean isContained=false;

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
		startPos=new Point(400,600);
		points.add(startPos);
		path.moveTo(startPos.x, startPos.y);
		mode=0;
		paint = new Paint(); 
		paint.setAntiAlias(true);
		paint.setStrokeWidth(4.0f);
		paint.setStyle(Paint.Style.STROKE);
		p=new Path();
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
		
		paint.setColor(Color.GREEN); 
		paint.setTextSize(64);

		canvas.drawPath(path, drawPaint);
		canvas.drawCircle(startPos.x, startPos.y, 8, drawPaint);
		if(isContained){
			//canvas.drawPath(p, paint);
		}
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		 final int action = event.getAction();
	        switch (action) {
	        case MotionEvent.ACTION_DOWN: {
	        	Xs=(int) event.getX();
	        	Ys=(int) event.getY();
	        	p = new Path();
	            p.moveTo(Xs-50, Ys-50);
	            p.lineTo(Xs-50, Ys-50);
	            p.lineTo(Xs+50, Ys-50);
	            p.lineTo(Xs+50, Ys+50);
	            p.lineTo(Xs+50, Ys+50);
	            p.lineTo(Xs-50, Ys+50);
	            p.close();
	    		RectF rectF = new RectF();
	    	    p.computeBounds(rectF, true);
	    	    r = new Region();
	    	    r.setPath(p, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
	    	    for(int i=0; i<points.size();i++){
	    	    	if(r.contains(points.get(i).x,points.get(i).y))
	    	    	{
	    	    		
	    	    		isContained=true;
	    	    		path.moveTo(points.get(i).x, points.get(i).y);
	    	    		invalidate();
	    	        	
	    	    	}else{
	    	    		isContained=false;
	    	    		invalidate();
	    	    	}

	    	    }
	            break;
	        }

	        case MotionEvent.ACTION_MOVE: {
	        	Xs=(int) event.getX();
	        	Ys=(int) event.getY();
	        	

	            break;
	        }
	        case MotionEvent.ACTION_UP: {
	        	isContained=false;
	        	points.add(new Point(Xs,Ys));
	        	invalidate();


	        	
	        	
	        	
	        	switch(getMode()){
	        	case 0:
	        		singleBond();
	        		break;
	        	case 1:
	        		break;
	        	case 2:
	        		Cycohexane();
	        	break;
	        	}
	            break;
	        }
	      }
		 return true;
	}
	private void singleBond(){
    	path.lineTo(Xs, Ys);
    	invalidate();
	}
	private void Cycohexane(){
	
		
	}
	public void unDo(){
		path.reset();
		path.moveTo(startPos.x,startPos.y);
		if(!points.isEmpty()){
			for(int i=0;i<points.size();i++){
				
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
