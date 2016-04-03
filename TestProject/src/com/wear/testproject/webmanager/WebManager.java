/**
 * 项目名称：PublcNumbe
r
 * 文件名：WebManager.java 
 * 2015-1-12-下午12:00:47
 * 2015 万家恒通公司-版权所有
 * @version 1.0.0
 */
package com.wear.testproject.webmanager;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.wear.testproject.impl.IListenerNetWorkStatus;

/**
 * 
 * @description: 管理帖子浏览接口
 * 
 *  author : liujw
 *  modify : 2015-1-12 下午12:00:47
 * 
 */
public class WebManager {

	private static WebManager mInstance;

	private Context mContext;
	
	private static AsyncHttpClient client = new AsyncHttpClient(); 
	
	static {
		client.setTimeout(11000); 
	}

	private WebManager() {
		super();
	}
	

	public void post(String urlString,RequestParams params,AsyncHttpResponseHandler res){
		client.post(urlString, params,res);
	}
	
	public synchronized static WebManager getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new WebManager();
		}
		return mInstance;
	}
	
	private IListenerNetWorkStatus mListenerNetWork;
	
	public IListenerNetWorkStatus getmListenerNetWork() {
		return mListenerNetWork;
	}

	public void setmListenerNetWork(IListenerNetWorkStatus mListenerNetWork) {
		this.mListenerNetWork = mListenerNetWork;
	}
	
	public AsyncHttpClient getClient() {
		return client;
	}

	// 用一个完整url获取一个string对象
	public void get(String urlString, AsyncHttpResponseHandler res){
		client.get(urlString, res);
	}
	
	// url里面带参数
	public void get(String urlString, RequestParams params,AsyncHttpResponseHandler res){
		client.get(urlString, params, res);
	}
	
	// 不带参数，获取json对象或者数组
	public void get(String urlString, JsonHttpResponseHandler res){
		client.get(urlString, res);
	}
	
	// 带参数，获取json对象或者数组
	public void get(String urlString, RequestParams params,JsonHttpResponseHandler res){
		client.get(urlString, params, res);
	}
	// 下载数据使用，会返回byte数据
	public void get(String uString, BinaryHttpResponseHandler bHandler){
		client.get(uString, bHandler);
	}
}
