package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.impl.IFeedBackListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.widget.WindowsToast;

public class WebFeedBackManager {

	private static WebFeedBackManager mInstance;

	private WebManager mWebManager;

	private Context mContext;

	private WebFeedBackManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebFeedBackManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebFeedBackManager(context);

		}
		return mInstance;
	}
	
	private IFeedBackListener mIFeedBackListener ;
	
	public IFeedBackListener getmIFeedBackListener() {
		return mIFeedBackListener;
	}

	public void setmIFeedBackListener(IFeedBackListener mIFeedBackListener) {
		this.mIFeedBackListener = mIFeedBackListener;
	}

	public void postFeedBack(int category,String usrid,String content){
		
		RequestParams params = new RequestParams();  
        params.put("category", category);   
        params.put("usrid", usrid);
        params.put("content", content);
        
        mWebManager.get(Constants.HOST+Constants.POSTFEEDBACK,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ResultBean bean = gson.fromJson(json, ResultBean.class);
					WindowsToast.makeText(mContext, bean.getInfo()).show();
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}

}
