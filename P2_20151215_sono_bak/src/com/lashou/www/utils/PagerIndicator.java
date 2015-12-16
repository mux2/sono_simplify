package com.lashou.www.utils;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class PagerIndicator extends View
{

	private Paint paint;
	private Paint paint2;
	private Paint paint3;
	private float RADIUS=5;
	int num=2;
	private float offset;
	public PagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		InitPaint();
		// TODO Auto-generated constructor stub
	}
	private void InitPaint()
	{
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		paint2 = new Paint();
//		paint2.setColor(Color.WHITE);
		paint2.setColor(Color.parseColor("#d8d8d8"));
		paint3 = new Paint();
	 
		paint3.setColor(Color.parseColor("#ff4b04"));
		
	}
	@Override
	protected void onDraw(Canvas canvas)
	{	
	int cx= (int) ( getWidth()/2-(num-1)*1.5*RADIUS);
	int cy= getHeight()/2;	
		for (int i = 0; i < num; i++)
		{
//			canvas.drawCircle(cx+i*3*RADIUS, cy, RADIUS, paint);
//			canvas.drawCircle(cx+i*3*RADIUS, cy, RADIUS-2, paint2);
 			canvas.drawCircle(cx+i*3*RADIUS, cy, RADIUS, paint2);
		}
//		canvas.drawCircle(cx+offset, cy, RADIUS-1, paint3);
 		canvas.drawCircle(cx+offset, cy, RADIUS, paint3);
		super.onDraw(canvas);
	}
	public void move(int position, float percent)
	{
		position%=num;
		offset =position*3*RADIUS+percent*3*RADIUS;
		if(offset>(num-1)*3*RADIUS){
			offset=(num-1)*3*RADIUS;
		}
		invalidate();
	}
	
}
