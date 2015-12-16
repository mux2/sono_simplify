package com.lashou.www.app;

import com.baidu.mapapi.SDKInitializer;
import com.igexin.sdk.PushManager;
import com.lashou.www.utils.SettingUtils;
import com.xinbo.app.BaseApp;

public class SonoApp extends BaseApp
{
	public static String APPID = "4762ef5b5c71acbdae3bc13c7e96c96b";
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
 		SDKInitializer.initialize(this);
 	   
 	      //getui
 		if(SettingUtils.readPushMsg(this)){
 			
 			PushManager.getInstance().initialize(this.getApplicationContext());
 		}
	}
}
