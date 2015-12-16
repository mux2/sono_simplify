package com.lashou.www.activity;

 


import com.sono.www.R;
import com.igexin.sdk.PushManager;
import com.lashou.www.fragment.Fragment1;
import com.lashou.www.fragment.Fragment2;
import com.lashou.www.fragment.Fragment3;
import com.lashou.www.fragment.Fragment4;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.TextViewUtils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments, using FragmentTabHost.
 */
public class FragmentTabs extends FragmentActivity {
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_tabs);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        addTabs("首页",R.drawable.ic_tab_artists,Fragment1.class);
        addTabs("周边",R.drawable.ic_tab_albums,Fragment2.class);
        addTabs("我的拉手",R.drawable.ic_tab_songs,Fragment3.class);
        addTabs("更多",R.drawable.ic_tab_playlists,Fragment4.class);
        
     
/*        View view2=getLayoutInflater().inflate(R.layout.tabs_item2, null);
        View view3=getLayoutInflater().inflate(R.layout.tabs_item3, null);
        View view4=getLayoutInflater().inflate(R.layout.tabs_item4, null);
		
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator(view2),
        		Fragment2.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator(view3),
        		Fragment3.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator(view4),
        		Fragment4.class, null);*/
      
    }
    
    public void addTabs(String text, int drawableRes,Class fragmentClass){
    	View view=getLayoutInflater().inflate(R.layout.tabs_item1, null);
    	TextView textView1 = (TextView) view.findViewById(R.id.textView1);
    	textView1.setText(text);
    	TextViewUtils.setTextDrawable(this, drawableRes, textView1);
    	mTabHost.addTab(mTabHost.newTabSpec(text).setIndicator(view),
        		fragmentClass, null);
    }
    public void onResume() {
		super.onResume();
//		MobclickAgent.onPageStart("SplashScreen"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd("SplashScreen"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}

