package com.wear.testproject.webmanager;

import java.io.File;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.utils.Constants;

public class WebPedoMeterManager {

	private static WebPedoMeterManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebPedoMeterManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebPedoMeterManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebPedoMeterManager(context);
			
		}
		return mInstance ;
	} 
	
	
	public void searchPedoMeter(Context context,String serialNumber,String time){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        params.put("time", time);
        
        mWebManager.get(Constants.HOST+Constants.SEARCHPEDOMETER,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
}
