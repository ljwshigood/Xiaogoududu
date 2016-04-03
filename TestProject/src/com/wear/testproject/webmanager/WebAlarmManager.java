package com.wear.testproject.webmanager;

import org.apache.http.Header;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.AlarmComponment;
import com.wear.testproject.bean.NewAlarmBean;
import com.wear.testproject.bean.SlienTimeBean;
import com.wear.testproject.impl.IAlarmListener;
import com.wear.testproject.utils.Constants;

public class WebAlarmManager {

	private static WebAlarmManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebAlarmManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebAlarmManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebAlarmManager(context);
			
		}
		return mInstance ;
	} 
	
	private IAlarmListener mIAlarmListener ;
	
	
	public IAlarmListener getmIAlarmListener() {
		return mIAlarmListener;
	}

	public void setmIAlarmListener(IAlarmListener mIAlarmListener) {
		this.mIAlarmListener = mIAlarmListener;
	}
	
	public void serialNumAction(String serialnumid,int action,String weekIsValid,String begintime,String endtime){
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialnumid);   
        params.put("action", action);
        params.put("weekIsValid", weekIsValid);
        params.put("begintime", begintime);
        params.put("endtime", endtime);
        
        mWebManager.get(Constants.HOST+Constants.SERIALNUMACTION,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					SlienTimeBean bean = gson.fromJson(json, SlienTimeBean.class);
					mIAlarmListener.serialNumAction(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	public void searchSerialParm(String serialnumid,int category){
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialnumid);   
        params.put("category", category);
        
        mWebManager.get(Constants.HOST+Constants.SEARCHSERIALPARM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					SlienTimeBean bean = gson.fromJson(json, SlienTimeBean.class);
					mIAlarmListener.searchSerialParm(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	public void serialNumAction(String serialnumid,String action){
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialnumid);   
        params.put("action", action);
        
        mWebManager.get(Constants.HOST+Constants.SERIALNUMACTION,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					NewAlarmBean bean = gson.fromJson(json, NewAlarmBean.class);
					mIAlarmListener.newAlarm(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	

	public void newAlarm(Context context,String serialNumber,String alarmName,String alarm){
		
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialNumber);   
        params.put("alarmName", alarmName);
        params.put("alarmVal", alarm);
        
        mWebManager.get(Constants.HOST+Constants.SETALARM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					NewAlarmBean bean = gson.fromJson(json, NewAlarmBean.class);
					mIAlarmListener.newAlarm(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	

	public void searchAlarm(Context context,String serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialNumber);
        
        mWebManager.get(Constants.HOST+Constants.LISTALARM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					AlarmComponment componment = gson.fromJson(json, AlarmComponment.class);
					mIAlarmListener.queryAlarmList(componment);
				}	

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	public void updateAlarm(Context context,String alarmid,String alarmName,String alarmVal){
		
		RequestParams params = new RequestParams();  
        params.put("alarmid", alarmid);
        params.put("alarmName", alarmName);
        params.put("alarmVal", alarmVal);
        
        mWebManager.get(Constants.HOST+Constants.UPDATEALARM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					NewAlarmBean componment = gson.fromJson(json, NewAlarmBean.class);
					mIAlarmListener.updateAlarm(componment);
				}	

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	public void deleteAlarm(Context context,String alarmid){
		
		RequestParams params = new RequestParams();  
        params.put("alarmid", alarmid);
        
        mWebManager.get(Constants.HOST+Constants.DELETEALARM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					NewAlarmBean componment = gson.fromJson(json, NewAlarmBean.class);
					mIAlarmListener.deleteAlarm(componment);
				}	

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	
}
