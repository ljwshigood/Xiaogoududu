package com.wear.testproject.webmanager;

import java.io.File;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.utils.Constants;

public class WebFamilyManager {

	private static WebFamilyManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebFamilyManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebFamilyManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebFamilyManager(context);
			
		}
		return mInstance ;
	} 
	
	//TODO
	public void addFamilyNo(Context context,String serialNumber,String masterNo,String listenNo,String sos){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        params.put("masterNo", masterNo);
        params.put("listenNo", listenNo);
        params.put("sos", sos);
        
        mWebManager.get(Constants.HOST+Constants.ADDFAMILYNO,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					//TODO
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	

	public void searchFamilyNo(Context context,String serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);
        
        mWebManager.get(Constants.HOST+Constants.SEARCHFAMILYNO,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					//TODO
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	
}
