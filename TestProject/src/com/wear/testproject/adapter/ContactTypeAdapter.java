package com.wear.testproject.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wear.testproject.R;
import com.wear.testproject.bean.ContactBean;

public class ContactTypeAdapter extends BaseAdapter {

	private Context mContext;

	private List<ContactBean> mList;

	private LayoutInflater mLayoutInflater;

	public ContactTypeAdapter(Context context, List<ContactBean> list) {
		this.mContext = context;
		this.mList = list;
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

	static class ViewHolder {
		TextView mTvContact;
		ImageView mIvSelect ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		ContactBean bean = mList.get(position);

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_contact_type,null);
			viewHolder.mTvContact = (TextView) convertView.findViewById(R.id.contact);
			viewHolder.mIvSelect = (ImageView)convertView.findViewById(R.id.iv_select);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mTvContact.setText(bean.getInfo());
		if(bean.isSelect()){
			viewHolder.mIvSelect.setVisibility(View.VISIBLE);
		}else{
			viewHolder.mIvSelect.setVisibility(View.GONE);
		}
		
		return convertView;
	}

}
