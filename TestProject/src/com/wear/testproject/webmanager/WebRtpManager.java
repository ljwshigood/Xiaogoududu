package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.RtpBean;
import com.wear.testproject.utils.Constants;

public class WebRtpManager {

	private static WebRtpManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebRtpManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebRtpManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebRtpManager(context);
			
		}
		return mInstance ;
	} 
	
	
	public void searchRtp(Context context,String serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        
        mWebManager.get(Constants.HOST+Constants.SEARCHRTP,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					RtpBean bean = gson.fromJson(json, RtpBean.class);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	

	public void updateRtp(Context context,String serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);
        
        mWebManager.get(Constants.HOST+Constants.UPDATERTP,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					UserInfoBean bean = gson.fromJson(json, UserInfoBean.class);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	
}
