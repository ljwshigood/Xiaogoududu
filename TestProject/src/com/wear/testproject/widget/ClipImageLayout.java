package com.wear.testproject.widget;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.wear.testproject.utils.PictureUtil;

/**
 *
 *
 */
public class ClipImageLayout extends RelativeLayout
{

	private ClipZoomImageView mZoomImageView;
	private ClipImageBorderView mClipImageView;

	/**
	 * 这里测试，直接写死了大小，真正使用过程中，可以提取为自定义属性
	 */
	private int mHorizontalPadding = 20;
	
	private String mBitmapFile ;

	public String getmBitmapFile() {
		return mBitmapFile;
	}

	public void setmBitmapFile(String mBitmapFile) {
		this.mBitmapFile = mBitmapFile;
		mZoomImageView.setImageBitmap(decodeBitmapFile());
	}

	public ClipImageLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		mZoomImageView = new ClipZoomImageView(context);
		mClipImageView = new ClipImageBorderView(context);

		android.view.ViewGroup.LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		
		/**
		 * 这里测试，直接写死了图片，真正使用过程中，可以提取为自定义属性
		 */
		
		this.addView(mZoomImageView, lp);
		this.addView(mClipImageView, lp);
		// 计算padding的px
		mHorizontalPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
						.getDisplayMetrics());
		mZoomImageView.setHorizontalPadding(mHorizontalPadding);
		mClipImageView.setHorizontalPadding(mHorizontalPadding);
	}

	private Bitmap decodeBitmapFile(){
		Bitmap bitmap = null ;
		if(mBitmapFile != null){
			File file = new File(mBitmapFile);
			if(file.exists()){
				bitmap = PictureUtil.getSmallBitmap(mBitmapFile);
			}	
		}
		return bitmap ;
	} 
	
	/**
	 * 对外公布设置边距的方法,单位为dp
	 * 
	 * @param mHorizontalPadding
	 */
	public void setHorizontalPadding(int mHorizontalPadding)
	{
		this.mHorizontalPadding = mHorizontalPadding;
	}

	/**
	 * 裁切图片
	 * 
	 * @return
	 */
	public Bitmap clip()
	{
		return mZoomImageView.clip();
	}

}
