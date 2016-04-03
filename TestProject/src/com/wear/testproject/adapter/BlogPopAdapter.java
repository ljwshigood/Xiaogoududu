package com.wear.testproject.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.SerialNumber;

public class BlogPopAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext;

	private List<SerialNumber> mSerialNumberList ;

	private LayoutInflater mLayoutInflater;
	
	private DisplayImageOptions mDisplayImageOptions ;

	public BlogPopAdapter(Context context,List<SerialNumber> serialNumberList,DisplayImageOptions options) {
		this.mSerialNumberList = serialNumberList;
		this.mContext = context;
		this.mDisplayImageOptions = options ; 
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mSerialNumberList == null ? 1 : mSerialNumberList.size()+1;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	final int VIEW_TYPE = 2;

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE;
	}

	@Override
	public int getItemViewType(int position) {
		int type = 0;
		int max = mSerialNumberList == null ? 1 : mSerialNumberList.size();
		if (position == max) {
			type = 1;
		}
		return type;
	}
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		AddViewHolder addViewHolder ;
		int viewType = getItemViewType(position);
		if (viewType == 1) {
			if (convertView == null) {
				addViewHolder = new AddViewHolder();
				convertView = mLayoutInflater.inflate(R.layout.item_add, null);
				addViewHolder.mIvPhoto = (ImageView) convertView.findViewById(R.id.iv_add);
				convertView.setTag(addViewHolder);
			} else {
				addViewHolder = (AddViewHolder) convertView.getTag();
			}
			addViewHolder.mIvPhoto.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext,CaptureActivity.class);
					intent.putExtra("action", 1);
					mContext.startActivity(intent);
				}
				
			});
			
		} else {
			SerialNumber serialNumber = mSerialNumberList.get(position);
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = mLayoutInflater.inflate(R.layout.item_blog_pop, null);
				viewHolder.mIvPhoto = (ImageView)convertView.findViewById(R.id.iv_photo);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
		
			String url = serialNumber.getFpicture();
			
			ImageLoader.getInstance().displayImage(url, viewHolder.mIvPhoto,mDisplayImageOptions, 
						new SimpleImageLoadingListener() {
							@Override
							public void onLoadingStarted(String imageUri, View view) {
								
							}
	
							@Override
							public void onLoadingFailed(String imageUri, View view,FailReason failReason) {
							}
	
							@Override
							public void onLoadingComplete(String imageUri, View view,Bitmap loadedImage) {
								
							}
						}, new ImageLoadingProgressListener() {
							@Override
							public void onProgressUpdate(String imageUri, View view,int current, int total) {
								
							}
			});
		}
		
		return convertView;
	}
	
	static class AddViewHolder {
		ImageView mIvPhoto ;
	}
	
	static class ViewHolder {
		ImageView mIvPhoto ;
	}
	
	private onSerialNumberListner mSerialNumberListener ; 
	
	public onSerialNumberListner getmSerialNumberListener() {
		return mSerialNumberListener;
	}

	public void setmSerialNumberListener(onSerialNumberListner mSerialNumberListener) {
		this.mSerialNumberListener = mSerialNumberListener;
	}

	public interface onSerialNumberListner{
		
		public void onSerialNumber(SerialNumber number);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		SerialNumber serialNumber = mSerialNumberList.get(position);
		mSerialNumberListener.onSerialNumber(serialNumber);
	}

}
