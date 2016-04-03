package com.wear.testproject.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmBean;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsBean;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.ui.AlaramSetActivity;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.webmanager.WebFriendManager;

public class FriendsAdapter extends BaseAdapter implements OnItemClickListener{
	
	private Context mContext;
	
	private List<FriendsBean> mList ;
	
	private LayoutInflater mLayoutInflater;
	
	public FriendsAdapter(Context context,List<FriendsBean> list){
		this.mContext = context ;
		this.mList = list ;
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mList == null ? 0 : mList.size();
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

		ViewHolder viewHolder;
		final FriendsBean friendsBean  = mList.get(position);
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_friends_request, null);
			viewHolder.mTvNickName = (TextView)convertView.findViewById(R.id.tv_nick_name);
			viewHolder.mTvAccountId = (TextView)convertView.findViewById(R.id.tv_account_id);
			viewHolder.mIvPhoto = (ImageView)convertView.findViewById(R.id.iv_account);
			viewHolder.mBtnAgree = (Button)convertView.findViewById(R.id.btn_agree);
			viewHolder.mBtnRefuse = (Button)convertView.findViewById(R.id.btn_refuse);
			viewHolder.mBtnAleardy = (Button)convertView.findViewById(R.id.btn_already_agree);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.mBtnAgree.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				WebFriendManager.getInstance(mContext).passFriendReq(friendsBean.getFrelationid(), "1",new IFriendsListener() {
					
					@Override
					public void queryFriendList(FriendBeanComponment bean) {
						
					}
					
					@Override
					public void queryFriend(FriendsComponment bean) {
						
					}
					
					@Override
					public void passFriendReq(ResultBean bean) {
						
					}
				});
			}
		});
		
		viewHolder.mBtnRefuse.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					WebFriendManager.getInstance(mContext).passFriendReq(friendsBean.getFrelationid(), "2",new IFriendsListener() {

						@Override
						public void queryFriendList(FriendBeanComponment bean) {
							
						}

						@Override
						public void queryFriend(FriendsComponment bean) {
							
						}

						@Override
						public void passFriendReq(ResultBean bean) {
							
						}
						
					});
				}
			});
		
		return convertView;
	}

	static class ViewHolder {
		
		ImageView mIvPhoto ;
		
		TextView mTvNickName ;
		
		TextView mTvAccountId ;
		
		Button mBtnAgree ;
		
		Button mBtnRefuse ;
		
		Button mBtnAleardy ;
	}
	
	private SerialNumber mSerialNumber ;
	
	public SerialNumber getmSerialNumber() {
		return mSerialNumber;
	}

	public void setmSerialNumber(SerialNumber mSerialNumber) {
		this.mSerialNumber = mSerialNumber;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
	}
	
}
