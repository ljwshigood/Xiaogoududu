package com.wear.testproject.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.InfoBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.ui.ArriveTipActivity;
import com.wear.testproject.ui.FriendRequestActivity;
import com.wear.testproject.ui.LeaveTipActivity;

public class InfoAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext;

	private LayoutInflater mLayoutInflater;

	private List<InfoBean> mList;

	private SerialNumber mSerialNumber ;
	
	public SerialNumber getmSerialNumber() {
		return mSerialNumber;
	}

	public void setmSerialNumber(SerialNumber mSerialNumber) {
		this.mSerialNumber = mSerialNumber;
	}

	public InfoAdapter(Context context, List<InfoBean> mInfoList) {
		this.mContext = context;
		this.mList = mInfoList;
		this.mLayoutInflater = LayoutInflater.from(context);
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
		InfoBean bean = mList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_info, null);
			holder.mTvInfo = (TextView)convertView.findViewById(R.id.tv_info);
			holder.mTvDetail = (TextView)convertView.findViewById(R.id.tv_detail);
			holder.mIvIcon = (ImageView)convertView.findViewById(R.id.iv_info);
			holder.mIvNext = (ImageView)convertView.findViewById(R.id.iv_next);
			holder.mTvShengyuBattery = (TextView)convertView.findViewById(R.id.tv_shengyu_battery);
			holder.mTvBatteryInfo = (TextView)convertView.findViewById(R.id.batter_info);
			holder.mLLArriveTip = (LinearLayout)convertView.findViewById(R.id.ll_info);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.mIvIcon.setImageResource(bean.getRes());
		holder.mTvInfo.setText(bean.getInfo());
		holder.mTvDetail.setText(bean.getDetail());
		
		if(position == 4 || position == 5){
			holder.mIvNext.setVisibility(View.INVISIBLE);
			holder.mTvShengyuBattery.setVisibility(View.GONE);
			holder.mTvBatteryInfo.setVisibility(View.GONE);
			holder.mTvDetail.setTextColor(Color.DKGRAY);
		}else if(position == 3 && !bean.getDetail().equals(mContext.getString(R.string.non_message))){
			holder.mTvDetail.setTextColor(Color.RED);
			holder.mTvDetail.setText(bean.getDetail() +"%");
			holder.mTvShengyuBattery.setVisibility(View.VISIBLE);
			holder.mTvBatteryInfo.setVisibility(View.VISIBLE);
		}else{
			holder.mTvShengyuBattery.setVisibility(View.GONE);
			holder.mTvBatteryInfo.setVisibility(View.GONE);
			holder.mTvDetail.setTextColor(Color.DKGRAY);
		}
		
		if(position == 1){
			holder.mLLArriveTip.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext,ArriveTipActivity.class);
					mContext.startActivity(intent);
				}
			});
		}
		
		if(position == 2){
			holder.mLLArriveTip.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(mContext,LeaveTipActivity.class);
					mContext.startActivity(intent);
				}
			});
		}
		
		return convertView;
	}

	class ViewHolder {
		LinearLayout mLLArriveTip ;
		TextView mTvInfo;
		TextView mTvDetail;
		ImageView mIvIcon;
		ImageView mIvNext ;
		TextView mTvShengyuBattery;
		TextView mTvBatteryInfo;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:
			Intent intent = new Intent(mContext,FriendRequestActivity.class);
			intent.putExtra("serialNumber", mSerialNumber);
			mContext.startActivity(intent);
			break;

		default:
			break;
		}
	}
}
