package com.lashou.www.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lashou.www.model.Cities;
import com.lashou.www.model.City;
import com.lashou.www.utils.APIClient;
import com.lashou.www.utils.Constants;
import com.sono.www.R;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;
import com.xinbo.widget.LetterSideBar;
import com.xinbo.widget.LetterSideBar.OnLetterChangedListener;

@SuppressLint("ViewHolder")
public class CityActivity extends Activity {
	private List<City> mData = new ArrayList<>();
	private List<City> mSearchData = new ArrayList<>();
	private List<City> mDataHot = new ArrayList<>();
	private CityAdapter mAdapter;
	private SearchAdapter mSearchAdapter;
	private ListView mSearchListView;
	private HotCityAdapter hotCityAdapter;
	private TextView tv_mid_letter;
	Handler handler=new Handler();
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_switch_city);
		initUI();
		initData();
	}

	private void initUI() {
		mListView = (ListView) findViewById(R.id.content_list);
		mAdapter = new CityAdapter();
		
		 View mGirdView = getLayoutInflater().inflate(R.layout.select_hotcity_item, null);
		 GridView gridView1 = (GridView) mGirdView.findViewById(R.id.gridView1);
		 hotCityAdapter = new HotCityAdapter();
		 gridView1.setAdapter(hotCityAdapter);
		 gridView1.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				City city = mDataHot.get(position);
				Intent data = new Intent();
				data.putExtra("cityname", city.getName());
				setResult(0, data);
				finish();
				
			}
		});
		 mListView.addHeaderView(mGirdView);
		
		
		
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				City city = mData.get(position);
				Intent data = new Intent();
				data.putExtra("cityname", city.getName());
				setResult(0, data);
				finish();
			}
		});

		mSearchListView = (ListView) findViewById(R.id.search_list);
		mSearchAdapter = new SearchAdapter();
		
		
		mSearchListView.setAdapter(mSearchAdapter);
		mSearchListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				City city = mSearchData.get(position);
				Intent data = new Intent();
				data.putExtra(Constants.KEY.CITYNAME, city.getName());
				setResult(0, data);
				finish();
			}
		});
		tv_mid_letter = (TextView) findViewById(R.id.tv_mid_letter);
		LetterSideBar citys_bladeview = (LetterSideBar) findViewById(R.id.citys_bladeview);
		citys_bladeview.setOnLetterChangedListener(new OnLetterChangedListener()
		{
			private Runnable mRunnable;
			@Override
			public void onTouchAction(int action)
			{ 
				if(action==MotionEvent.ACTION_DOWN||action==MotionEvent.ACTION_UP){
					handler.removeCallbacks(mRunnable);
					
					
				}else{
					
					mRunnable = new Runnable()
					{
						
						@Override
						public void run()
						{
							tv_mid_letter.setVisibility(View.INVISIBLE);
							
						}
					};
					handler.postDelayed(mRunnable, 2000);
				}
				
			}
			
			@Override
			public void onLetterChanged(String letter)
			{
				// TODO Auto-generated method stub
				tv_mid_letter.setVisibility(View.VISIBLE);
				tv_mid_letter.setText(letter);
				int position = 0;
				for (int i = 0; i < mData.size(); i++)
				{
					String string = mData.get(i).getPinyin();
					if(letter.equalsIgnoreCase(string.substring(0, 1))){
						position=i;
						break;
					}
				}
				//这里要加上
				mListView.setSelection(position+mListView.getHeaderViewsCount());
			}
		});
		

		initSearchEdit();
	}

	public void btnBack(View v) {
		finish();
	}

	private void initSearchEdit() {
		EditText mEditText = (EditText) findViewById(R.id.search_edit);
		mEditText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String key = s.toString();
				// 判断是否输入关键字
				if ("".equals(key)) {
					mSearchListView.setVisibility(View.GONE);
				} else {
					mSearchListView.setVisibility(View.VISIBLE);
				}

				mSearchData.clear();
				mSearchAdapter.notifyDataSetChanged();

				// 根据关键字遍历所有城市
				for (int i = 0; i < mData.size(); i++) {
					City city = mData.get(i);
					// 以输入的拼音开头，或者以输入的中文开头
					if (city.getPinyin().toLowerCase().startsWith(key.toLowerCase())
							|| city.getName().startsWith(key)) {
						mSearchData.add(city);
						mSearchAdapter.notifyDataSetChanged();
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}

	private void initData() {
		APIClient.getCities(this, new VolleyListener() {

			@Override
			public void onResponse(String arg0) {
				Cities cities = GsonUtils.parseJSON(arg0, Cities.class);
				mData.addAll(cities.getAllcity());
				mAdapter.notifyDataSetChanged();
				
				mDataHot.addAll(cities.getHotcity());
				hotCityAdapter.notifyDataSetChanged();
				
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	class CityAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.list_item_descity, null);
			TextView tvLetter = (TextView) layout.findViewById(R.id.tv_group_title);
			TextView tvName = (TextView) layout.findViewById(R.id.tv_descity_name);
			City city = mData.get(position);
			String firstLetter = city.getPinyin().toUpperCase().substring(0, 1);

			if (position > 0) {
				City prevCity = mData.get(position - 1);
				String prevFirstLetter = prevCity.getPinyin().toUpperCase().substring(0, 1);
				if (prevFirstLetter.equals(firstLetter)) {
					tvLetter.setVisibility(View.GONE);
				}
			}
			tvLetter.setText(firstLetter);
			tvName.setText(city.getName());
			return layout;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	class SearchAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return mSearchData.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater();

			View layout = inflater.inflate(R.layout.list_item_descity, null);
			TextView tvLetter = (TextView) layout.findViewById(R.id.tv_group_title);
			TextView tvName = (TextView) layout.findViewById(R.id.tv_descity_name);
			tvLetter.setVisibility(View.GONE);
			City city = mSearchData.get(position);
			String firstLetter = city.getPinyin().toUpperCase().substring(0, 1);
			tvLetter.setText(firstLetter);
			tvName.setText(city.getName());
			return layout;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	}
	
	class HotCityAdapter extends BaseAdapter{

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return mDataHot.size();
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
			 View view = getLayoutInflater().inflate(R.layout.list_item_hotcity, null);
			 TextView tv_hotcity = (TextView) view.findViewById(R.id.tv_hotcity);
			 City city = mDataHot.get(position);
			 tv_hotcity.setText(city.getName());
			return view;
		}
		
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("CityActivity"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("CityActivity"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
		MobclickAgent.onPause(this);
	}
}
