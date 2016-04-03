/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.client.android;

import java.util.Collection;
import java.util.HashSet;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.wear.testproject.R;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class ViewfinderView extends View {

	private static final int[] SCANNER_ALPHA = { 0, 64, 128, 192, 255, 192,
			128, 64 };
	// ˢ��Ƶ��
	private static final long ANIMATION_DELAY = 10L;
	private static final int OPAQUE = 0xFF;

	private final Paint paint;
	
	private Bitmap resultBitmap;
	
	private final int maskColor;
	private final int resultColor;
	private final int frameColor;
	private final int laserColor;
	private final int resultPointColor;
	private int scannerAlpha;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	private int slideTop;
	
	private static final int SCAN_LINE_WIDTH = 6;
	
	private static final int SCAN_LINE_PADDING = 7;
	
	private static final int SPEEN_DISTANCE = 7;
	
	private boolean isFrist = true;
	
	private static final int TEXT_SIZE = 30;
	
	public void setCameraManager(CameraManager cameraManager) {
	    this.cameraManager = cameraManager;
	}
  
	private CameraManager cameraManager;
	  
	private Context mContext ;
	
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context ;
		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);
		frameColor = resources.getColor(R.color.viewfinder_frame);
		laserColor = resources.getColor(R.color.viewfinder_laser);
		resultPointColor = resources.getColor(R.color.possible_result_points);
		scannerAlpha = 0;
		possibleResultPoints = new HashSet<ResultPoint>(5);

	}
	
	@Override
	public void onDraw(Canvas canvas) {
		Rect frame = cameraManager.getFramingRect();
		if (frame == null) {
			return;
		}
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		
		paint.setColor(getResources().getColor(R.color.green));

		canvas.drawRect(frame.left, frame.top, frame.left + 30, frame.top + 5,
				paint);

		canvas.drawRect(frame.left, frame.top, frame.left + 5, frame.top + 30,
				paint);

		canvas.drawRect(frame.right - 30, frame.top, frame.right,
				frame.top + 5, paint);

		canvas.drawRect(frame.right - 5, frame.top, frame.right,
				frame.top + 30, paint);

		canvas.drawRect(frame.left, frame.bottom - 5, frame.left + 30,
				frame.bottom, paint);

		canvas.drawRect(frame.left, frame.bottom - 30, frame.left + 5,
				frame.bottom, paint);

		canvas.drawRect(frame.right - 30, frame.bottom - 5, frame.right,
				frame.bottom, paint);

		canvas.drawRect(frame.right - 5, frame.bottom - 30, frame.right,
				frame.bottom, paint);

		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,paint);
		canvas.drawRect(0, frame.bottom + 1 , width, height, paint);

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {
			if(isFrist){
				slideTop = frame.top;
				isFrist = false;
			}
			
			paint.setColor(getResources().getColor(R.color.green));
			slideTop += SPEEN_DISTANCE;
			if (slideTop >= frame.bottom) {
				slideTop = frame.top;
			}
			
			
			canvas.drawRect(frame.left + SCAN_LINE_PADDING, slideTop
					- SCAN_LINE_WIDTH / 2, frame.right - SCAN_LINE_PADDING,
					slideTop + SCAN_LINE_WIDTH / 2, paint);
			
			//
			paint.setColor(Color.WHITE);    
			paint.setTextSize(TEXT_SIZE);    
			paint.setAlpha(0x40);    
			paint.setTypeface(Typeface.DEFAULT_BOLD);   
			String text = mContext.getString(R.string.scan_info);
			float textWidth = paint.measureText(text);  
			canvas.drawText(text, (width - textWidth)/2, (float) (frame.bottom+TEXT_SIZE), paint);

			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(5);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 6.0f, paint);
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 3.0f, paint);
				}
			}

			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);
		}
	}

	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 * 
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}


}
