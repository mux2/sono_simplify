package com.lashou.www.utils;

import android.content.Context;
import android.widget.ImageView;

import com.xinbo.utils.ConnectionUtils;
import com.xinbo.utils.UILUtils;

public class ImageUtils
{
	public static void displayImage(Context context, String imageUrl, ImageView imageView1){
		boolean onlyWifi = SettingUtils.readOnlyWifi(context);
		boolean isWifi = ConnectionUtils.isWIFI(context);
		if(!onlyWifi||(onlyWifi&&isWifi)){
			
			UILUtils.displayImage(imageUrl, imageView1);
		}
	}
}
