package edu.sbu.cs.android.drawing;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;

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
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(path, drawPaint);
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	

		 tPoint.x = (int) event.getX();
		 tPoint.y = (int) event.getY();
		 path.lineTo(tPoint.x, tPoint.y);
		 drawCanvas.drawPath(path, drawPaint);
		 path.setLastPoint(tPoint.x, tPoint.y);
		 path.moveTo(tPoint.x, tPoint.y);
		 invalidate();
		 
		 
		 
		 
		 final int action = event.getAction();
	        switch (action) {
	        case MotionEvent.ACTION_DOWN: {

	            break;
	        }

	        case MotionEvent.ACTION_MOVE: {
	         
	            break;
	        }

	        case MotionEvent.ACTION_UP: {
	          
	            break;
	        }

	        case MotionEvent.ACTION_CANCEL: {
	         
	            break;
	        }
	        }
		 return true;
	}


	public void drawSinlgeBond(){
		//  move to selected point
		//  draw one line
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawDoubleBond(){
		//  move to selected point
		//  draw two lines
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawTripleBond(){
		//  move to selected point
		//  draw three lines  
		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
	public void drawBenezene(){

		drawCanvas.drawPath(path, drawPaint);
		invalidate();
	}
}
