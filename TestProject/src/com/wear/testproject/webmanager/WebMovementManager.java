package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.MoveComponment;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.TrackBeanComponment;
import com.wear.testproject.impl.IElectFenceListener;
import com.wear.testproject.utils.Constants;

public class WebMovementManager {

	private static WebMovementManager mInstance;

	private WebManager mWebManager;

	private Context mContext;

	private WebMovementManager(Context context) {
		super();
		mContext = context;
		mWebManager = WebManager.getInstance(context);
	}

	public static WebMovementManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebMovementManager(context);

		}
		return mInstance;
	}

	public void searchMovment(Context context, String serialNumber,
			String startTime, String endTime) {

		RequestParams params = new RequestParams();
		params.put("serialNumber", serialNumber);
		params.put("startTime", startTime);
		params.put("endTime", endTime);

		mWebManager.get(Constants.HOST + Constants.SEARCHMOVMENT, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						TrackBeanComponment bean = gson.fromJson(json,TrackBeanComponment.class);
						mIElectFenceListener.searchMovement(bean);
						
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}

	public void searchLoaPhone(Context context, String phone) {

		RequestParams params = new RequestParams();
		params.put("phone", phone);

		mWebManager.get(Constants.HOST + Constants.SEARCHLOAPHONE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						MoveComponment bean = gson.fromJson(json,MoveComponment.class);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}

	public void addElectFence(Context context, String serialNumber,
			String name, String locationInfo, String scope, int mode) {

		RequestParams params = new RequestParams();
		params.put("serialNumber", serialNumber);
		params.put("locationInfo", locationInfo);
		params.put("mode", mode);
		params.put("name", name);
		params.put("scope", scope);

		mWebManager.get(Constants.HOST + Constants.ADDELECTFENCE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						ResultBean bean = gson.fromJson(json,ResultBean.class);
						mIElectFenceListener.addElectFence(bean);
						
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}
	
	public void modifyElectFence(Context context, String areaNumber ,String serialNumber,
			String name, String locationInfo, String scope, int mode) {

		RequestParams params = new RequestParams();
		params.put("areaNumber", areaNumber);
		params.put("serialNumber", serialNumber);
		params.put("locationInfo", locationInfo);
		params.put("mode", mode);
		params.put("name", name);
		params.put("scope", scope);

		mWebManager.get(Constants.HOST + Constants.ADDELECTFENCE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						ResultBean bean = gson.fromJson(json,ResultBean.class);
						mIElectFenceListener.modifyElectFence(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}

	private IElectFenceListener mIElectFenceListener ;
	
	public IElectFenceListener getmIElectFenceListener() {
		return mIElectFenceListener;
	}

	public void setmIElectFenceListener(IElectFenceListener mIElectFenceListener) {
		this.mIElectFenceListener = mIElectFenceListener;
	}

	public void searchElectFence(Context context,String serialNumber) {

		RequestParams params = new RequestParams();
		params.put("serialNumber", serialNumber);

		mWebManager.get(Constants.HOST + Constants.SEARCHELECTFENCE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						MoveComponment bean = gson.fromJson(json,MoveComponment.class);
						mIElectFenceListener.searchElectFence(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}
	
	public void deleteElectFence(Context context,String serialNumber,String areaNumber) {

		RequestParams params = new RequestParams();
		params.put("serialNumber", serialNumber);
		params.put("areaNumber", areaNumber);

		mWebManager.get(Constants.HOST + Constants.DELETEELECTFENCE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						ResultBean bean = gson.fromJson(json,ResultBean.class);
						mIElectFenceListener.deleteElectFence(bean);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}
	
	public void queryLocEleChange(Context context,String serialNumber) {

		RequestParams params = new RequestParams();
		params.put("serialNumber", serialNumber);
		mWebManager.get(Constants.HOST + Constants.DELETEELECTFENCE, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onStart() {
						super.onStart();

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String json = new String(responseBody);
						Gson gson = new Gson();
						MoveComponment bean = gson.fromJson(json,MoveComponment.class);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						error.printStackTrace(System.out);
					}

				});
	}
	
}
