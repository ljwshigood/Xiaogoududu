package com.wear.testproject.widget;

import android.app.Dialog;
import android.content.Context;

import com.wear.testproject.R;

public class CustomProgressDialog extends Dialog {

	public CustomProgressDialog(Context context, String strMessage) {
		this(context, R.style.CustomDialog, strMessage);
	}

	public CustomProgressDialog(Context context, int theme, String strMessage) {
		super(context, theme);
		this.setContentView(R.layout.customprogressdialog);
	}

}
