package com.wear.testproject.webmanager;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.bean.AccountInfoBean;
import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;
import com.wear.testproject.impl.IAccountManager;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.widget.WindowsToast;

public class WebAccountManager {

	private static WebAccountManager mInstance ;
	
	private WebManager mWebManager ;
	
	private Context mContext ;
	
	private WebAccountManager(Context context){
		super() ;
		mContext = context ;
		mWebManager = WebManager.getInstance(context);
	}
	
	public static WebAccountManager getInstance(Context context){
		if(mInstance == null){
			mInstance = new WebAccountManager(context);
			
		}
		return mInstance ;
	} 
	
	public void pushAndroidInfo(int action,String usrid){
		RequestParams params = new RequestParams();  
        params.put("usrid", usrid);   
        params.put("action",action);   
        
        mWebManager.get(Constants.HOST+Constants.PUSHANDROID,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					pushBean bean = gson.fromJson(json, pushBean.class);
					mIAcountManager.pushAndroid(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	public void userUpdate(AccountInfoBean bean){
		
		RequestParams params = new RequestParams();  
        params.put("usrid", bean.getFuniqueid());   
        params.put("sex", bean.getSex());   
        params.put("nickname", bean.getNickname());   
        params.put("birthday", bean.getBirthday());   
        params.put("height", bean.getHeight());   
        params.put("weight", bean.getWeight());   
        params.put("femail", bean.getFemail()); 
        
        mWebManager.get(Constants.HOST+Constants.USERUPDATE,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ResultBean bean = gson.fromJson(json, ResultBean.class);
					mIAcountManager.userUpdate(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	
	public void uploadUserLoad(String usrimg,String usrid){
		RequestParams params = new RequestParams();  
        try {
			params.put("usrimg", new File(usrimg));
			params.put("usrid", usrid);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}   
       
        mWebManager.post(Constants.HOST+Constants.DOUPLOAD,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					Log.e("liujw","################json : "+json);
					UserInfoBean bean = gson.fromJson(json, UserInfoBean.class);
					mIAcountManager.uploadFile(bean);
				}	

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     }); 
	}
	
	public void serialNumUpdate(SerialNumber serialNumber){
		
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialNumber.getFuniqueid());   
        params.put("sex",serialNumber.getFsex());   
        params.put("nickname",serialNumber.getNickname());   
        params.put("birthday", serialNumber.getBirthday());   
        params.put("height",serialNumber.getFeight());   
        params.put("weight", serialNumber.getFweight());   
        params.put("fschool", serialNumber.getFschool());   
        params.put("fgrade", serialNumber.getFgrade());   
        params.put("fcallname", serialNumber.getFcallname()); 
        params.put("fremark", serialNumber.getFremark()); 
        
        mWebManager.get(Constants.HOST+Constants.SERIALNUMUPDATE,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ResultBean bean = gson.fromJson(json, ResultBean.class);
					if(Integer.valueOf(bean.getState()) == 1){
						mIAcountManager.uupdateSerialView(bean);	
					}else{
						WindowsToast.makeText(mContext, bean.getInfo()).show();
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	public void uploadAccountFile(String usrimg,String serialnumid){
		
		RequestParams params = new RequestParams();  
        try {
			params.put("usrimg", new File(usrimg));
			params.put("serialnumid", serialnumid);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}   
       
        mWebManager.post(Constants.HOST+Constants.DOUPLOAD,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					DeviceInfoBean bean = gson.fromJson(json, DeviceInfoBean.class);
					mIAcountManager.deviceInfoPhoto(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	public void numUsrRelation(String serialnumid){
		RequestParams params = new RequestParams();  
        params.put("serialnumid", serialnumid);   
        
        mWebManager.get(Constants.HOST+Constants.NUMUSRRELATION,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					NumUsrRelation bean = gson.fromJson(json, NumUsrRelation.class);
					if(bean.getState() == 2){
						mIAcountManager.actionSerialView(bean);
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	public void getSerialNum(String serialNumber){
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        
        mWebManager.get(Constants.HOST+Constants.GETSERIALNUM,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					SerialNumberComponment bean = gson.fromJson(json, SerialNumberComponment.class);
					if(bean != null && bean.getData() != null){
						mIAcountManager.getSerialNum(bean);
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	
	public void register(Context context,String serialNumber,String userName,String pwd,String phone){
		
		RequestParams params = new RequestParams();  
        params.put("serialNumber", serialNumber);   
        params.put("userName", userName);
        params.put("password", pwd);
        params.put("phone", phone);
        
        mWebManager.get(Constants.HOST+Constants.REG,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					ResultBean bean = gson.fromJson(json, ResultBean.class);
					mIAcountManager.registerSuccess(bean);
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
					 if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().netWorkError("");
					 }
				}  
	           
	     });  
	}
	
	
	private IAccountManager mIAcountManager ;

	public IAccountManager getmIAcountManager() {
		return mIAcountManager;
	}

	public void setmIAcountManager(IAccountManager mIAcountManager) {
		this.mIAcountManager = mIAcountManager;
	}

	public void login(Context context,String pwd,String phone){
		
		RequestParams params = new RequestParams();  
        params.put("password", pwd);
        params.put("phone", phone);
        
        mWebManager.get(Constants.HOST+Constants.LOGION,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					if(mWebManager.getmListenerNetWork() != null){
						mWebManager.getmListenerNetWork().startNetWorkRequest("");
					}
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					UserInfoBean bean = gson.fromJson(json, UserInfoBean.class);
					if(bean.getState() == 1){
						mIAcountManager.loginSuccess(bean);
					}else{
						WindowsToast.makeText(mContext, bean.getInfo()).show();
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });  
	}
	
	
	
	public void searchLoaUsrid(Context context,String usrid){
		
		RequestParams params = new RequestParams();  
        params.put("usrid", usrid);
        
        mWebManager.get(Constants.HOST+Constants.SEARCHLOAUSRID,params,new AsyncHttpResponseHandler() {
			
				@Override
				public void onStart() {
					super.onStart();
					
				}

				@Override
				public void onSuccess(int statusCode, Header[] headers,byte[] responseBody) {
					String json = new String(responseBody) ;
					Gson gson = new Gson();
					LocationComponment bean = gson.fromJson(json, LocationComponment.class);
					if(bean.getState() == 1){
						mIAcountManager.searchLoaUsrid(bean);
					}else{
						WindowsToast.makeText(mContext, bean.getInfo()).show();
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers,byte[] responseBody, Throwable error) {
					 error.printStackTrace(System.out);
				}  
	           
	     });
	}
	
	
}
