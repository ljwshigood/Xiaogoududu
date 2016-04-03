package com.wear.testproject.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.MoveBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.ui.SafeQoneEditActivity;
import com.wear.testproject.utils.Constants;

public class SafeQoneListAdapter extends BaseAdapter implements OnItemClickListener{

	private Context mContext ;
	
	private LayoutInflater mLayoutInflater ;
	
	private List<MoveBean> mSafeList ;
	
	public SafeQoneListAdapter(Context context,List<MoveBean>safeList){
		this.mContext = context ;
		this.mSafeList = safeList ;
		this.mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mSafeList == null ? 0 :mSafeList.size();
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
		
		MoveBean bean = mSafeList.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_safe_qone_list, null);
			holder.mTvName = (TextView)convertView.findViewById(R.id.tv_name);
			holder.mTvFanwei = (TextView)convertView.findViewById(R.id.tv_safe_fanwei_value);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.mTvName.setText(bean.getName());
		holder.mTvFanwei.setText(String.valueOf(bean.getScope())+" m");
		return convertView;
	}
	
	class ViewHolder{
		TextView mTvFanwei ;
		TextView mTvName ;
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
		
		MoveBean bean = mSafeList.get(position);
		Intent intent = new Intent(mContext,SafeQoneEditActivity.class);
		intent.putExtra("action", 1);
		intent.putExtra("serialNumber", mSerialNumber);
		intent.putExtra("move", bean);
		((Activity)mContext).startActivityForResult(intent,Constants.EDITER_QONE);
	}
}
