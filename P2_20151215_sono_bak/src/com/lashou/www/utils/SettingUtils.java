package com.lashou.www.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SettingUtils
{
	public static void saveOnlyWifi(Context context, boolean checked){
		 SharedPreferences sp = context.getSharedPreferences(Constants.FILENAME.SETTINGS, 0);
		 Editor edit = sp.edit();
		 edit.putBoolean(Constants.FILENAME.ONLYWIFI, checked);
		 edit.commit();
	}
	public static boolean readOnlyWifi(Context context){
		SharedPreferences sp = context.getSharedPreferences(Constants.FILENAME.SETTINGS, 0);
		 
		boolean b = sp.getBoolean(Constants.FILENAME.ONLYWIFI, false);
		return b;
	}
	public static void savePushMsg(Context context, boolean checked){
		SharedPreferences sp = context.getSharedPreferences(Constants.FILENAME.SETTINGS, 0);
		Editor edit = sp.edit();
		edit.putBoolean(Constants.FILENAME.PUSHMSG, checked);
		edit.commit();
	}
	public static boolean readPushMsg(Context context){
		SharedPreferences sp = context.getSharedPreferences(Constants.FILENAME.SETTINGS, 0);
		
		boolean b = sp.getBoolean(Constants.FILENAME.PUSHMSG, true);
		return b;
	}
}
