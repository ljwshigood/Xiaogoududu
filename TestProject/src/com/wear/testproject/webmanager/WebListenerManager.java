package com.wear.testproject.webmanager;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.ListenerBean;
import com.wear.testproject.impl.IWatchListener;
import com.wear.testproject.utils.Constants;

import android.content.Context;

public class WebListenerManager {

	private static WebListenerManager mInstance;

	private WebManager mWebManager;

	private Context mContext;
	
	private IWatchListener mIWatchListener ;

	public IWatchListener getmIWatchListener() {
		return mIWatchListener;
	}

	public void setmIWatchListener(IWatchListener mIWatchListener) {
		this.mIWatchListener = mIWatchListener;
	}

	private WebListenerManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebListenerManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebListenerManager(context);

		}
		return mInstance;
	}
	
	public void searchListener(Context context,String serialNumber){
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        
        mWebManager.get(Constants.HOST+Constants.SEARCHLISTEN,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ListenerBean bean = gson.fromJson(json, ListenerBean.class);
					if(mIWatchListener != null){
						mIWatchListener.searchListen(bean);
					}
					
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	public void sendListener(Context context,String serialNumber){
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        
        mWebManager.get(Constants.HOST+Constants.sendListen,params,new AsyncHttpResponseHandler() {
			
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
