package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.InformationBean;
import com.wear.testproject.impl.IInfomationManager;
import com.wear.testproject.utils.Constants;

public class WebInfomationManager {

	private static WebInfomationManager mInstance;

	private WebManager mWebManager;

	private Context mContext;

	private WebInfomationManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebInfomationManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebInfomationManager(context);

		}
		return mInstance;
	}
	
	private IInfomationManager mInfomationMaager ;
	
	public IInfomationManager getmInfomationMaager() {
		return mInfomationMaager;
	}

	public void setmInfomationMaager(IInfomationManager mInfomationMaager) {
		this.mInfomationMaager = mInfomationMaager;
	}

	public void queryMsgCenter(String serialnumid){
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialnumid);   
        
        mWebManager.get(Constants.HOST+Constants.QUERYMSGCENTER,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					InformationBean bean = gson.fromJson(json, InformationBean.class);
					mInfomationMaager.queryMegCenter(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
}
