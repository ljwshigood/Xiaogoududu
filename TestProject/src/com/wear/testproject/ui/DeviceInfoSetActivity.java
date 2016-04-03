package com.wear.testproject.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.client.android.encode.EncodeActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.DeviceInfoBean;
import com.wear.testproject.bean.LocationComponment;
import com.wear.testproject.bean.NumUsrRelation;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.bean.SerialNumberComponment;
import com.wear.testproject.bean.UserInfoBean;
import com.wear.testproject.bean.pushBean;
import com.wear.testproject.impl.IAccountManager;
import com.wear.testproject.impl.IListenerNetWorkStatus;
import com.wear.testproject.sharepefrence.SharePerfenceUtil;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.PictureUtil;
import com.wear.testproject.webmanager.WebAccountManager;
import com.wear.testproject.webmanager.WebManager;
import com.wear.testproject.widget.CommonDialog;
import com.wear.testproject.widget.SelectPicPopupWindow;
import com.wear.testproject.widget.WindowsToast;

public class DeviceInfoSetActivity extends BaseActivity implements OnClickListener,IAccountManager,IListenerNetWorkStatus{
	
	
	private TextView mTvLeft;

	private ImageView mIvLeft;

	private TextView mTvMainInfo;

	private ImageView mIvRight;

	private ImageView mIvPhoto;

	private RelativeLayout mRLPhoto;

	private RelativeLayout mRlNickName;

	private RelativeLayout mRlBirth;

	private RelativeLayout mRlQRCode ;
	
	private RelativeLayout mRlSex;

	private RelativeLayout mRlHeight;

	private RelativeLayout mRlWeight;
	
	private TextView mTvNickName ;
	
	private TextView mTvBirthday ;
	
	private TextView mTvHeight ;
	
	private TextView mTvWeight ;
	
	private TextView mTvSex ;
	
	private TextView mTvRight ;
	
	private SerialNumber mSerialNumber ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_info_set);
		initView();
		getIntentData();
		initData();
		WebManager.getInstance(mContext).setmListenerNetWork(this);
		WebAccountManager.getInstance(mContext).setmIAcountManager(this);
	}
	
	private int mAction ;
	
	private void getIntentData(){
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber)intent.getSerializableExtra("serialNumber");
		mAction = intent.getIntExtra("action", -1);
	}
	
	private void initUserPhotoInfo(String url,ImageView iv){
		ImageLoader.getInstance().displayImage(url, iv,mOptionsStyle, 
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						
					}
	
					@Override
					public void onLoadingFailed(String imageUri, View view,FailReason failReason) {
					}
	
					@Override
					public void onLoadingComplete(String imageUri, View view,Bitmap loadedImage) {
						
					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,int current, int total) {
						
					}
				});
	}
	
	private void initData(){
		
		initUserPhotoInfo(mSerialNumber.getFpicture(),mIvPhoto);
		
		if(mSerialNumber.getNickname() != null){
			mTvNickName.setText(mSerialNumber.getNickname());
		}
		if(mSerialNumber.getFeight() != null){
			mTvHeight.setText(mSerialNumber.getFeight());	
		}
		
		if(mSerialNumber.getFsex() != null){
			if(Integer.valueOf(mSerialNumber.getFsex()) == 0){
				mTvSex.setText("男");
			}else{
				mTvSex.setText("女");
			}
		}
		
		if(mSerialNumber.getBirthday() != null){
			mTvBirthday.setText(mSerialNumber.getBirthday());
		}
		
	}
	
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			Intent intent;
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.ll_capture:
				takePhoto();
				break;
			case R.id.ll_gallery:
				intent = new Intent(mContext, GalleryActivity.class);
				startActivityForResult(intent, Constants.REQUEST_GET_MEDIA);
				break;
			default:
				break;
			}
		}
	};

	
	private SelectPicPopupWindow menuWindow;

	private void showWindows() {
		menuWindow = new SelectPicPopupWindow(DeviceInfoSetActivity.this,
				itemsOnClick);
		menuWindow.showAtLocation(this.findViewById(R.id.frame), Gravity.BOTTOM
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}
	
	private String mCurrentPhotoPath ;
	
	private File createImageFile() throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = format.format(new Date());
		String imageFileName = "sheqing_" + timeStamp + ".jpg";

		File image = new File(PictureUtil.getAlbumDir(), imageFileName);
		mCurrentPhotoPath = image.getAbsolutePath();
		return image;
	}
	
	public void takePhoto() {
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		try {
			File f = createImageFile();
			takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
			startActivityForResult(takePictureIntent,Constants.CAMERA_CAPTURE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String mCurrentFilePath ;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == Constants.UNDO){
			return ;
		}
		if(requestCode == Constants.CAMERA_CAPTURE){
			if (resultCode == Activity.RESULT_OK) {
				PictureUtil.galleryAddPic(this, mCurrentPhotoPath);
			} else {
				PictureUtil.deleteTempFile(mCurrentPhotoPath);
				return ;
			}
			Intent intent = new Intent(mContext,ClipPictureActivity.class);
			intent.putExtra("filePath", mCurrentPhotoPath);
			intent.putExtra("action", 0);
			startActivityForResult(intent,Constants.REQUEST_GET_MEDIA);
		}else if(requestCode == Constants.REQUEST_GET_MEDIA){
			mCurrentFilePath = data.getStringExtra("filePath");
			initUserPhotoInfo("file://"+mCurrentFilePath,mIvPhoto);	
		}
	}
	
	private void initView() {
		mRlQRCode = (RelativeLayout)findViewById(R.id.rl_qrcode);
		mTvRight = (TextView)findViewById(R.id.tv_right);
		mRLPhoto = (RelativeLayout) findViewById(R.id.rl_photo);
		mRlBirth = (RelativeLayout) findViewById(R.id.rl_birth);
		mRlHeight = (RelativeLayout) findViewById(R.id.rl_height);
		mRlSex = (RelativeLayout) findViewById(R.id.rl_sex);
		mRlNickName = (RelativeLayout) findViewById(R.id.rl_nickname);
		mRlWeight = (RelativeLayout) findViewById(R.id.rl_weight);

		mTvNickName = (TextView)findViewById(R.id.tv_nick_name);
		mTvBirthday = (TextView)findViewById(R.id.tv_birthday);
		mTvHeight = (TextView)findViewById(R.id.tv_height);
		mTvWeight = (TextView)findViewById(R.id.tv_weight);
		mTvSex = (TextView)findViewById(R.id.tv_sex);
		
		
		mIvPhoto = (ImageView) findViewById(R.id.iv_photo);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		mIvLeft = (ImageView) findViewById(R.id.iv_back);
		mTvLeft = (TextView) findViewById(R.id.tv_left);
		mTvMainInfo = (TextView) findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.device_info_set));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);

		mRlQRCode.setOnClickListener(this);
		mRLPhoto.setOnClickListener(this);
		mRlBirth.setOnClickListener(this);
		mRlHeight.setOnClickListener(this);
		mRlSex.setOnClickListener(this);
		mRlNickName.setOnClickListener(this);
		mRlWeight.setOnClickListener(this);

		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
		mTvRight.setVisibility(View.VISIBLE);
		mTvRight.setText(mContext.getString(R.string.save));
		mTvRight.setOnClickListener(this);
	}
	
	 private static final String BS_PACKAGE = "com.google.zxing.client.android";
	 
	 private void encodeBarcode(CharSequence type, CharSequence data) {
	    Intent intent = new Intent(mContext, EncodeActivity.class);
	    intent.setAction(BS_PACKAGE + ".ENCODE");
	    intent.putExtra("ENCODE_TYPE", type);
	    intent.putExtra("ENCODE_DATA",data);
		startActivity(intent);
	}
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.rl_qrcode:
			encodeBarcode("TEXT_TYPE",mSerialNumber.getSerialnumber());
			break ;
		case R.id.tv_right :
			if(mCurrentFilePath != null){
				WebAccountManager.getInstance(mContext).uploadAccountFile(mCurrentFilePath,mSerialNumber.getFuniqueid());
			}else{
				WebAccountManager.getInstance(mContext).serialNumUpdate(mSerialNumber);
			}
			break;
		case R.id.tv_left:
			finish();
			break;
		case R.id.rl_nickname:
			String str = mContext.getString(R.string.nick_hint);
			CommonDialog.getInstance(mContext).showDialog(DeviceInfoSetActivity.this, 2, str,0,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						mTvNickName.setText(String.valueOf(String.valueOf(msg.obj)));
						
					}
				};
			});
			break ;
		case R.id.rl_photo:
			showWindows();
			break;
		case R.id.rl_birth:
			CommonDialog.getInstance(mContext).setmTime(mTvBirthday.getText().toString().trim());
			CommonDialog.getInstance(mContext).showDialog(DeviceInfoSetActivity.this, 0 ,null, 1,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						String birthday = (String)msg.obj;
						mSerialNumber.setBirthday(birthday);
					}
				};
			});
			break;
		case R.id.rl_height:
			String str1 = mContext.getString(R.string.height_hint);
			CommonDialog.getInstance(mContext).showDialog(DeviceInfoSetActivity.this, 2 ,str1 , 1,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						mTvHeight.setText(String.valueOf(String.valueOf(msg.obj)));
						mSerialNumber.setFeight(String.valueOf(String.valueOf(msg.obj)));
					}
				};
			});
			break;
		case R.id.rl_weight:
			String str2 = mContext.getString(R.string.weight_hint);
			CommonDialog.getInstance(mContext).showDialog(DeviceInfoSetActivity.this, 2,str2,1, new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						mTvWeight.setText(String.valueOf(String.valueOf(msg.obj)));
						mSerialNumber.setFweight(String.valueOf(String.valueOf(msg.obj)));
					}
				};
			});
			break;
		case R.id.rl_sex:
			CommonDialog.getInstance(mContext).showDialog(DeviceInfoSetActivity.this, 1, null,0,new Handler() {

				public void handleMessage(android.os.Message msg) {
					if(msg.what == 1){
						String sex = String.valueOf(msg.obj);
						if(Integer.valueOf(sex) == 0){
							mTvSex.setText("男");
							mSerialNumber.setFsex("1");
						}else{
							mTvSex.setText("女");
							mSerialNumber.setFsex("0");
						}
					}
				};
			});
			break;
		default:
			break;
		}
		
	}

	@Override
	public void getSerialNum(SerialNumberComponment bean) {
		
	}

	@Override
	public void actionSerialView(NumUsrRelation bean) {
		
	}

	@Override
	public void uupdateSerialView(ResultBean bean) {
		WindowsToast.makeText(mContext, bean.getInfo()).show();
		if(mAction == 0){
			String username = (String) SharePerfenceUtil.getParam(mContext, "userName", "");
			String pwd = (String)SharePerfenceUtil.getParam(mContext, "pwd", "");
			WebAccountManager.getInstance(mContext).setmIAcountManager(this);
			WebAccountManager.getInstance(mContext).login(mContext, pwd, username);
		}
	}

	@Override
	public void loginSuccess(UserInfoBean bean) {
		Intent intent = new Intent(mContext,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("userInfo", bean);
		startActivity(intent);
	}

	@Override
	public void registerSuccess(ResultBean bean) {
		
	}

	@Override
	public void searchLoaUsrid(LocationComponment componment) {
		
	}

	@Override
	public void userUpdate(ResultBean bean) {
		
	}

	@Override
	public void uploadFile(UserInfoBean bean) {
		
	}

	@Override
	public void deviceInfoPhoto(DeviceInfoBean bean) {
		WebAccountManager.getInstance(mContext).serialNumUpdate(mSerialNumber);
	}

	@Override
	public void pushAndroid(pushBean bean) {
		
	}

	@Override
	public void startNetWorkRequest(String message) {
		
	}

	@Override
	public void endNetWorkRequest(String message) {
		
	}

	@Override
	public void netWorkError(String message) {
		SharePerfenceUtil.setParam(mContext, "userName", "");
		SharePerfenceUtil.setParam(mContext, "pwd", "");
	}
	
	
}
