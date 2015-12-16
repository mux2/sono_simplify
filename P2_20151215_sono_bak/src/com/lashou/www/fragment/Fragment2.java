package com.lashou.www.fragment;

import java.util.ArrayList;
import java.util.List;

import com.sono.www.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lashou.www.activity.DetailActivity;
import com.lashou.www.activity.ShopDetailActivity;
import com.lashou.www.baidumap.CoveringActivity;
import com.lashou.www.baidumap.LocationActivity;
import com.lashou.www.model.ItemInfo;
import com.lashou.www.model.MyAllCatalog;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.widget.ListView4ScrollView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Fragment2 extends Fragment implements OnClickListener
{
	
	private PopupWindow popupWindow;
	private ToggleButton tv_all_catalog;
	LayoutInflater mInflater;
	ArrayList<MyAllCatalog> mData=new ArrayList();
	private MyAdapter adapter;
	private List<ItemInfo> mDataBody = new ArrayList<ItemInfo>();
	private MyAdapterBody myAdapterBody;
	private ListView listView;
	
	
	public Fragment2()
	{
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view = null;
		if(view ==null){
			
			mInflater = inflater;
			
			view = inflater.inflate(R.layout.fragment_fragment2, container,
					false);
			initUI(view);
			initData();
		}
		return view;
	}

	private void initUI(View view)
	{
		//百度地图
		ImageView right_map = (ImageView) view.findViewById(R.id.right_map);
		right_map.setOnClickListener(this);
		ImageView right_search = (ImageView) view.findViewById(R.id.right_search);
		right_search.setOnClickListener(this);
		
		
		
		
		tv_all_catalog = (ToggleButton) view.findViewById(R.id.bt_filter_category);
		tv_all_catalog.setOnClickListener(this);
		PullToRefreshListView ptr_lv = (PullToRefreshListView) view.findViewById(R.id.ptr_lv);
		listView = ptr_lv.getRefreshableView();
		myAdapterBody = new MyAdapterBody();
		listView.setAdapter(myAdapterBody);
		initPopWindow();
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ItemInfo info = mDataBody.get(position-listView.getHeaderViewsCount());
				switch (info.type) {
				case 0:
					// TODO 跳转到商家详情
					Intent intent=new Intent(getActivity(),ShopDetailActivity.class);
					startActivity(intent);
					break;
				case 1:
					// TODO 跳转到商品详情
					Intent intent2 = new Intent(getContext(), DetailActivity.class);
					startActivity(intent2);
					break;
				case 2:
					// TODO 刷新ListView
					mDataBody.remove(info);
					ItemInfo info1 = new ItemInfo("", "足浴", "108", 1);
					ItemInfo info2 = new ItemInfo("", "足浴", "88", 1);
					mDataBody.add(position-listView.getHeaderViewsCount(),info1);
					mDataBody.add(position,info2);
					myAdapterBody.notifyDataSetChanged();
					break;

				default:
					break;
				}
			}
		});
	}
	
	private void initData()
	{
		mData.add(new MyAllCatalog(R.drawable.ic_all, "全部分类", 13697));
		mData.add(new MyAllCatalog(R.drawable.ic_newest, "今日新单", 8));
		mData.add(new MyAllCatalog(R.drawable.ic_food, "美食", 1268));
		mData.add(new MyAllCatalog(R.drawable.ic_entertain, "休闲娱乐", 1319));
		mData.add(new MyAllCatalog(R.drawable.ic_movie, "电影", 34));
		mData.add(new MyAllCatalog(R.drawable.ic_life, "生活服务", 811));
		mData.add(new MyAllCatalog(R.drawable.ic_photo, "摄影写真", 571));
		mData.add(new MyAllCatalog(R.drawable.ic_hotel, "酒店", 5440));
		mData.add(new MyAllCatalog(R.drawable.ic_travel, "旅游", 485));
		mData.add(new MyAllCatalog(R.drawable.ic_beauty, "丽人", 1010));
		mData.add(new MyAllCatalog(R.drawable.ic_edu, "教育培训", 239));
		mData.add(new MyAllCatalog(R.drawable.ic_luck, "抽奖公益", 1));
		mData.add(new MyAllCatalog(R.drawable.ic_shopping, "购物", 2123));
		adapter.notifyDataSetChanged();
		
		
		ItemInfo info = new ItemInfo("", "富桥", "养生", 0);
		ItemInfo info1 = new ItemInfo("", "足浴", "98", 1);
		ItemInfo info2 = new ItemInfo("", "足浴", "78", 1);
		ItemInfo info3 = new ItemInfo("", "查看其他优惠", null, 2);
		ItemInfo info4 = new ItemInfo("", "富桥", "养生", 0);
		ItemInfo info5 = new ItemInfo("", "足浴", "98", 1);
		ItemInfo info6 = new ItemInfo("", "足浴", "78", 1);
		ItemInfo info7 = new ItemInfo("", "查看其他优惠", null, 2);
		ItemInfo info8 = new ItemInfo("", "富桥", "养生", 0);
		ItemInfo info9 = new ItemInfo("", "足浴", "98", 1);
		ItemInfo info10 = new ItemInfo("", "足浴", "78", 1);
		ItemInfo info11 = new ItemInfo("", "查看其他优惠", null, 2);
		mDataBody.add(info);
		mDataBody.add(info1);
		mDataBody.add(info2);
		mDataBody.add(info3);
		mDataBody.add(info4);
		mDataBody.add(info5);
		mDataBody.add(info6);
		mDataBody.add(info7);
		mDataBody.add(info8);
		mDataBody.add(info9);
		mDataBody.add(info10);
		mDataBody.add(info11);
		
	}

	private void initPopWindow()
	{
		// ListView4ScrollView
		// View contentView=inflater.inflate(R.layout.nearby_popupwindow, null);
		View popupView = mInflater.inflate(R.layout.popup_category, null);
		View view_transparent = popupView.findViewById(R.id.view_transparent);
		view_transparent.setOnClickListener(this);
		
		 
		popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		Drawable background = getResources().getDrawable(R.drawable.bg_popup);
		popupWindow.setBackgroundDrawable(background);
		popupWindow.setOnDismissListener(new OnDismissListener()
		{
			
			@Override
			public void onDismiss()
			{
				
				tv_all_catalog.setChecked(false);
			}
		});
		// ListView4ScrollView listView1 = (ListView4ScrollView)
		// contentView.findViewById(R.id.listView1);
		ListView lv_category = (ListView) popupView
				.findViewById(R.id.lv_category);
		adapter = new MyAdapter();
		lv_category.setAdapter(adapter);
		
		ListView lv_subcategory = (ListView) popupView.findViewById(R.id.lv_subcategory); 
		MyAdapter adapter2 = new MyAdapter();
		lv_subcategory.setAdapter(adapter2);
		
		
		lv_category.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				// TODO  
				
				
			}
		});
	}
	
	private class MyAdapterBody extends BaseAdapter {
		@Override
		public int getViewTypeCount() {
			// TODO 有几种不同行布局
			return 3;
		}
		public int getItemViewType(int position) {
			ItemInfo info = mDataBody.get(position);
			return info.type;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View layout = null;
			ItemInfo info = mDataBody.get(position);
			if (convertView == null) {
				switch (info.type) {
				case 0:
					layout = mInflater.inflate(R.layout.nearby_list_item1, null);
					break;
				case 1:
					layout = mInflater.inflate(R.layout.nearby_list_item2, null);
					TextView textView4 = (TextView) layout.findViewById(R.id.textView4);
					textView4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
					break;
				case 2:
					layout = mInflater.inflate(R.layout.nearby_list_item3, null);

					break;

				default:
					break;
				}
			} else {
				layout = convertView;
			}
//			TextView tvTitle = (TextView) layout.findViewById(R.id.textView1);
//			tvTitle.setText(info.title);
			return layout;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDataBody.size();
		}
	}
	 
	
	class MyAdapter extends BaseAdapter
	{
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return mData.size();
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
			View view = mInflater.inflate(R.layout.pop_listview1_item, null);
			ImageView imageView1 = (ImageView) view.findViewById(R.id.imageView1);
			
			TextView textView1 = (TextView) view.findViewById(R.id.textView1);
			TextView textView2 = (TextView) view.findViewById(R.id.textView2);
			MyAllCatalog catalog = mData.get(position);
			
			
			imageView1.setImageResource(catalog.getImgId());
			textView1.setText(catalog.getCatalog());
			textView2.setText(""+catalog.getCount());
			return view;
		}
		
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.bt_filter_category:
					popupWindow.showAsDropDown(v);
//				 
				break;
			case R.id.view_transparent:
				popupWindow.dismiss();
				tv_all_catalog.setChecked(false);
				break;
			case R.id.right_map:
				
				Intent intent=new Intent(getContext(),LocationActivity.class);
				startActivity(intent);
				break;
			case R.id.right_search:
				
//				Intent intent2=new Intent(getContext(),CoveringActivity.class);
//				startActivity(intent2);
				break;
			default:
				break;
		}
		
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("Fragment2"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Fragment2"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
//		MobclickAgent.onPause(this);
	}
}
