package com.wear.testproject.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wear.testproject.R;
import com.wear.testproject.bean.AlarmBean;
import com.wear.testproject.bean.SerialNumber;
import com.wear.testproject.utils.PayResult;
import com.wear.testproject.utils.SignUtils;

public class ChangePayActivity extends BaseActivity implements OnClickListener{
	
	private TextView mTvLeft ; 
	
	private ImageView mIvLeft ;
	
	private ImageView mIvRight ;
	
	private TextView mTvMainInfo ;
	
	private LinearLayout mLLPay ;
	
	private SerialNumber mSerialNumber ;

	private void getIntentData() {
		Intent intent = getIntent();
		mSerialNumber = (SerialNumber) intent.getSerializableExtra("serialNumber");
	}
	

	// 商户PID
	public static final String PARTNER = "2088911850320292";
	// 商户收款账号
	public static final String SELLER = "sales@wearme.cc";
	// 商户私钥，pkcs8格式
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANhp9v9YtP5no0tG8spBhQaMW7twzO3sJ+" +
											"c+XDsqIdnhHhQtlZTew+xg3U1K0lCs1TSPb7A1bnS12i6mqTwgsOZX9SyYsohvKNpZ7OELcCroPd8BMVueDLF7HtztHoPqDQZjP8U1xq/HSY4ZyTw915" +
											"QGsurrYGv8BredMytQ296VAgMBAAECgYBqlKVEVFevAzN0m1hvK51sACwdQAxtEGUGibu6l6FcQHYj0d9dFei/vWS/8B1sBfJqlEmZKndfmz/8wa+PITp9D" +
											"pLt9Ot25ZSwvC2gO42ZGBBe+4hP3BvKDUiGWWVoNMvQ8KvbVJNzKGeWBQK1GYdhiP+i8nxZ2xKcap5AIYbBcQJBAPlLaHu4KCBh+pd+0mzNVpgeyPOywAx2gm4" +
											"/M9rogv2MXICI1DDzPYVNCeDBoRm+m43AO7Q+u5hK/EhxZ3FMCcsCQQDePCW0ZYyfBB6KAHB0VI4nHo3qaSpdnyUsxRu/Jzz24H7D/0cOq7oefpto9mTCmT" +
											"9o2npHaaxlibwtjb5rCi0fAkAXXvpsntckMgtMKJeRm66ClGBCeH+Iq5OOwl4aJ7IgEwiZBDrgW+1okdBbQcvYngX3RA5U14y+ukjsgbUw6fABAkEAgle4" +
											"rZOZ5HIgD0SusCwfq+YJymCgDoUW4qlA2t6CrTGCaCZDYOF" +
											"rqhMLx4YsFs6XGEJ70YKzMGKWUgl+k31GdQJAHwT8ISokOQ3orR3Q4faPHlsRJfC3P38WGs3pgJtT3l/yoOhtM9WqSc0uUmT8E+CI5YUrMXzEjcbYHmtm1nWZhA==";
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvy" +
			"Tn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/" +
			"cNMW0QIDAQAB";
	private static final int SDK_PAY_FLAG = 1;

	private static final int SDK_CHECK_FLAG = 2;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);

				// 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
				String resultInfo = payResult.getResult();

				String resultStatus = payResult.getResultStatus();

				// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(ChangePayActivity.this, "支付成功",
							Toast.LENGTH_SHORT).show();
				} else {
					// 判断resultStatus 为非“9000”则代表可能支付失败
					// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
					if (TextUtils.equals(resultStatus, "8000")) {
						Toast.makeText(ChangePayActivity.this, "支付结果确认中",
								Toast.LENGTH_SHORT).show();

					} else {
						// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
						Toast.makeText(ChangePayActivity.this, "支付失败",
								Toast.LENGTH_SHORT).show();

					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				Toast.makeText(ChangePayActivity.this, "检查结果为：" + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
			default:
				break;
			}
		};
	};

	public void pay() {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
				|| TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(this)
					.setTitle("警告")
					.setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialoginterface, int i) {
									//
									finish();
								}
							}).show();
			return;
		}
		// 订单
		String orderInfo = getOrderInfo("小狗嘟嘟", "小狗嘟嘟在线充值", "0.01");

		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(ChangePayActivity.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}
	
	/**
	 * create the order info. 创建订单信息
	 * 
	 */
	public String getOrderInfo(String subject, String body, String price) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
				+ "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pay);
		getIntentData();
		initView();
		initUserPhotoInfo(mSerialNumber.getFpicture(),mIvPay);
	}
	
	private ImageView mIvPay ;
	
	private void initView(){
		mIvPay = (ImageView)findViewById(R.id.iv_pay_on);
		mLLPay = (LinearLayout)findViewById(R.id.ll_change);
		mIvRight = (ImageView)findViewById(R.id.iv_right);
		mIvLeft = (ImageView)findViewById(R.id.iv_back);
		mTvLeft = (TextView)findViewById(R.id.tv_left);
		mTvMainInfo = (TextView)findViewById(R.id.tv_main_title_info);
		mTvMainInfo.setText(mContext.getString(R.string.change_account));
		mTvLeft.setText(mContext.getString(R.string.back));
		mTvLeft.setOnClickListener(this);
		mLLPay.setOnClickListener(this);
		mIvLeft.setVisibility(View.GONE);
		mIvRight.setVisibility(View.GONE);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left:
			finish();
			break;
		case R.id.ll_change:
			pay();
		/*	Intent intent = new Intent(mContext,ChangePayActivity.class);
			startActivity(intent);*/
			break ;
		default:
			break;
		}
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
	
}
