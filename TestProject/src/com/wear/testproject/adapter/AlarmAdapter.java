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
import android.widget.CheckBox;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.ui.AlaramSetActivity;
import com.wear.testproject.utils.Constants;

public class AlarmAdapter extends BaseAdapter implements OnItemClickListener{
	
	private Context mContext;
	
	private List<AlarmBean> mList ;
	
	private LayoutInflater mLayoutInflater;
	
	public AlarmAdapter(Context context,List<AlarmBean> list){
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
		AlarmBean bean = mList.get(position);
		
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_alarm_list, null);
			viewHolder.mIvAlarmTime = (TextView) convertView.findViewById(R.id.tv_alarm_time);
			viewHolder.mIvAlarmName = (TextView)convertView.findViewById(R.id.tv_alarm_name);
			viewHolder.mCbAlarm = (CheckBox)convertView.findViewById(R.id.cb_alarm);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.mIvAlarmTime.setText(bean.getHour() +":"+bean.getMinute());
		viewHolder.mIvAlarmName.setText(bean.getFname());
		viewHolder.mCbAlarm.setChecked(setCheckStatus(bean));
		return convertView;
	}
	
	private char[] mRepearChar = {'0','0','0','0','0','0','0','0'};

	private boolean setCheckStatus(AlarmBean bean) {
			
			String time = bean.getFtime();
			String[] timeArray = time.split(":");
			String repeart = timeArray[0];
			String mRepeart = Integer.toBinaryString(Integer.valueOf(repeart));
			switch (mRepeart.length()) {
			case 1:
				mRepeart = "0000000" +mRepeart ;
				break;
			case 2:
				mRepeart = "000000" +mRepeart ;
				break;
			case 3:
				mRepeart = "00000" +mRepeart ;
				break;
			case 4:
				mRepeart = "0000" +mRepeart ;
				break;
			case 5:
				mRepeart = "000" +mRepeart ;
				break;
			case 6:
				mRepeart = "00" +mRepeart ;
				break;
			case 7:
				mRepeart = "0" +mRepeart ;
				break;

			default:
				break;
			}
			
			mRepearChar = mRepeart.toCharArray();
			
			if(mRepearChar[0] == '1'){
				return true ;
			}else{
				return false ;
			}
	}
	
	static class ViewHolder {
		TextView mIvAlarmTime ;
		TextView mIvAlarmName;
		CheckBox mCbAlarm ;
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
		
		AlarmBean bean = mList.get(position);
		Intent intent = new Intent(mContext,AlaramSetActivity.class);
		intent.putExtra("action", 1);
		intent.putExtra("alarmBean", bean);
		intent.putExtra("serialNumber", mSerialNumber);
		((Activity)mContext).startActivityForResult(intent,Constants.EDITER_ALARM);
	}
	
}
