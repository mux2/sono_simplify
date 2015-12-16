package com.lashou.www.fragment;

import java.io.File;

import cn.bmob.v3.BmobUser;

import com.sono.www.R;
import com.lashou.www.activity.MyAccountManagerActivity;
import com.lashou.www.utils.CustomDialog;
import com.lashou.www.utils.CustomDialog.onConfirmListener;
import com.lashou.www.utils.SettingUtils;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.FileUtils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Fragment4 extends Fragment implements OnClickListener
{
	
	private TextView cache_size;
	private File externalCacheFile;
	private File cacheFile;
	private CheckBox cb_switch;
	private CheckBox cb_remind_switch;

	public Fragment4()
	{
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view =null;
		if(view ==null){
			
			view = inflater.inflate(R.layout.setting_layout, container, false);
			
			initUI(view);
		}
		return view;
	}

	private void initUI(View view)
	{
		TextView title_tv = (TextView) view.findViewById(R.id.title_tv);
		title_tv.setText("更多");
		//wifi
		cb_switch = (CheckBox) view.findViewById(R.id.cb_switch);
		boolean onlyWifi = SettingUtils.readOnlyWifi(getContext());
		cb_switch.setChecked(onlyWifi);
		cb_switch.setOnClickListener(this);
		//wifi
		RelativeLayout image_settings_layout = (RelativeLayout) view.findViewById(R.id.image_settings_layout);
		image_settings_layout.setOnClickListener(this);
		
		//msg TODO
		cb_remind_switch = (CheckBox) view.findViewById(R.id.cb_remind_switch);
		boolean isPushMsg=SettingUtils.readPushMsg(getContext());
		cb_remind_switch.setChecked(isPushMsg);
		cb_remind_switch.setOnClickListener(this);
		//msg
		View set_remind_layout = view.findViewById(R.id.set_remind_layout);
		set_remind_layout.setOnClickListener(this);
	
		RelativeLayout clear_cache_layout = (RelativeLayout) view.findViewById(R.id.clear_cache_layout);
		clear_cache_layout.setOnClickListener(this);
		cache_size = (TextView) view.findViewById(R.id.cache_size);
		String size = queryCache();
		cache_size.setText(size);
		
		//客服
		View kefu_layout = view.findViewById(R.id.kefu_layout);
		kefu_layout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			 
			case R.id.clear_cache_layout:
				String content="缓存文件可以用来帮您节省流量，但较大时会占用较多磁盘空间。\n确定开始清理吗？";
				CustomDialog dialog = new CustomDialog(content);
				dialog.setOnConfirmListener(new onConfirmListener() {
							
				@Override
				public void onConfirm() {
					FileUtils.delFilesFromPath(externalCacheFile);
					FileUtils.delFilesFromPath(cacheFile);
					String size = queryCache();
					cache_size.setText(size);
					}
				});
				dialog.show(getChildFragmentManager(), null);
			
				break;
				//wifi
			case R.id.cb_switch:
				boolean checked = cb_switch.isChecked();
				SettingUtils.saveOnlyWifi(getContext(),checked);
				break;
				//wifi
			case R.id.image_settings_layout:
				boolean checked2 = !cb_switch.isChecked();
				cb_switch.setChecked(checked2);
				SettingUtils.saveOnlyWifi(getContext(),checked2);
				break;
				//msg TODO
			case R.id.cb_remind_switch:
				boolean checked3 = cb_remind_switch.isChecked();
				SettingUtils.savePushMsg(getContext(),checked3);
				break;
				//msg
			case R.id.set_remind_layout:
				boolean checked4 = !cb_remind_switch.isChecked();
				cb_remind_switch.setChecked(checked4);
				SettingUtils.savePushMsg(getContext(),checked4);
				break;	
			case R.id.kefu_layout:
				Intent intent2=new Intent();
				//交出拨号程序
 				intent2.setAction(Intent.ACTION_DIAL);
				//直接打出去
// 				intent2.setAction(Intent.ACTION_CALL);
				String mobile="4000-517-317";
				intent2.setData(Uri.parse("tel:"+mobile));
				startActivity(intent2);
				break;
			
			default:
				break;
		}
		
	}
	
	private String queryCache()
	{
		externalCacheFile = getActivity().getExternalCacheDir();
		cacheFile = getActivity().getCacheDir();
		 long fileLen1 = FileUtils.getFileLen(externalCacheFile);
		 long fileLen2 = FileUtils.getFileLen(cacheFile);
		 long fileLen=fileLen1+fileLen2;
		 String size = FileUtils.size(fileLen);
		return size;
	}
	 
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("Fragment4"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Fragment4"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
//		MobclickAgent.onPause(this);
	}
}
