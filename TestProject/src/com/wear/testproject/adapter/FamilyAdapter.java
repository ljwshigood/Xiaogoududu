package com.wear.testproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class FamilyAdapter extends BaseAdapter {
	
	private Context mContext;

	private LayoutInflater mLayoutInflater;

	public FamilyAdapter(Context context) {
		this.mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return 0;
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
		return null;
	}

}
