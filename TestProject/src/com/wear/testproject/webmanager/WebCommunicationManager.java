package com.wear.testproject.webmanager;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.ISendSMSListener;
import com.wear.testproject.utils.Constants;

import android.content.Context;

public class WebCommunicationManager {
	
	private static WebCommunicationManager mInstance;

	private WebManager mWebManager;

	private Context mContext;

	private WebCommunicationManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebCommunicationManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebCommunicationManager(context);

		}
		return mInstance;
	}
	
	private ISendSMSListener mISendSmsListener ;
	
	public ISendSMSListener getmISendSmsListener() {
		return mISendSmsListener;
	}

	public void setmISendSmsListener(ISendSMSListener mISendSmsListener) {
		this.mISendSmsListener = mISendSmsListener;
	}

	public void sendSMS(String toNum,String templatedid,String valide){
		
		RequestParams params = new RequestParams();  
        params.put("toNum", toNum);   
        params.put("templateid",templatedid);
        params.put("param", valide);
        
        mWebManager.get(Constants.HOST+Constants.SENDSMS,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ResultBean bean  = gson.fromJson(json, ResultBean.class);
					mISendSmsListener.sendSMS(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
}
