package com.lashou.www.activity;

import static cn.smssdk.framework.utils.R.getStringRes;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.sono.www.R;
import com.lashou.www.activity.LoginActivity.TimeCount;
import com.lashou.www.model.MyUser;
import com.lashou.www.utils.Constants;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.RegexValidateUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener
{
	private Button bt_get_sms_code;
	private TimeCount time;
	private EditText et_phone;
	private EditText et_check_code;
	private EditText et_password;
	private EditText et_repwd;
	private String phone;
	private String code;
	private String password;
	private boolean hasPhone;
	private boolean hasCode;
	private boolean hasPassword;
	private boolean hasRepwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_view);
		ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(this);
		
		// 明文开关
		CheckBox cb_show_pwd = (CheckBox) findViewById(R.id.cb_show_pwd);
		
		iv_delete_mobile = (ImageView) findViewById(R.id.iv_delete_mobile);
		iv_delete_mobile.setOnClickListener(this);
		iv_delete_code = (ImageView) findViewById(R.id.iv_delete_code);
		iv_delete_code.setOnClickListener(this);
		iv_delete_psw = (ImageView) findViewById(R.id.iv_delete_psw);
		iv_delete_psw.setOnClickListener(this);
		iv_delete_repsw = (ImageView) findViewById(R.id.iv_delete_repsw);
		iv_delete_repsw.setOnClickListener(this);
		
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_check_code = (EditText) findViewById(R.id.et_check_code);
		et_password = (EditText) findViewById(R.id.et_password);
		et_repwd = (EditText) findViewById(R.id.et_repwd);
		
		bt_signup = (Button) findViewById(R.id.bt_signup);
		bt_signup.setOnClickListener(this);
		// 获取验证码按钮
		time = new TimeCount(60000, 1000);
		bt_get_sms_code = (Button) findViewById(R.id.bt_get_sms_code);
		bt_get_sms_code.setOnClickListener(this);
		
		// 初始化SMS
		initSMS();
		// 监听 输入
		et_phone.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				
				if ("".equals(s.toString()))
				{
					iv_delete_mobile.setVisibility(View.INVISIBLE);
					
				} else
				{
					iv_delete_mobile.setVisibility(View.VISIBLE);
					
				}
				
				
				if (RegexValidateUtil.checkMobileNumber(s.toString()))
				{
					bt_get_sms_code.setEnabled(true);
					hasPhone = true;
				} else
				{
					bt_get_sms_code.setEnabled(false);
					hasPhone = false;
				}
				signup_btn_enable();
				
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
		
		et_check_code.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				if ("".equals(s.toString()))
				{
					hasCode = false;
					iv_delete_code.setVisibility(View.INVISIBLE);
				} else
				{
					hasCode = true;
					iv_delete_code.setVisibility(View.VISIBLE);
				}
				signup_btn_enable();
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
					iv_delete_psw.setVisibility(View.INVISIBLE);
				} else
				{
					hasPassword = true;
					iv_delete_psw.setVisibility(View.VISIBLE);
				}
				signup_btn_enable();
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
		et_repwd.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count)
			{
				if ("".equals(s.toString()))
				{
					hasRepwd = false;
					iv_delete_repsw.setVisibility(View.INVISIBLE);
				} else
				{
					hasRepwd = true;
					iv_delete_repsw.setVisibility(View.VISIBLE);
				}
				signup_btn_enable();
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
					et_repwd
					.setTransformationMethod(HideReturnsTransformationMethod
							.getInstance()); // 密码以明文显示
				} else
				{
					et_password
							.setTransformationMethod(PasswordTransformationMethod
									.getInstance()); // 密码以密文显示
					et_repwd
					.setTransformationMethod(PasswordTransformationMethod
							.getInstance()); // 密码以密文显示
				}
				
			}
		});
	}
	
	/**
	 * @describe 判断注册按钮是否可用
	 * @author mux2
	 * @date 2015年12月6日 下午1:12:20
	 */
	private void signup_btn_enable()
	{
		if (hasPhone && hasCode && hasPassword && hasRepwd)
		{
			bt_signup.setEnabled(true);
		} else
		{
			bt_signup.setEnabled(false);
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		
			case R.id.iv_back:
				finish();
				break;
			case R.id.iv_delete_mobile:
				et_phone.setText("");
				break;
			case R.id.iv_delete_code:
				et_check_code.setText("");
				break;
			case R.id.iv_delete_psw:
				et_password.setText("");
				break;
			case R.id.iv_delete_repsw:
				et_repwd.setText("");
				break;
			
			case R.id.bt_signup:
				phone = et_phone.getText().toString();
				code = et_check_code.getText().toString();
				password = et_password.getText().toString();
				String repwd = et_repwd.getText().toString();
				// 判断是否输入
				if ("".equals(phone))
				{
					et_phone.setError("请输入手机号");
					return;
				}
				if ("".equals(code))
				{
					et_check_code.setError("请输入验证码");
					return;
				}
				if ("".equals(password))
				{
					et_password.setError("请输入密码");
					return;
				}
				if ("".equals(repwd))
				{
					et_password.setError("请再次输入密码");
					return;
				}
				
				// 校验手机号是否合法
				if (!RegexValidateUtil.checkMobileNumber(phone))
				{
					et_phone.setError("手机号格式不对");
					return;
				}
				// 校验密码是否合法
				if (!RegexValidateUtil.checkCharacter(code))
				{
					et_check_code.setError("请输入4位验证码");
					return;
				}
				
				// 校验密码是否合法
				if (!RegexValidateUtil.checkCharacter(repwd))
				{
					et_repwd.setError("请输入4-16位密码");
					return;
				}
				if (!repwd.equals(password))
				{
					et_repwd.setError("两次密码不一致");
					return;
				}
				// TODO 提交请求
				// ToastUtils.showToast(this, "快速提交");
				bt_signup.setEnabled(false);
				bt_signup.setText("请稍后...");
				SMSSDK.submitVerificationCode("86", phone, code);
				
				break;
			// 获取验证码 按钮
			case R.id.bt_get_sms_code:
				String phone2 = et_phone.getText().toString();
				if (!RegexValidateUtil.checkMobileNumber(phone2))
				{
					et_phone.setError("请输入正确的手机号");
					return;
				}
				
				SMSSDK.getVerificationCode("86", phone2);
				
				// 倒计时
				time.start();
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
			bt_get_sms_code.setEnabled(false);
			bt_get_sms_code.setText(millisUntilFinished / 1000 + "秒");
		}
		
		@Override
		public void onFinish()
		{
			bt_get_sms_code.setText("重新获取");
			bt_get_sms_code.setEnabled(true);
			
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
					btnSignUp();
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
				int resId = getStringRes(RegisterActivity.this,
						"smssdk_network_error");
				Toast.makeText(RegisterActivity.this, "验证码错误",
						Toast.LENGTH_SHORT).show();
				if (resId > 0)
				{
					Toast.makeText(RegisterActivity.this, resId,
							Toast.LENGTH_SHORT).show();
				}
				bt_signup.setText("注册");
				bt_signup.setEnabled(true);
			}
			
		}
		
	};
	private Button bt_signup;
	private ImageView iv_delete_mobile;
	private ImageView iv_delete_code;
	private ImageView iv_delete_psw;
	private ImageView iv_delete_repsw;
	
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
	
	// Bomb注册验证
	public void btnSignUp()
	{
		final MyUser user = new MyUser();
		user.setUsername(phone);
		user.setPassword(password);
		user.signUp(this, new SaveListener()
		{
			@Override
			public void onSuccess()
			{
				Toast.makeText(RegisterActivity.this,
						"注册成功：" + user.getObjectId(), Toast.LENGTH_SHORT)
						.show();
				
				finish();
			}
			
			@Override
			public void onFailure(int code, String msg)
			{
				
				Toast.makeText(RegisterActivity.this,
						"注册失败：" + msg + "(" + code + ")", Toast.LENGTH_SHORT)
						.show();
				bt_signup.setText("注册");
				bt_signup.setEnabled(true);
			}
		});
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		SMSSDK.unregisterAllEventHandler();
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("RegisterActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("RegisterActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}
