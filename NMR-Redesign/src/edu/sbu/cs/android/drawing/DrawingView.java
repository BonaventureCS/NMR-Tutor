package edu.sbu.cs.android.drawing;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;

public class DrawingView extends View {
	private Path path;
	private Paint drawPaint, canvasPaint;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	List<Point> points = new ArrayList<Point>();
	Point tPoint;  // where the user touches 
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
		}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
	}
	@Override
	protected void onDraw(Canvas canvas) {
	//draw view
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(path, drawPaint);
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		// need to select a point
		 tPoint.x = (int) event.getX();
		 tPoint.y = (int) event.getY();
		 path.moveTo(tPoint.x, tPoint.y);
		 drawCanvas.drawPath(path, drawPaint);
		 //path.setLastPoint(dx, dy);
		 invalidate();
		 return true;
	}
	
	public void drawCarbonLine(){
		//draw one line from the touched line 
		//path.moveTo(tPoint.x, tPoint.y);
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawDoubleBond(){
		//path.moveTo(tPoint.x, tPoint.y);
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawTripleBond(){
		//path.moveTo(tPoint.x, tPoint.y);
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawBenezene(){
		//path.moveTo(tPoint.x, tPoint.y);
		path.lineTo(tPoint.x,tPoint.y); 
		path.lineTo(200,100);   
        path.lineTo(900,50);   
        path.lineTo(50,50);   
        path.lineTo(700,100);   
        path.lineTo(tPoint.x,tPoint.y);  
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	
	
	
	public void saveState(){
		//drawCanvas.save();
		//drawCanvas.restore();
	}
	//detect user touch    
//	Point point = new Point();
//   
//    points.add(point);
//    for (int i = 1; i < points.size(); i++)
//	{	
//    	
//    	path.moveTo(points.get(i-1).x, points.get(i-1).y);
//		path.lineTo(points.get(i).x, points.get(i).y);
//		
//	}
	
}
