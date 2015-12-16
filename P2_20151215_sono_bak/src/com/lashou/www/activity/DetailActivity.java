package com.lashou.www.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.senab.photoview.sample.ViewPagerActivity;

import com.android.volley.VolleyError;
import com.sono.www.R;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lashou.www.model.Detail1;
import com.lashou.www.model.ItemInfo;
import com.lashou.www.utils.APIClient;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.demo.AccessTokenKeeper;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;
import com.xinbo.widget.ListView4ScrollView;
import com.xinbo.widget.ScrollViewExtend;
import com.xinbo.widget.ScrollViewExtend.OnScrollChangedListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity implements OnClickListener
{
	private PopupWindow popupWindow;
	private View popupView;
	
	private WebView mWebViewDetail;
	private WebView mWebViewPrompt;
	private View top_buy_layout;
	private ImageView image_view;
	private View layout_big_image;
	private ArrayList<ItemInfo> mDataComment = new ArrayList<ItemInfo>();
	private MyCommentAdapter myCommentadapter;
	private ListView4ScrollView lv_comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_detail);
		initUI();
		initData();
	}
	
	private void initData()
	{
		APIClient.getDetail1(this, new VolleyListener()
		{
			
			@Override
			public void onResponse(String arg0)
			{
				Detail1 detail1 = GsonUtils.parseJSON(arg0, Detail1.class);
				String details = detail1.getNotice();
				mWebViewDetail.loadDataWithBaseURL(null, details, "text/html",
						"utf-8", null);
				mWebViewPrompt.loadDataWithBaseURL(null, details, "text/html",
						"utf-8", null);
			}
			
			@Override
			public void onErrorResponse(VolleyError arg0)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		ItemInfo info1 = new ItemInfo("", "足浴", "98", 1);
		ItemInfo info2 = new ItemInfo("", "足浴", "78", 1);
		ItemInfo info3 = new ItemInfo("", "查看其他优惠", null, 2);
		
		mDataComment.add(info1);
		mDataComment.add(info2);
		mDataComment.add(info3);
		// int size = mDataComment.size();
		
	}
	
	private void initUI()
	{
		TextView title_tv = (TextView) findViewById(R.id.title_tv);
		title_tv.setText("商品详情");
		ImageView iv_collect = (ImageView) findViewById(R.id.iv_collect);
		iv_collect.setVisibility(View.VISIBLE);
		ImageView right_img = (ImageView) findViewById(R.id.right_img);
		right_img.setVisibility(View.VISIBLE);
		right_img.setOnClickListener(this);
		ImageView back_img = (ImageView) findViewById(R.id.back_img);
		back_img.setVisibility(View.VISIBLE);
		back_img.setOnClickListener(this);
		View ll_call_phone = findViewById(R.id.ll_call_phone);
		ll_call_phone.setOnClickListener(this);
		
		layout_big_image = findViewById(R.id.layout_big_image);
		layout_big_image.setOnClickListener(this);
		
		mWebViewDetail = (WebView) findViewById(R.id.wv_gn_details);
		mWebViewPrompt = (WebView) findViewById(R.id.wv_gn_warm_prompt);
		
		// ptr
		PullToRefreshScrollView pull_to_refresh_scrollview = (PullToRefreshScrollView) findViewById(R.id.pull_to_refresh_scrollview);
		ScrollViewExtend scrollView = (ScrollViewExtend) pull_to_refresh_scrollview
				.getRefreshableView();
		top_buy_layout = findViewById(R.id.top_buy_layout);
		image_view = (ImageView) findViewById(R.id.image_view);
		
	
		
		// 删除线,写两次
		View buy_layout = findViewById(R.id.buy_layout);
		TextView tv_org_price = (TextView) buy_layout
				.findViewById(R.id.tv_org_price);
		tv_org_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		TextView tv_org_price2 = (TextView) top_buy_layout
				.findViewById(R.id.tv_org_price);
		tv_org_price2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		
		
		//购买按钮
		Button btn_buy = (Button) buy_layout.findViewById(R.id.btn_buy);
		btn_buy.setOnClickListener(this);
		Button btn_buy2 = (Button) top_buy_layout.findViewById(R.id.btn_buy);
		btn_buy2.setOnClickListener(this);
		
		scrollView.setOnScrollChangedListener(new OnScrollChangedListener()
		{
			
			@Override
			public void onScrollChange(int offset)
			{
				int height = layout_big_image.getHeight();
				if (offset > height)
				{
					top_buy_layout.setVisibility(View.VISIBLE);
				} else
				{
					top_buy_layout.setVisibility(View.GONE);
					
				}
				
			}
		});
		
		lv_comment = (ListView4ScrollView) findViewById(R.id.lv_comment);
		
		myCommentadapter = new MyCommentAdapter();
		lv_comment.setAdapter(myCommentadapter);
		lv_comment.setOnItemClickListener(new OnItemClickListener()
		{
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				ItemInfo info = mDataComment.get(position
						- lv_comment.getHeaderViewsCount());
				switch (info.type)
				{
				
					case 1:
						// TODO 跳转到商品详情
						break;
					case 2:
						// TODO 刷新ListView
						mDataComment.remove(info);
						ItemInfo info1 = new ItemInfo("", "足浴", "108", 1);
						ItemInfo info2 = new ItemInfo("", "足浴", "88", 1);
						ItemInfo info3 = new ItemInfo("", "足浴", "88", 1);
						mDataComment.add(
								position - lv_comment.getHeaderViewsCount(),
								info1);
						mDataComment.add(position, info2);
						mDataComment.add(position + 1, info3);
						myCommentadapter.notifyDataSetChanged();
						break;
					
					default:
						break;
				}
				
			}
		});
		
		initPopWindow();
	}
	
	class MyCommentAdapter extends BaseAdapter
	{
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return mDataComment.size();
		}
		
		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View layout = null;
			ItemInfo info = mDataComment.get(position);
			if (convertView == null)
			{
				switch (info.type)
				{
				
					case 1:
						layout = getLayoutInflater().inflate(
								R.layout.item_all_comment_list, null);
						break;
					case 2:
						layout = getLayoutInflater().inflate(
								R.layout.item_all_comment_list_more, null);
						
						break;
					
					default:
						break;
				}
			} else
			{
				layout = convertView;
			}
			// TextView tvTitle = (TextView)
			// layout.findViewById(R.id.textView1);
			// tvTitle.setText(info.title);
			return layout;
		}
		
		@Override
		public int getItemViewType(int position)
		{
			ItemInfo info = mDataComment.get(position);
			return info.type;
		}
		
		@Override
		public int getViewTypeCount()
		{
			// TODO Auto-generated method stub
			return 3;
		}
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.back_img:
				finish();
				break;
			case R.id.right_img:
				
				popupWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 0);
				break;
			// pop取消按钮
			case R.id.cancel_share_layout:
				// pop体上半边 半透明控件
			case R.id.view_transparent:
				popupWindow.dismiss();
				break;
			case R.id.sina_weibo:
				
				Intent intentW = new Intent(this, ShareEditActivity.class);
				startActivity(intentW);
				break;
			case R.id.layout_big_image:
				Intent intent = new Intent(this, ViewPagerActivity.class);
				startActivity(intent);
				break;
			case R.id.btn_buy:
			 
				
				Intent intent2=new Intent(this,SubmitOrderActivity.class);
				startActivity(intent2);
				break;
			case R.id.ll_call_phone:
				Intent intent3=new Intent();
				//交出拨号程序
 				intent3.setAction(Intent.ACTION_DIAL);
				//直接打出去
// 				intent2.setAction(Intent.ACTION_CALL);
				String mobile="13726280916";
				intent3.setData(Uri.parse("tel:"+mobile));
				startActivity(intent3);
				break;
			default:
				break;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void initPopWindow()
	{
		popupView = getLayoutInflater().inflate(
				R.layout.activity_share_sms_view, null);
		
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		Drawable background = getResources().getDrawable(R.drawable.bg_popup);
		popupWindow.setBackgroundDrawable(background);
		
		// popupWindow.setOutsideTouchable(true);
		// popupWindow.setFocusable(true);
		View view_transparent = (View) popupView
				.findViewById(R.id.view_transparent);
		view_transparent.setOnClickListener(this);
		LinearLayout cancel_share_layout = (LinearLayout) popupView
				.findViewById(R.id.cancel_share_layout);
		cancel_share_layout.setOnClickListener(this);
		TextView sina_weibo = (TextView) popupView
				.findViewById(R.id.sina_weibo);
		sina_weibo.setOnClickListener(this);
	}
	
	public void onResume()
	{
		super.onResume();
		MobclickAgent.onPageStart("DetailActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}
	
	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPageEnd("DetailActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}
