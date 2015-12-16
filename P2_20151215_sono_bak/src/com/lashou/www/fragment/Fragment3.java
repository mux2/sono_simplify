package com.lashou.www.fragment;

import java.util.List;

import com.android.volley.VolleyError;
import com.sono.www.R;
import com.lashou.www.activity.LoginActivity;
import com.lashou.www.activity.MyAccountManagerActivity;
import com.lashou.www.model.Apiweibo;
import com.lashou.www.model.Cainixihuan;
import com.lashou.www.model.Goodlist;
import com.lashou.www.model.MyUser;
import com.lashou.www.utils.APIClient;
import com.lashou.www.utils.ImageUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import de.hdodenhof.circleimageview.CircleImageView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Fragment3 extends Fragment implements OnClickListener
{
	
	private RelativeLayout rl_unlogin;
	private RelativeLayout rl_logined;
	private TextView tv_name;
	 /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
    private Oauth2AccessToken mAccessToken;
	private CircleImageView iv_user;
	public Fragment3()
	{
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view=null;
		if(view==null){
			
			view = inflater.inflate(R.layout.fragment_fragment3, container, false);
			initUI(view);
			
			btnGetUser();
		}
		return view;
		
	}

	/**
	 * @describe  TODO
	 * @author mux2
	 * @date 2015年12月11日 上午12:38:04
	 * @param view
	 */
	private void initUI(View view)
	{
		Button bt_login = (Button) view.findViewById(R.id.bt_login);
		bt_login.setOnClickListener(this);
		
		rl_unlogin = (RelativeLayout) view.findViewById(R.id.rl_unlogin);
		rl_logined = (RelativeLayout) view.findViewById(R.id.rl_logined);
		rl_logined.setOnClickListener(this);
		tv_name = (TextView) view.findViewById(R.id.tv_name);
		iv_user = (CircleImageView) view.findViewById(R.id.iv_user);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.bt_login:
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
				break;
			case R.id.rl_logined:
				Intent intent2 = new Intent(getActivity(), MyAccountManagerActivity.class);
				startActivity(intent2);
				break;
			
			default:
				break;
		}
		
	}
	
	
	private void btnGetUser()
	{
		MyUser myUser = MyUser.getCurrentUser(getContext(), MyUser.class);
		mAccessToken = AccessTokenKeeper.readAccessToken(getContext());
		String uid = mAccessToken.getUid();
 		String token = mAccessToken.getToken();
		if (myUser == null&&"".equals(uid)) {
			// 显示未登录UI
			rl_logined.setVisibility(View.GONE);
			rl_unlogin.setVisibility(View.VISIBLE);
			
		} else if(myUser != null){
			// 显示已登录UI
			rl_logined.setVisibility(View.VISIBLE);
			rl_unlogin.setVisibility(View.GONE);
			tv_name.setText(myUser.getUsername());
			 iv_user.setBackgroundResource(R.drawable.user_head);
			 iv_user.setImageDrawable(null);
		}else if(!"".equals(uid)){
			rl_logined.setVisibility(View.VISIBLE);
			rl_unlogin.setVisibility(View.GONE);
			APIClient.requestWeiboUser(getActivity(),token,uid,
					new VolleyListener()
					{
						
						@SuppressLint("NewApi")
						@Override
						public void onResponse(String arg0)
						{
							  Apiweibo json = GsonUtils.parseJSON(arg0, Apiweibo.class);
							  tv_name.setText(json.getName());
							  iv_user.setBackground(null);
							 
							  ImageUtils.displayImage(getContext(),json.getProfileImageUrl(),  iv_user);
							 
							
						}
						
						@Override
						public void onErrorResponse(VolleyError arg0)
						{
							
						}
					});
		}
		
	}
	
	/*public void onResume()
	{
		btnGetUser() ;
		super.onResume();
	}*/
	@Override
	public void onResume() {
		super.onResume();
		btnGetUser() ;
		MobclickAgent.onPageStart("Fragment3"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Fragment3"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
//		MobclickAgent.onPause(this);
	}
}
