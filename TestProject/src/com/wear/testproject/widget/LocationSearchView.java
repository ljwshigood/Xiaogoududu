package com.wear.testproject.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wear.testproject.R;
/**
 * SearchText
 *
 */
public class LocationSearchView extends LinearLayout {
	
	private ImageView mIvDelete;
	private EditText mEtSearch;
	
	private ILocationSearchChangeListener mLocationSearchListener;
	
	public ILocationSearchChangeListener getmLocationSearchListener() {
		return mLocationSearchListener;
	}

	public void setmLocationSearchListener(ILocationSearchChangeListener mLocationSearchListener) {
		this.mLocationSearchListener = mLocationSearchListener;
	}

	public LocationSearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		View view = LayoutInflater.from(context).inflate(R.layout.layout_search_text, null);
	    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(     
                   LinearLayout.LayoutParams.MATCH_PARENT,
                   LinearLayout.LayoutParams.WRAP_CONTENT     
        );   
 
		addView(view,params);
		
		mIvDelete = (ImageView) view.findViewById(R.id.iv_delete);
		mEtSearch = (EditText) view.findViewById(R.id.et_search);
		mIvDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mEtSearch.setText("");
			}
		});
		mEtSearch.addTextChangedListener(new MyTextWatcher());
	}
	
	private class MyTextWatcher implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) {
			if(mLocationSearchListener != null){
				mLocationSearchListener.editTextChange(mEtSearch.getText().toString());
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if(s.length() > 0){
				mIvDelete.setVisibility(View.VISIBLE);
			}else{
				mIvDelete.setVisibility(View.GONE);
			}
		}
	}
	
	public interface ILocationSearchChangeListener{
		public void editTextChange(String string);
	}
}
