package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.FriendBeanComponment;
import com.wear.testproject.bean.FriendsComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.impl.IFriendsListener;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.widget.WindowsToast;

public class WebFriendManager {

	private static WebFriendManager mInstance;

	private WebManager mWebManager;

	private Context mContext;

	private WebFriendManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebFriendManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebFriendManager(context);

		}
		return mInstance;
	}
	
	private IFriendsListener mIFriendsListener ;

	public IFriendsListener getmIFriendsListener() {
		return mIFriendsListener;
	}

	public void setmIFriendsListener(IFriendsListener mIFriendsListener) {
		this.mIFriendsListener = mIFriendsListener;
	}
	
	public void passFriendReq(String frelationid,String passaction,final IFriendsListener iFriendsListener){
		RequestParams params = new RequestParams();
		params.put("frelationid", frelationid);
		params.put("passaction", passaction);

		mWebManager.get(Constants.HOST + Constants.PASSFRIENDREQ, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork()
									.startNetWorkRequest("");
						}
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						ResultBean bean = gson.fromJson(json, ResultBean.class);
						iFriendsListener.passFriendReq(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork().netWorkError("");
						}
					}

				});
	}
	
	public void postFriendReq(Context context ,String tosnid ,String fromsnid,String attachText){
		
		RequestParams params = new RequestParams();
		params.put("tosnid", tosnid);
		params.put("fromsnid", fromsnid);
		params.put("attachText", attachText);

		mWebManager.get(Constants.HOST + Constants.POSTFRIENDREQ, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork()
									.startNetWorkRequest("");
						}
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						ResultBean bean = gson.fromJson(json,ResultBean.class);
						WindowsToast.makeText(mContext, bean.getInfo()).show();
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork().netWorkError("");
						}
					}

				});
		
	}
	
	public void searchSerialNumber(String phonenum){
		
		RequestParams params = new RequestParams();
		params.put("phonenum", phonenum);

		mWebManager.get(Constants.HOST + Constants.SEARCHSERIALNUMBER, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork()
									.startNetWorkRequest("");
						}
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						FriendsComponment bean = gson.fromJson(json,FriendsComponment.class);
						mIFriendsListener.queryFriend(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork().netWorkError("");
						}
					}

				});
	}


	public void queryFriendList(Context context, int qtype, String snid) {

		RequestParams params = new RequestParams();
		params.put("qtype", qtype);
		params.put("snid", snid);

		mWebManager.get(Constants.HOST + Constants.QUERYFRIENDLIST, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork()
									.startNetWorkRequest("");
						}
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						FriendBeanComponment bean = gson.fromJson(json,FriendBeanComponment.class);
						mIFriendsListener.queryFriendList(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
						if (mWebManager.getmListenerNetWork() != null) {
							mWebManager.getmListenerNetWork().netWorkError("");
						}
					}

				});
	}

}
