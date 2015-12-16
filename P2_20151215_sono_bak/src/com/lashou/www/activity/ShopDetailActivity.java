package com.lashou.www.activity;

import com.lashou.www.baidumap.CoveringActivity;
import com.sono.www.R;
import com.sono.www.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ShopDetailActivity extends Activity implements OnClickListener
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_detail);
		initUI();
	}

	private void initUI()
	{
		ImageView back_img = (ImageView) findViewById(R.id.back_img);
		back_img.setOnClickListener(this);
		View ll_shop_detail = findViewById(R.id.ll_shop_detail);
		ll_shop_detail.setOnClickListener(this);
		View ll_call_phone = findViewById(R.id.ll_call_phone);
		ll_call_phone.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.back_img:
				finish();
				break;
				//打开地图
			case R.id.ll_shop_detail:
				Intent intent=new Intent(this,CoveringActivity.class);
				startActivity(intent);
				break;
				//打电话
			case R.id.ll_call_phone:
				Intent intent2=new Intent();
				//交出拨号程序
 				intent2.setAction(Intent.ACTION_DIAL);
				//直接打出去
// 				intent2.setAction(Intent.ACTION_CALL);
				String mobile="13726280916";
				intent2.setData(Uri.parse("tel:"+mobile));
				startActivity(intent2);
				break;
			default:
				break;
		}
	}
	
	 
}
