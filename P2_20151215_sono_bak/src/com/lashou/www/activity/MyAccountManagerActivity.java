package com.lashou.www.activity;

import cn.bmob.v3.BmobUser;

import com.sono.www.R;
import com.lashou.www.model.MyUser;
import com.lashou.www.utils.CustomDialog;
import com.lashou.www.utils.CustomDialog.onConfirmListener;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAccountManagerActivity extends FragmentActivity implements OnClickListener
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_my_account_manage);
		Button bt_login_out = (Button) findViewById(R.id.bt_login_out);
		bt_login_out.setOnClickListener(this);
		TextView title_tv = (TextView) findViewById(R.id.title_tv);
		title_tv.setText("个人中心");
		ImageView back_img = (ImageView) findViewById(R.id.back_img);
		back_img.setVisibility(View.VISIBLE);
		back_img.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.back_img:
				finish();
				break;
			case R.id.bt_login_out:
				String content="确定要退出登录吗？";
				CustomDialog dialog = new CustomDialog(content);
				dialog.setOnConfirmListener(new onConfirmListener() {
							
				@Override
				public void onConfirm() {
					MyUser myUser = MyUser.getCurrentUser(MyAccountManagerActivity.this, MyUser.class);
					Oauth2AccessToken	mAccessToken = AccessTokenKeeper.readAccessToken(MyAccountManagerActivity.this);
					if (myUser != null){
						
						BmobUser.logOut(MyAccountManagerActivity.this);
					}else if(!"".equals(mAccessToken.getUid()) ){
						  AccessTokenKeeper.clear(getApplicationContext());
					}else{
						
					}
					
					finish();
					}
				});
				dialog.show(getSupportFragmentManager(), null);
			
				break;
			
			
			default:
				break;
		}
		
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("MyAccountManagerActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("MyAccountManagerActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}
