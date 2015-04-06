package com.r2apps.sfa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class LoginActivity extends Activity implements OnClickListener {

    Button login;
    EditText username, password;
    private RelativeLayout rlHeader;
    private LinearLayout llLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);

        login = (Button) findViewById(R.id.btnlogin);
        username = (EditText) findViewById(R.id.txtlogin);
        password = (EditText) findViewById(R.id.txtpassword);
        rlHeader = (RelativeLayout) findViewById(R.id.rl_root_header_login);
        llLogin = (LinearLayout) findViewById(R.id.ll_login);

        password.setText("admin");
        login.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(username.getWindowToken(), 0);
        login.setOnClickListener(this);

        setTitle("");

    }


    @Override
    public void onClick(View v) {
        hideSoftKeypad(v);

        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        this.finish();
    }


    @Override
    protected void onResume() {
        hideSoftKeypad(username);
        super.onResume();
    }

    private void hideSoftKeypad(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }
}
