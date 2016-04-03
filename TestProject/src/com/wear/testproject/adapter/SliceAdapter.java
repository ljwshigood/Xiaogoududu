package com.wear.testproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.ui.MyFriendsActivity;
import com.wear.testproject.ui.SafeQoneListActivity;
import com.wear.testproject.ui.InformationActivity;

public class SliceAdapter extends BaseAdapter implements OnItemClickListener {

	private Context mContext;

	private LayoutInflater mLayoutInflater;

	private int[] mResDaweableList;

	private String[] mInfoList;

	private SerialNumber mSericalNumber;
	
	private UserInfoBean mUserInfoBean ;
	
	public UserInfoBean getmUserInfoBean() {
		return mUserInfoBean;
	}

	public void setmUserInfoBean(UserInfoBean mUserInfoBean) {
		this.mUserInfoBean = mUserInfoBean;
	}

	public SerialNumber getmSericalNumber() {
		return mSericalNumber;
	}

	public void setmSericalNumber(SerialNumber mSericalNumber) {
		this.mSericalNumber = mSericalNumber;
	}

	public SliceAdapter(Context context, int[] resDaweableList,
			String[] infoList) {
		this.mContext = context;
		this.mLayoutInflater = LayoutInflater.from(mContext);
		this.mResDaweableList = resDaweableList;
		mInfoList = infoList;
	}

	@Override
	public int getCount() {
		return mResDaweableList == null ? 0 : mResDaweableList.length;
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_left_menu, null);
			holder.mIvResDraweableList = (ImageView) convertView.findViewById(R.id.iv_left_menu);
			holder.mTvInfo = (TextView) convertView.findViewById(R.id.tv_left_menu);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mIvResDraweableList.setBackgroundResource(mResDaweableList[position]);
		holder.mTvInfo.setText(mInfoList[position]);

		return convertView;
	}

	public final class ViewHolder {
		public ImageView mIvResDraweableList;
		public TextView mTvInfo;
	}
	
	private IDismissView mIDismiss ;
	
	public IDismissView getmIDismiss() {
		return mIDismiss;
	}

	public void setmIDismiss(IDismissView mIDismiss) {
		this.mIDismiss = mIDismiss;
	}

	public interface IDismissView{
		
		public void dismiss();
		
		public void destoryActivity();
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			mIDismiss.dismiss();
			break;
		case 1:
			intent = new Intent(mContext, InformationActivity.class);
			intent.putExtra("userInfoBean", mUserInfoBean);
			mContext.startActivity(intent);
			break;

		case 2:
			intent = new Intent(mContext, SafeQoneListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("userInfoBean", mUserInfoBean);
			intent.putExtras(bundle);
			mContext.startActivity(intent);
			break;
		case 3:
			intent = new Intent(mContext, MyFriendsActivity.class);
			Bundle bundle1 = new Bundle();
			bundle1.putSerializable("userInfoBean", mUserInfoBean);
			intent.putExtras(bundle1);
			mContext.startActivity(intent);
			break;
		case 4:
			mIDismiss.destoryActivity();
			break;
		default:
			break;
		}
	}

}
