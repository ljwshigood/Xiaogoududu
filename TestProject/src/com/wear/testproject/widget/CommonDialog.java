package com.wear.testproject.widget;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.wear.testproject.R;
import com.wear.testproject.utils.WindowsTools;

public class CommonDialog {
	
	/**
	 * 公共弹窗
	 * 
	 * @param context
	 *            :Context 传入当前调用该方法的activity实例
	 * @param msg
	 *            :String 要显示的显示文字
	 * @param type
	 *            :int 显示类型1：仅为确定，2：有“确定”、“取消”两个操作
	 * @param mHandlerFilter
	 *            :Handler 传入的需要回调的handler信息，可作为回调方法是用，msg.what = 1时为操作完成状态符
	 */
	private int mType ;
	
	private Dialog mDialog ;
	
	private int mSex ;
	
	public int getmSex() {
		return mSex;
	}

	public void setmSex(int mSex) {
		this.mSex = mSex;
	}
	
	private String mTime ;

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	
	public static CommonDialog mInstance ;
	
	private Context mContext ;
	
	private CommonDialog(Context context){
		this.mContext = context ;
	}
	
	public static CommonDialog getInstance(Context context){
		if(mInstance == null){
			mInstance = new CommonDialog(context);
		}
		return mInstance;
	}
	
	public void showDialog(Activity context , int type, String hint ,int isNickName,final Handler handler){
		boolean isFinish = ((Activity)context).isFinishing() ;
		if(isFinish){
			return ;
		}
		mDialog = new Dialog(context, R.style.dialog_style);
		mDialog.setCancelable(true);
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.global_dialog, null);
		LinearLayout mLLDate = (LinearLayout)view.findViewById(R.id.ll_date);
		LinearLayout mLLEditor = (LinearLayout)view.findViewById(R.id.ll_editor);
		LinearLayout mLLRadioGroup = (LinearLayout)view.findViewById(R.id.ll_sex);
		
		final EditText etText = (EditText)view.findViewById(R.id.et_text);
		if(hint != null){
			etText.setHint(hint);
			if(isNickName == 0){
				 etText.setInputType(InputType.TYPE_CLASS_TEXT);
			}else{
				 etText.setInputType(InputType.TYPE_CLASS_NUMBER);
			}
		}
		
		RadioGroup rb = (RadioGroup)view.findViewById(R.id.radioGroup);
		
		LinearLayout llConfirm = (LinearLayout) view.findViewById(R.id.ll_ok);
		LinearLayout llCancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
		
		Window dialogWindow = mDialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.width = (int) (context.getResources().getDisplayMetrics().density*288);
		dialogWindow.setAttributes(lp);
		mType = type ;
		if(type == 0){ // Date 
			initData(view,context,mTime);
			mLLDate.setVisibility(View.VISIBLE);
			mLLEditor.setVisibility(View.GONE);
			mLLRadioGroup.setVisibility(View.GONE);
		}else if(type == 1){ // sex
			mLLDate.setVisibility(View.GONE);
			mLLEditor.setVisibility(View.GONE);
			mLLRadioGroup.setVisibility(View.VISIBLE);
		}else{
			mLLDate.setVisibility(View.GONE);
			mLLEditor.setVisibility(View.VISIBLE);
			mLLRadioGroup.setVisibility(View.GONE);
		}
		
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				if(checkedId == R.id.radioMale){
					mSex = 0;
				}else if(checkedId == R.id.radioFemale){
					mSex = 1;
				}
			}
		});
		
		llConfirm.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				if(handler != null){
					Message msg = handler.obtainMessage();
					msg.what = 1;
					if(mType == 0){ // Date
						msg.obj = wheelMain.getTime();
					}else if(mType == 1){ // sex
						msg.obj = mSex;
					}else {
						msg.obj = etText.getText().toString().trim();
					}
					
					handler.sendMessage(msg);
				}
				mDialog.dismiss();
			}
		});
		
		llCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mDialog.dismiss();
			}
		});
		
		mDialog.addContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		mDialog.setCancelable(true);
		mDialog.setCanceledOnTouchOutside(true);
		mDialog.show();
	}
	
	private WheelDateMain wheelMain; 
	
	public  boolean isDate(String str_input,String rDateFormat){
		if (!isNull(str_input)) {
	         SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
	         formatter.setLenient(false);
	         try {
	             formatter.format(formatter.parse(str_input));
	         } catch (Exception e) {
	             return false;
	         }
	         return true;
	     }
		return false;
	}
	
	public static boolean isNull(String str){
		if(str == null)
			return true;
		else
			return false;
	}
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	private void initData(View view,Activity context,String time){
		
		wheelMain = new WheelDateMain(view);
		wheelMain.screenheight = WindowsTools.getWindowsHeight(context) * 2 / 2;
		
		Calendar calendar = Calendar.getInstance();
		if(isDate(time, "yyyy-MM-dd")){
			try {
				calendar.setTime(dateFormat.parse(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year,month,day);
	}
	
	public void dismissDialog(){
		if(mDialog != null){
			mDialog.dismiss();
			mDialog = null ;
		}
	}
	
}
