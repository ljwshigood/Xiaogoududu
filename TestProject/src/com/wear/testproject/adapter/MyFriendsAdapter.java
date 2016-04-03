package com.wear.testproject.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.FriendsBean;
import com.wear.testproject.utils.ImageTools;

public class MyFriendsAdapter extends BaseAdapter {

	private Context mContext;

	private LayoutInflater mLayoutInflater;

	private List<FriendsBean> mFriendsList;
	
	private DisplayImageOptions mOptions ;

	public MyFriendsAdapter(Context context, List<FriendsBean> list,DisplayImageOptions options) {
		this.mContext = context;
		this.mFriendsList = list;
		this.mOptions = options;
		this.mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mFriendsList == null ? 0 : mFriendsList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		FriendsBean bean = mFriendsList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_friends_list, null);
			holder.mTvName = (TextView)convertView.findViewById(R.id.tv_name);
			holder.mTvPhone = (TextView)convertView.findViewById(R.id.tv_phone);
			holder.mIvIcon = (ImageView)convertView.findViewById(R.id.iv_photo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.mTvName.setText(bean.getFriendname());
		holder.mTvPhone.setText(bean.getFmobile());
		initUserPhotoInfo(bean.getFrompic(),holder.mIvIcon);
		return convertView;
	}
	
	private void initUserPhotoInfo(String url,ImageView iv){
		ImageLoader.getInstance().displayImage(url, iv,mOptions, 
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
	
	
	class ViewHolder{
		TextView mTvName ;
		TextView mTvPhone ;
		ImageView mIvIcon ;
	}

}
