package com.lashou.www.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.sono.www.R;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.lashou.www.activity.CityActivity;
import com.lashou.www.activity.DetailActivity;
import com.lashou.www.model.Cainixihuan;
import com.lashou.www.model.CenterBanner;
import com.lashou.www.model.Goodlist;
import com.lashou.www.model.HeaderBanner;
import com.lashou.www.model.HeaderBanner_;
import com.lashou.www.model.HeaderType;
import com.lashou.www.model.Hotfilm;
import com.lashou.www.model.Image;
import com.lashou.www.model.UpThreeBanner;
import com.lashou.www.model.UpTwoBanner;
import com.lashou.www.utils.APIClient;
import com.lashou.www.utils.Constants;
import com.lashou.www.utils.ImageUtils;
import com.lashou.www.utils.PagerIndicator;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;
import com.zxing.activity.CaptureActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class Fragment1 extends Fragment implements OnClickListener
{
	ArrayList<Object> mData=new ArrayList<Object>();
	ArrayList<Object> mRData=new ArrayList<>();
	public static final int BANNER_COUNT = 4 * 100000;
	private MyAdapter adapter;
	private ViewPager pagerBanner;
	private PagerIndicator pagerIndicator1;
	private PullToRefreshListView mPullToRefreshListView;
	MyRAdapter rAdapter;
	private ImageView imageView0;
	private ImageView imageView1;
	private ImageView imageView2;
	private ImageView imageView3;
	private ImageView imageView4;
	private ImageView imageViewM1;
	private ImageView imageViewM2;
	private ImageView imageViewM3;
	private ImageView imageViewM4;
	private ImageView imageViewM5;
	private ListView mListView;
	private TextView tv_city_name;
	private View view;
	LayoutInflater inflater;
	public Fragment1()
	{
		// Required empty public constructor
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{  
		if(view==null){
			this.inflater=inflater;
			view = inflater.inflate(R.layout.fragment_fragment1, container, false);
			initUI();	
			initData();	
		}
		return view;
	}

	private void initUI()
	{
		LinearLayout location_lay = (LinearLayout) view.findViewById(R.id.location_lay);
		location_lay.setOnClickListener(this);
		//扫描按钮
		ImageView scan_img = (ImageView) view.findViewById(R.id.scan_img);
		scan_img.setOnClickListener(this);
		tv_city_name = (TextView) view.findViewById(R.id.city_name);
		mPullToRefreshListView = (PullToRefreshListView) view
						.findViewById(R.id.pullToRefreshListView1);
				mListView = mPullToRefreshListView.getRefreshableView();
		adapter = new MyAdapter();
		//mHeadView
		View mHeadView = inflater.inflate(R.layout.headview_item, null);
		pagerBanner = (ViewPager) mHeadView.findViewById(R.id.pager_banner);
		pagerIndicator1 = (PagerIndicator) mHeadView.findViewById(R.id.pagerIndicator1);
		FragmentManager fm = getChildFragmentManager();
		pagerBanner.setAdapter(new PagerBannerAdapter(fm));
		pagerBanner.setCurrentItem(BANNER_COUNT / 2);
		pagerBanner.setOnPageChangeListener(new MyOnPageChangeListener());
		//mHeadView0
		View mHeadView0 = inflater.inflate(R.layout.headview_item0, null);
		//mHeadView2
		View mHeadView2 = inflater.inflate(R.layout.headview_item2, null);
		//recycler
		RecyclerView mRecyclerView= (RecyclerView) mHeadView2.findViewById(R.id.recyclerView1);
		mRecyclerView.setHasFixedSize(true);
 		LayoutManager layout=new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
 		mRecyclerView.setLayoutManager(layout);
		 rAdapter=new MyRAdapter();
	
		mRecyclerView.setAdapter(rAdapter);
		imageView0 = (ImageView) mHeadView0.findViewById(R.id.imageView0);
		imageView1 = (ImageView) mHeadView2.findViewById(R.id.imageView1);
		imageView2 = (ImageView) mHeadView2.findViewById(R.id.imageView2);
		imageView3 = (ImageView) mHeadView2.findViewById(R.id.imageView3);
		imageView4 = (ImageView) mHeadView2.findViewById(R.id.imageView4);
		imageViewM1 = (ImageView) mHeadView2.findViewById(R.id.imageViewM1);
		imageViewM2 = (ImageView) mHeadView2.findViewById(R.id.imageViewM2);
		imageViewM3 = (ImageView) mHeadView2.findViewById(R.id.imageViewM3);
		imageViewM4 = (ImageView) mHeadView2.findViewById(R.id.imageViewM4);
		imageViewM5 = (ImageView) mHeadView2.findViewById(R.id.imageViewM5);
		
		mListView.addHeaderView(mHeadView0);
		mListView.addHeaderView(mHeadView);
		mListView.addHeaderView(mHeadView2);
		View footerView = inflater.inflate(R.layout.home_footer, null);
		mListView.addFooterView(footerView);
		mListView.setAdapter(adapter);
		
		// 下拉刷新监听
		
				mPullToRefreshListView
						.setOnRefreshListener(new OnRefreshListener2<ListView>()
						{
							
							@Override
							public void onPullDownToRefresh(
									PullToRefreshBase<ListView> refreshView)
							{
								String label = DateUtils.formatDateTime(getActivity()
										.getApplicationContext(), System
										.currentTimeMillis(),
										DateUtils.FORMAT_SHOW_TIME
												| DateUtils.FORMAT_SHOW_DATE
												| DateUtils.FORMAT_ABBREV_ALL);
								
								refreshView.getLoadingLayoutProxy()
										.setLastUpdatedLabel(label);
								 
									initData();
								 
							}
							
							@Override
							public void onPullUpToRefresh(
									PullToRefreshBase<ListView> refreshView)
							{
								String label = DateUtils.formatDateTime(getActivity()
										.getApplicationContext(), System
										.currentTimeMillis(),
										DateUtils.FORMAT_SHOW_TIME
												| DateUtils.FORMAT_SHOW_DATE
												| DateUtils.FORMAT_ABBREV_ALL);
								
								refreshView.getLoadingLayoutProxy()
										.setLastUpdatedLabel(label);
								 
									initData();
								 
							}
							
						});
				mListView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						mListView.getHeaderViewsCount();
						Intent intent = new Intent(getContext(), DetailActivity.class);
						startActivity(intent );
					}
				});
	}
	
	private void initData()
	{
		//加载猜你喜欢
		APIClient.requestCainixihuan(getActivity(),
				new VolleyListener()
				{
					
					@Override
					public void onResponse(String arg0)
					{
						 Cainixihuan cainixihuan = GsonUtils.parseJSON(
								arg0, Cainixihuan.class);
						  List<Goodlist> goodlist = cainixihuan.getGoodlist();
								 
						mData.clear();
						mData.addAll(goodlist);
//						catalog = "news";
						adapter.notifyDataSetChanged();
 						mPullToRefreshListView.onRefreshComplete();
					}
					
					@Override
					public void onErrorResponse(VolleyError arg0)
					{
						/*Toast.makeText(getActivity(), "下载数据失败",
								Toast.LENGTH_SHORT).show();*/
 						mPullToRefreshListView.onRefreshComplete();
						
					}
				});
		//加载热门电影
		APIClient.requestHotfilm(getActivity(),
				new VolleyListener()
				{
					
					@Override
					public void onResponse(String arg0)
					{
							Type type = new TypeToken<ArrayList<Hotfilm>>(){}.getType(); 
						  List<Hotfilm> headerType = GsonUtils.parseJSONArray(arg0, type);
							
						  
						  
						mRData.clear();
						mRData.addAll(headerType);
//						catalog = "news";
						rAdapter.notifyDataSetChanged();
 						mPullToRefreshListView.onRefreshComplete();
					}
					
					@Override
					public void onErrorResponse(VolleyError arg0)
					{
						/*Toast.makeText(getActivity(), "下载数据失败",
								Toast.LENGTH_SHORT).show();*/
 						mPullToRefreshListView.onRefreshComplete();
						
					}
				});
		//加载首页各种banner
		APIClient.requestHeaderBanner(getActivity(),
				new VolleyListener()
				{
					
					@Override
					public void onResponse(String arg0)
					{
					/*	 Cainixihuan cainixihuan = GsonUtils.parseJSON(
								arg0, Cainixihuan.class);
						  List<Goodlist> goodlist = cainixihuan.getGoodlist();
								 
						mData.clear();
						mData.addAll(goodlist);*/
//						 
						HeaderBanner headerBanner = GsonUtils.parseJSON(arg0, HeaderBanner.class);
						List<HeaderBanner_> banner = headerBanner.getHeaderBanner();
						List<CenterBanner> centerBanner = headerBanner.getCenterBanner();
						List<UpTwoBanner> upTwoBanner = headerBanner.getUpTwoBanner();
						List<UpThreeBanner> upThreeBanner = headerBanner.getUpThreeBanner();
						//TODO
  						UILUtils.displayImage(banner.get(0).getImgMid(), imageView0);
  						UILUtils.displayImage(centerBanner.get(0).getImgMid(), imageView1);
  						UILUtils.displayImage(centerBanner.get(1).getImgMid(), imageView2);
  						UILUtils.displayImage(centerBanner.get(2).getImgMid(), imageView3);
  						UILUtils.displayImage(centerBanner.get(3).getImgMid(), imageView4);
  						UILUtils.displayImage(upTwoBanner.get(0).getImgMid(), imageViewM1);
  						UILUtils.displayImage(upTwoBanner.get(1).getImgMid(), imageViewM2);
  						UILUtils.displayImage(upThreeBanner.get(0).getImgMid(), imageViewM3);
  						UILUtils.displayImage(upThreeBanner.get(1).getImgMid(), imageViewM4);
  						UILUtils.displayImage(upThreeBanner.get(2).getImgMid(), imageViewM5);
						
						
  					/*	private ImageView imageView0;
  						private ImageView imageView1;
  						private ImageView imageView2;
  						private ImageView imageView3;
  						private ImageView imageView4;
  						private ImageView imageViewM1;
  						private ImageView imageViewM2;
  						private ImageView imageViewM3;
  						private ImageView imageViewM4;
  						private ImageView imageViewM5;*/
  						
  						
  						
//						adapter.notifyDataSetChanged();
// 						mPullToRefreshListView.onRefreshComplete();
					}
					
					@Override
					public void onErrorResponse(VolleyError arg0)
					{
					/*	Toast.makeText(getActivity(), "下载数据失败",
								Toast.LENGTH_SHORT).show();*/
// 						mPullToRefreshListView.onRefreshComplete();
						
					}
				});
		
		
	}

	class MyAdapter extends BaseAdapter{

		@Override
		public int getCount()
		{
			return mData.size();
		}

		@Override
		public Object getItem(int position)
		{
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			return 0;
		}

		 
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			 View view=null;
			 LVViewHolder viewHolder=null;
			 if(view==null){
				 
				 view = getActivity().getLayoutInflater().inflate(R.layout.home_listview_item, null);
				 
				
				viewHolder = new LVViewHolder();
				 viewHolder. iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
				 viewHolder. tv_title = (TextView) view.findViewById(R.id.tv_title);
				 viewHolder. tv_body = (TextView) view.findViewById(R.id.tv_body);
				 viewHolder. tv_price_red = (TextView) view.findViewById(R.id.tv_price_red);
				 viewHolder. tv_price_gray = (TextView) view.findViewById(R.id.tv_price_gray);
				 viewHolder. tv_sellCount = (TextView) view.findViewById(R.id.tv_sellCount);
				 view.setTag(viewHolder);
			 }else{
				 view=convertView;
				 viewHolder =(LVViewHolder) view.getTag();
			 }
			 
			 Goodlist goodlist = (Goodlist) mData.get(position);
			 List<Image> images = goodlist.getImages();
			 if(images.size()>2){
				 
//				 UILUtils.displayImage(images.get(2).getImage(), viewHolder. iv_pic);
				 ImageUtils.displayImage(getContext(),images.get(2).getImage(), viewHolder. iv_pic);
			 }
			 viewHolder. tv_title .setText(goodlist.getProduct());
			 viewHolder. tv_body.setText(goodlist.getShortTitle());
			 viewHolder. tv_price_red.setText("¥"+goodlist.getPrice());
			 viewHolder. tv_price_gray.setText("¥"+goodlist.getValue());
			 //TODO 中间加删除线
			 viewHolder. tv_price_gray.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			 viewHolder. tv_sellCount.setText("已售"+goodlist.getBought());
			 
			return view;
		}
		
	}
	
	class LVViewHolder{
		 ImageView iv_pic  ;
		 TextView tv_title ;
		 TextView tv_body  ;
		 TextView tv_price_red  ;
		 TextView tv_price_gray  ;
		 TextView tv_sellCount;
	}
	
	class PagerBannerAdapter extends FragmentPagerAdapter
	{
		
		public PagerBannerAdapter(FragmentManager fm)
		{
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position)
		{
			position %= 2;
			return new BannerItemFragment(position);
		}
		
		@Override
		public int getCount()
		{
			return BANNER_COUNT;
		}
		
	}
	
	class MyOnPageChangeListener implements OnPageChangeListener
	{
		
		@Override
		public void onPageScrollStateChanged(int state)
		{
			/*switch (state)
			{
				case ViewPager.SCROLL_STATE_DRAGGING:
					isDrag = true;
					break;
				case ViewPager.SCROLL_STATE_IDLE:
					isDrag = false;
					break;
				case ViewPager.SCROLL_STATE_SETTLING:
					isDrag = false;
					break;
				
				default:
					break;
			}*/
			
		}
		
		@Override
		public void onPageScrolled(int position, float percent, int arg2)
		{
			pagerIndicator1.move(position,percent);
		}
		
		@Override
		public void onPageSelected(int arg0)
		{
			
		}
		
	}
	
	class MyRAdapter extends Adapter<MyViewHolder>{

		@Override
		public int getItemCount()
		{
			return mRData.size();
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position)
		{
			Hotfilm hotfilm = (Hotfilm) mRData.get(position);
			//行点击 TODO
		 
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					 
					
				}
			});
//			arg0.itemView.sett	
			holder.setFilmName(hotfilm.getFilmName());
			holder.setGrade(hotfilm.getGrade());
			holder.setImgUrl(hotfilm.getImageUrl());
		
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1)
		{
			View view = getActivity().getLayoutInflater().inflate(R.layout.hotfilm_item, null);
			MyViewHolder holder = new MyViewHolder(view);
			return holder;
		}

		 
		 
	 }
	class MyViewHolder extends ViewHolder{
		public MyViewHolder(View itemView)
		{
			super(itemView);
		 
		}
		public void setFilmName(String title)
		{
			TextView textView1 = (TextView) itemView.findViewById(R.id.textView1);
			textView1.setText(title);
			
		}
		
		public void setGrade(String grade)
		{
			TextView textView2 = (TextView) itemView.findViewById(R.id.textView2);
			textView2.setText(grade+"分");
			
		}
		public void setImgUrl(String imageUrl)
		{
			ImageView imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
//			UILUtils.displayImage(imageUrl, imageView1);
			ImageUtils.displayImage(getContext(),imageUrl, imageView1);
			
		}
	
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.location_lay:
				Intent intent = new Intent(getActivity(),CityActivity.class);
				startActivityForResult(intent, 0);
				break;
			case R.id.scan_img:
				Intent openCameraIntent = new Intent(getActivity(),CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
				break;
			
			default:
				break;
		}
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data != null && data.hasExtra(Constants.KEY.CITYNAME)) {
			String cityName = data.getStringExtra(Constants.KEY.CITYNAME);
			tv_city_name.setText(cityName);
		}
		//处理扫描结果（在界面上显示）
				if (resultCode == getActivity().RESULT_OK) {
					Bundle bundle = data.getExtras();
					String scanResult = bundle.getString("result");
					Toast.makeText(getActivity(), scanResult, Toast.LENGTH_SHORT).show();
//					resultTextView.setText(scanResult);
				}
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("Fragment1"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("Fragment1"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
//		MobclickAgent.onPause(this);
	}
	
}
