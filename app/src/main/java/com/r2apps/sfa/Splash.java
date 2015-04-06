package com.r2apps.sfa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class Splash extends Activity{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run() {
				startLogin();
			}
		}, 1500);

	}

	private void startLogin(){
		Intent login = new Intent(this, LoginActivity.class);
		startActivity(login);
		this.finish();
	}
}
