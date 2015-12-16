package com.lashou.www.activity;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.VolleyError;
import com.sono.www.R;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShareEditActivity extends Activity implements OnClickListener
{	private Oauth2AccessToken mAccessToken;
private EditText edt_text_content;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_edit_activity);
		initUI();
	}
	
	private void initUI()
	{
		Button btn_send = (Button) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(this);
		edt_text_content = (EditText) findViewById(R.id.edt_text_content);
		TextView tv_back = (TextView) findViewById(R.id.tv_back);
		tv_back.setOnClickListener(this);
	}

	 
	
	 

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_send:
				weiboShare();
				break;
			case R.id.tv_back:
				finish();
				break;
			default:
				break;
		}
		
	}
	/**
	 * 
	 * @describe  TODO
	 * @author mux2
	 * @date 2015年12月9日 上午12:11:00
	 */
	private void weiboShare()
	{
		String content = edt_text_content.getText().toString();
	 
		
		mAccessToken = AccessTokenKeeper.readAccessToken(this);
		if (!mAccessToken.isSessionValid()) {
			Toast.makeText(this, "请先登录", Toast.LENGTH_LONG).show();
			return;
		}

		
		String token = mAccessToken.getToken();
		String url = "https://api.weibo.com/2/statuses/update.json";
		Map<String, String> params = new HashMap<String, String>();
		params.put("access_token", token);
		params.put("status", content);
		HTTPUtils.post(this, url, params, new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				Toast.makeText(ShareEditActivity.this, "分享成功", Toast.LENGTH_LONG).show();
				finish();
			}

		 

			@Override
			public void onErrorResponse(VolleyError arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("ShareEditActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("ShareEditActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	} 
}
