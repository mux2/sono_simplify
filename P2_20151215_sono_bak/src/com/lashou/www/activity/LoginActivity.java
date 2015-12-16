package com.lashou.www.activity;

import static cn.smssdk.framework.utils.R.getStringRes;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.sono.www.R;
import com.lashou.www.fragment.Fragment1;
import com.lashou.www.fragment.Fragment2;
import com.lashou.www.fragment.Fragment3;
import com.lashou.www.fragment.Fragment4;
import com.lashou.www.model.MyUser;
import com.lashou.www.utils.Constants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.sina.weibo.sdk.exception.WeiboException;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.RegexValidateUtil;
import com.xinbo.utils.TextViewUtils;
import com.xinbo.utils.ToastUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener,
		OnCheckedChangeListener
{
	
	private LinearLayout ll_indicator;
	private Button quick_login_btn;
	private Button login_btn;
	private LinearLayout ll_quick_login;
	private LinearLayout ll_login;
	private EditText et_username;
	private EditText et_password;
	private EditText et_quick_phone;
	private EditText et_quick_code;
	private boolean hasUserName;
	private boolean hasPassword;
	private boolean quickHasPhone;
	private boolean quickHasCode;
	private ImageView iv_delete_uname;
	private ImageView iv_delete_mobile;
	private Button bt_get_code;
	private String username;
	private String password;
	private TimeCount time;
	
	 private AuthInfo mAuthInfo;
	    
	    /** 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能  */
	    private Oauth2AccessToken mAccessToken;

	    /** 注意：SsoHandler 仅当 SDK 支持 SSO 时有效 */
	    private SsoHandler mSsoHandler;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_view);
		TextView tv_register = (TextView) findViewById(R.id.tv_register);
		tv_register.setOnClickListener(this);
		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		ll_indicator = (LinearLayout) findViewById(R.id.ll_indicator);
		RadioGroup rg1 = (RadioGroup) findViewById(R.id.rg1);
		rg1.setOnCheckedChangeListener(this);
		// 快速登陆按钮
		quick_login_btn = (Button) findViewById(R.id.quick_login_btn);
		quick_login_btn.setOnClickListener(this);
		et_quick_phone = (EditText) findViewById(R.id.et_quick_phone);
		et_quick_code = (EditText) findViewById(R.id.et_quick_code);
		// 普通登陆按钮
		login_btn = (Button) findViewById(R.id.login_btn);
		login_btn.setOnClickListener(this);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		// 明文开关
		CheckBox cb_show_pwd = (CheckBox) findViewById(R.id.cb_show_pwd);
		// 清空按钮
		iv_delete_uname = (ImageView) findViewById(R.id.iv_delete_uname);
		iv_delete_uname.setOnClickListener(this);
		iv_delete_mobile = (ImageView) findViewById(R.id.iv_delete_mobile);
		iv_delete_mobile.setOnClickListener(this);
		// Tab切换
		ll_quick_login = (LinearLayout) findViewById(R.id.ll_quick_login);
		ll_login = (LinearLayout) findViewById(R.id.ll_login);
		// 获取验证码按钮
		time = new TimeCount(60000, 1000);
		bt_get_code = (Button) findViewById(R.id.bt_get_code);
		bt_get_code.setOnClickListener(this);
		//新浪图标
		TextView sina_weibo = (TextView) findViewById(R.id.sina_weibo);
		sina_weibo.setOnClickListener(this);
		 mAuthInfo = new AuthInfo(this, Constants.WEIBO.APP_KEY, Constants.WEIBO.REDIRECT_URL, Constants.WEIBO.SCOPE);
	        mSsoHandler = new SsoHandler(LoginActivity.this, mAuthInfo);
		// 初始化SMS
		initSMS();
		// 监听用户名输入
		et_username.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				
				if ("".equals(s.toString()))
				{
					iv_delete_uname.setVisibility(View.INVISIBLE);
					hasUserName = false;
				} else
				{
					iv_delete_uname.setVisibility(View.VISIBLE);
					hasUserName = true;
				}
				login_btn_enable();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
			}
		});
		et_password.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				if ("".equals(s.toString()))
				{
					hasPassword = false;
				} else
				{
					hasPassword = true;
				}
				login_btn_enable();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
			}
		});
		// 监听手机号输入
		et_quick_phone.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				
				if ("".equals(s.toString()))
				{
					iv_delete_mobile.setVisibility(View.INVISIBLE);
					quickHasPhone = false;
				} else
				{
					iv_delete_mobile.setVisibility(View.VISIBLE);
					quickHasPhone = true;
				}
				quick_login_btn_enable();
				/*
				 * if ( RegexValidateUtil.checkMobileNumber(s.toString())) {
				 * bt_get_code.setEnabled(true);
				 * 
				 * }else{ bt_get_code.setEnabled(false); }
				 */
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
			}
		});
		
		et_quick_code.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				if ("".equals(s.toString()))
				{
					quickHasCode = false;
				} else
				{
					quickHasCode = true;
				}
				quick_login_btn_enable();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
			}
		});
		
		cb_show_pwd
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
				{
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked)
					{
						if (isChecked)
						{
							et_password
									.setTransformationMethod(HideReturnsTransformationMethod
											.getInstance()); // 密码以明文显示
						} else
						{
							et_password
									.setTransformationMethod(PasswordTransformationMethod
											.getInstance()); // 密码以密文显示
						}
						
					}
				});
	}
	
	/**
	 * @describe 判断普通登陆按钮是否可用
	 * @author mux2
	 * @date 2015年12月6日 下午1:12:20
	 */
	private void login_btn_enable()
	{
		if (hasPassword && hasUserName)
		{
			login_btn.setEnabled(true);
		} else
		{
			login_btn.setEnabled(false);
		}
	}
	
	private void quick_login_btn_enable()
	{
		if (quickHasPhone && quickHasCode)
		{
			quick_login_btn.setEnabled(true);
		} else
		{
			quick_login_btn.setEnabled(false);
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.tv_register:
				Intent intent = new Intent(this, RegisterActivity.class);
				startActivity(intent);
				
				break;
			case R.id.iv_back:
				finish();
				break;
			case R.id.iv_delete_uname:
				et_username.setText("");
				break;
			case R.id.iv_delete_mobile:
				et_quick_phone.setText("");
				break;
		 
			case R.id.login_btn:
				username = et_username.getText().toString();
				password = et_password.getText().toString();
				// 判断是否输入
				if ("".equals(username))
				{
					et_username.setError("请输入用户名");
					return;
				}
				if ("".equals(password))
				{
					et_password.setError("请输入密码");
					return;
				}
				// 校验用户名是否合法
				if (!RegexValidateUtil.checkCharacter(username)
						&& !RegexValidateUtil.checkEmail(username)
						&& !RegexValidateUtil.checkMobileNumber(username))
				{
					et_username.setError("用户名不合法");
					return;
				}
				// 校验密码是否合法
				if (!RegexValidateUtil.checkCharacter(password))
				{
					et_password.setError("请输入4-16位密码");
					return;
				}
				btnSignIn();
				break;
			case R.id.quick_login_btn:
				String phone = et_quick_phone.getText().toString();
				String code = et_quick_code.getText().toString();
				// 判断是否输入
				if ("".equals(phone))
				{
					et_quick_phone.setError("请输入手机号");
					return;
				}
				if ("".equals(code))
				{
					et_quick_code.setError("请输入验证码");
					return;
				}
				// 校验手机号是否合法
				if (!RegexValidateUtil.checkMobileNumber(phone))
				{
					et_quick_phone.setError("手机号格式不对");
					return;
				}
				// 校验密码是否合法
				if (!RegexValidateUtil.checkCharacter(code))
				{
					et_quick_code.setError("请输入4位验证码");
					return;
				}
				
				SMSSDK.submitVerificationCode("86", phone, code);
				
				break;
			// 获取验证码 按钮
			case R.id.bt_get_code:
				String phone2 = et_quick_phone.getText().toString();
				if (!RegexValidateUtil.checkMobileNumber(phone2))
				{
					et_quick_phone.setError("请输入正确的手机号");
					return;
				}
				
				SMSSDK.getVerificationCode("86", phone2);
				
				// 倒计时
				time.start();
				break;
				
			case R.id.sina_weibo:
				 mSsoHandler.authorize(new AuthListener());
				break;
			
			default:
				break;
		}
		
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		switch (checkedId)
		{
			case R.id.tv_phone_register:
				Animation animation = AnimationUtils.loadAnimation(this,
						R.anim.anim_translate2);
				animation.setFillAfter(true);
				ll_indicator.startAnimation(animation);
				ll_quick_login.setVisibility(View.VISIBLE);
				quick_login_btn.setVisibility(View.VISIBLE);
				ll_login.setVisibility(View.GONE);
				ll_login.setVisibility(View.GONE);
				login_btn.setVisibility(View.GONE);
				break;
			case R.id.tv_email_register:
				Animation animation2 = AnimationUtils.loadAnimation(this,
						R.anim.anim_translate1);
				animation2.setFillAfter(true);
				ll_indicator.startAnimation(animation2);
				ll_login.setVisibility(View.VISIBLE);
				ll_login.setVisibility(View.VISIBLE);
				login_btn.setVisibility(View.VISIBLE);
				ll_quick_login.setVisibility(View.GONE);
				quick_login_btn.setVisibility(View.GONE);
				break;
			
			default:
				break;
		}
		
	}
	
	class TimeCount extends CountDownTimer
	{
		
		public TimeCount(long millisInFuture, long countDownInterval)
		{
			super(millisInFuture, countDownInterval);
		}
		
		@Override
		public void onTick(long millisUntilFinished)
		{
			bt_get_code.setEnabled(false);
			bt_get_code.setText(millisUntilFinished / 1000 + "秒");
		}
		
		@Override
		public void onFinish()
		{
			bt_get_code.setText("重新获取");
			bt_get_code.setEnabled(true);
			
		}
	}
	
	private Handler handler = new Handler()
	{
		
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			// Log.e("event", "event=" + event);
			if (result == SMSSDK.RESULT_COMPLETE)
			{
				// 短信注册成功后，返回MainActivity,然后提示新好友
				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
				{// 提交验证码成功
					Toast.makeText(getApplicationContext(), "提交验证码成功",
							Toast.LENGTH_SHORT).show();
					// TODO 验证码成功之后继续检验账号密码
					
					// textView2.setText("提交验证码成功");
				} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE)
				{
					Toast.makeText(getApplicationContext(), "验证码已经发送",
							Toast.LENGTH_SHORT).show();
					// textView2.setText("验证码已经发送");
				}
			} else
			{
				((Throwable) data).printStackTrace();
				int resId = getStringRes(LoginActivity.this,
						"smssdk_network_error");
				Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT)
						.show();
				if (resId > 0)
				{
					Toast.makeText(LoginActivity.this, resId,
							Toast.LENGTH_SHORT).show();
				}
			}
			
		}
		
	};

	
	private void initSMS()
	{
		SMSSDK.initSDK(this, Constants.KEY.APPKEY, Constants.KEY.APPSECRET);
		EventHandler eh = new EventHandler()
		{
			
			@Override
			public void afterEvent(int event, int result, Object data)
			{
				
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
			
		};
		SMSSDK.registerEventHandler(eh);
	}
	
	// Bomb登陆验证
	public void btnSignIn()
	{
		final MyUser user = new MyUser();
		user.setUsername(username);
		user.setPassword(password);
		user.login(this, new SaveListener()
		{
			@Override
			public void onSuccess()
			{
				Toast.makeText(LoginActivity.this,
						"登录成功：" + user.getObjectId(), Toast.LENGTH_SHORT)
						.show();
				//TODO
				  AccessTokenKeeper.clear(getApplicationContext());
				finish();
			}
			
			@Override
			public void onFailure(int code, String msg)
			{
				
				Toast.makeText(LoginActivity.this,
						"登录失败：" + msg + "(" + code + ")", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}
	/**
	 * 从这边开始微博api
	 */
	 /**
     * 当 SSO 授权 Activity 退出时，该函数被调用。
     * 
     * @see {@link Activity#onActivityResult}
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        // SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        
    }
	/**
     * 微博认证授权回调类。
     * 1. SSO 授权时，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack} 后，
     *    该回调才会被执行。
     * 2. 非 SSO 授权时，当授权结束后，该回调就会被执行。
     * 当授权成功后，请保存该 access_token、expires_in、uid 等信息到 SharedPreferences 中。
     */
    class AuthListener implements WeiboAuthListener {
        
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            //从这里获取用户输入的 电话号码信息 
            String  phoneNum =  mAccessToken.getPhoneNum();
            if (mAccessToken.isSessionValid()) {
                // 显示 Token
//                updateTokenView(false);
           
                // 保存 Token 到 SharedPreferences
                AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                Toast.makeText(LoginActivity.this, 
                        R.string.weibosdk_demo_toast_auth_success, Toast.LENGTH_SHORT).show();
             	//TODO
                BmobUser.logOut(LoginActivity.this);
                finish();
            } else {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = values.getString("code");
                String message = getString(R.string.weibosdk_demo_toast_auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(LoginActivity.this, 
                   R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LoginActivity.this, 
                    "Auth exception : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("LoginActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("LoginActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}
