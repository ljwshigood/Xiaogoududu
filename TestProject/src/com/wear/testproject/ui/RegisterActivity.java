package com.wear.testproject.ui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.AccountInfoBean;
import com.wear.testproject.bean.ResultBean;
import com.wear.testproject.impl.ISendSMSListener;
import com.wear.testproject.sharepefrence.SharePerfenceUtil;
import com.wear.testproject.utils.Constants;
import com.wear.testproject.utils.FormatVerify;
import com.wear.testproject.utils.PictureUtil;
import com.wear.testproject.webmanager.WebCommunicationManager;
import com.wear.testproject.widget.SelectPicPopupWindow;
import com.wear.testproject.widget.WindowsToast;

public class RegisterActivity extends BaseActivity implements OnClickListener ,ISendSMSListener{

	
	private TextView mTvLeft ;
	
	private TextView mTvRight ;
	
	private TextView mTvMainInfo ;
	
	private ImageView mIvBack ;
	
	private ImageView mIvRight ;
	
	private EditText mEtMobile ;
	
	private EditText mEtName ;
	
	private EditText mEtPwd ;
	
	private EditText mEtConfirePwd ;
	
	private EditText mEtValide ;
	
	private LinearLayout mllValideCode ;
	
	private void initView(){
		mEtValide = (EditText)findViewById(R.id.et_valide);
		mllValideCode = (LinearLayout)findViewById(R.id.ll_valide_code);
		mEtMobile = (EditText)findViewById(R.id.et_mobile);
		mEtConfirePwd = (EditText)findViewById(R.id.et_confire_pwd);
		mEtPwd = (EditText)findViewById(R.id.et_pwd);
		mEtName = (EditText)findViewById(R.id.et_name);
		mIvPhoto = (ImageView)findViewById(R.id.iv_photo);
		mRlAddPhoto = (RelativeLayout)findViewById(R.id.rl_photo);
		mIvBack =(ImageView)findViewById(R.id.iv_back);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvRight =(TextView)findViewById(R.id.tv_right);
		
		mIvRight.setVisibility(View.GONE);
		mIvBack.setVisibility(View.GONE);
		mTvLeft.setText(mContext.getString(R.string.cancel));
		mTvRight.setText(mContext.getString(R.string.next_step));
		mTvRight.setOnClickListener(this);
		mRlAddPhoto.setOnClickListener(this);
		mTvLeft.setOnClickListener(this);
		mTvMainInfo.setText(mContext.getString(R.string.reg));
		mllValideCode.setOnClickListener(this);
		
	}
	
	Random random = new Random();
	
	private String randomValide(){
		String sRand = "" ;
		String bb = "012345678901234567890123456789" ;
		for(int i = 0 ;i < 4 ; i++){
			char rand = bb.charAt(random.nextInt(bb.length()));
			sRand += rand ;
		}
		return sRand ;
	}
	
	
	private SelectPicPopupWindow menuWindow ;
	
	private void showWindows() {
		menuWindow = new SelectPicPopupWindow(RegisterActivity.this,itemsOnClick);
		menuWindow.showAtLocation(this.findViewById(R.id.frame), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
			intent.putExtra("action", 0);//0 :camera : 1 : gallery
			startActivityForResult(intent,Constants.REQUEST_GET_MEDIA);
		}else if(requestCode == Constants.REQUEST_GET_MEDIA){
			mCurrentFilePath = data.getStringExtra("filePath");
			initUserPhotoInfo("file://"+mCurrentFilePath,mIvPhoto);	
		}
	}
	
	private String mCurrentFilePath ;
	
	private void initUserPhotoInfo(String url,ImageView iv){
		ImageLoader.getInstance().displayImage(url,iv,mOptionsStyle, 
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

	
	
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			Intent intent ;
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.ll_capture:
				takePhoto();
				break;
			case R.id.ll_gallery:
				intent = new Intent(mContext,GalleryActivity.class);
				startActivityForResult(intent, Constants.REQUEST_GET_MEDIA);
				break ;
			default:
				break;
			}
		}
	};
	
	private RelativeLayout mRlAddPhoto ;
	
	private ImageView mIvPhoto ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initView();
		WebCommunicationManager.getInstance(mContext).setmISendSmsListener(this);
	}


	private boolean  valideData(){
		String mobile = mEtMobile.getText().toString().trim();
		String newPwd = mEtPwd.getText().toString().trim();
		String confirPwd = mEtConfirePwd.getText().toString().trim();
		boolean isMobile = FormatVerify.isMobileNO(mobile);
		boolean ret = true ;
		
		if(!isMobile){
			WindowsToast.makeText(mContext, mContext.getString(R.string.mobile_valide)).show();
			ret = false ;
		}else if(!newPwd.equals(confirPwd)){
			WindowsToast.makeText(mContext, mContext.getString(R.string.pwd_incorrect)).show();
			ret = false ;
		}else if(mEtValide.getText().toString().trim().equals("")){
			WindowsToast.makeText(mContext, mContext.getString(R.string.valide_empty)).show();
			ret = false ;
		}else if(!mEtValide.getText().toString().trim().equals(valide)){
			WindowsToast.makeText(mContext, mContext.getString(R.string.valide_incorrect)).show();
			ret = false ;
		}
		return ret ;
	}
	
	
	private boolean valideCodeData(){
		String mobile = mEtMobile.getText().toString().trim();
		String newPwd = mEtPwd.getText().toString().trim();
		String confirPwd = mEtConfirePwd.getText().toString().trim();
		boolean isMobile = FormatVerify.isMobileNO(mobile);
		boolean ret = true ;
		
		if(!isMobile){
			WindowsToast.makeText(mContext, mContext.getString(R.string.mobile_valide)).show();
			ret = false ;
		}else if(!newPwd.equals(confirPwd)){
			WindowsToast.makeText(mContext, mContext.getString(R.string.pwd_incorrect)).show();
			ret = false ;
		}
		return ret ;
	}
	
	private String valide ;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_right:
			if(valideData()){
				Intent intent = new Intent(mContext,CaptureActivity.class);
				AccountInfoBean account = new AccountInfoBean();
				account.setUsername(mEtMobile.getText().toString().trim());
				account.setPassword(mEtPwd.getText().toString().trim());
				account.setFmobile(mEtMobile.getText().toString().trim());
				intent.putExtra("action", 0);
				intent.putExtra("account", account);
				
				SharePerfenceUtil.setParam(mContext, "userName", mEtMobile.getText().toString().trim());
				SharePerfenceUtil.setParam(mContext, "pwd", mEtPwd.getText().toString().trim());
				
				startActivity(intent);
			}
			break;
		case R.id.tv_left:
			finish();
			break ;
		case R.id.rl_photo:
			showWindows();
			break ;
		case R.id.ll_valide_code :
			if(valideCodeData()){
				valide = randomValide();
				WindowsToast.makeText(mContext, valide).show();
				String mobile = mEtMobile.getText().toString().trim();
				WebCommunicationManager.getInstance(mContext).sendSMS(mobile, "13729", valide+",2");
			}
			break ;
		default:
			break;
		}
	}

	private boolean isValideSuccess = false ;
	
	@Override
	public void sendSMS(ResultBean bean) {
		if(Integer.valueOf(bean.getState()) == 1){
			isValideSuccess = true ;
			WindowsToast.makeText(mContext,mContext.getString(R.string.valide_success)).show();
		}else{
			WindowsToast.makeText(mContext,bean.getInfo()).show();
		}
		
	}
	
}
