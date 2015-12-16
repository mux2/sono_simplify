package com.lashou.www.utils;

import android.content.Context;

import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

public class APIClient {
	public static void requestCainixihuan(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.URL_CAINIXIHUAN, listener);
	}
	public static void requestHeaderType(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.URL_HEADER_TYPE, listener);
	}
	public static void requestHotfilm(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.URL_HOTFILM, listener);
	} 
	public static void requestHeaderBanner(Context context,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.URL_HEADER_BANNER, listener);
	} 
	public static void getCities(Context context, VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.CITIES, listener);
	}
	public static void getDetail1(Context context, VolleyListener listener) {
		HTTPUtils.get(context, Constants.URL.DETAIL1, listener);
	}
	
	//weibo
	public static void requestWeiboUser(Context context,String access_token,String uid,VolleyListener listener){
		HTTPUtils.get(context, Constants.URL.URL_WEIBOUSER+"access_token="+access_token+"&uid="+uid, listener);
	}
	
	
}
