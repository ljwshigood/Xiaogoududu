package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.LbsBean;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.impl.ILbsListener;
import com.wear.testproject.utils.Constants;

public class WebLbsManager {

	private static WebLbsManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebLbsManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebLbsManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebLbsManager(context);
			
		}
		return mInstance ;
	} 
	
	private ILbsListener mILbslistener ;
	
	public ILbsListener getmILbslistener() {
		return mILbslistener;
	}

	public void setmILbslistener(ILbsListener mILbslistener) {
		this.mILbslistener = mILbslistener;
	}

	public void searchLbs(Context context,String serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        
        mWebManager.get(Constants.HOST+Constants.SEARCHLBS,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					LbsBean bean = gson.fromJson(json, LbsBean.class);
					if(mILbslistener != null){
						mILbslistener.searchLbs(bean);
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	

	public void setLbs(Context context,String serialNumber,int lbs,int gps){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);
        params.put("lbs", lbs);
        params.put("gps", gps);
        
        mWebManager.get(Constants.HOST+Constants.SETLBS,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					LbsBean lbs = gson.fromJson(json, LbsBean.class);
					if(mILbslistener != null){
						mILbslistener.setLbs(lbs);
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	
}
