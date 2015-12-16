package com.lashou.www.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.VolleyError;
import com.sono.www.R;
import com.google.gson.reflect.TypeToken;
import com.lashou.www.model.Cainixihuan;
import com.lashou.www.model.Goodlist;
import com.lashou.www.model.HeaderType;
import com.lashou.www.utils.APIClient;
import com.umeng.analytics.MobclickAgent;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class BannerItemFragment extends Fragment
{
	
	private int position;
	private int[] arr;
	ArrayList<Object> mData = new ArrayList<Object>();
	private MyAdapter adapter;
	
	public BannerItemFragment(int position)
	{
		this.position = position;
		// arr = new int[]
		// {R.drawable.banner_bg1,R.drawable.banner_bg2,R.drawable.banner_bg3,R.drawable.banner_bg4};
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// Inflate the layout for this fragment
		View view=null;
		if(view==null){
			
			view = inflater.inflate(R.layout.fragment_banner_item, container,
					false);
			// TextView mTextView = (TextView) view.findViewById(R.id.textView1);
			// mTextView.setText("position"+position);
			// ImageView mImageView = (ImageView)
			// view.findViewById(R.id.imageView1);
			// mImageView.setImageResource(arr[position]);
			initUI(view);
			initData();
		}
		return view;
	}

	/**
	 * @describe  TODO
	 * @author mux2
	 * @date 2015年12月11日 上午12:36:41
	 * @param view
	 */
	private void initUI(View view)
	{
		GridView gv1 = (GridView) view.findViewById(R.id.gv1);
		adapter = new MyAdapter();
		gv1.setAdapter(adapter);
	}
	
	private void initData()
	{

		APIClient.requestHeaderType(getActivity(),
				new VolleyListener()
				{
					
					@Override
					public void onResponse(String arg0)
					{
//						 Cainixihuan cainixihuan = GsonUtils.parseJSON(
//								arg0, Cainixihuan.class);
//						  List<Goodlist> goodlist = cainixihuan.getGoodlist();
						  
						Type type = new TypeToken<ArrayList<HeaderType>>(){}.getType(); 
//						List<HeaderType> mData2 = GsonUtils.parseJSONArray(arg0, type);
					  List<HeaderType> headerType = GsonUtils.parseJSONArray(arg0, type);
						
						
//						HeaderType headerType = GsonUtils.parseJSONArray(arg0, HeaderType.class); 
						  
								 
						mData.clear();
						mData.addAll(headerType);
//						catalog = "news";
						adapter.notifyDataSetChanged();
//						mPullToRefreshListView.onRefreshComplete();
					}
					
					@Override
					public void onErrorResponse(VolleyError arg0)
					{
						Toast.makeText(getActivity(), "下载数据失败",
								Toast.LENGTH_SHORT).show();
//						mPullToRefreshListView.onRefreshComplete();
						
					}
				});
		
	}

	class MyAdapter extends BaseAdapter
	{
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
//			return mData.size();
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
			View view = null;
			ViewHolder viewHolder = null;
			if (view == null)
			{
				
				view = getActivity().getLayoutInflater().inflate(
						R.layout.gridview_item, null);
				
				viewHolder = new ViewHolder();
				viewHolder.iv_logo = (ImageView) view
						.findViewById(R.id.iv_logo);
				viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
				view.setTag(viewHolder);
			} else
			{
				view = convertView;
				viewHolder = (ViewHolder) view.getTag();
			}
			if(BannerItemFragment.this.position==1){
				position+=8;
			}
			HeaderType headerType = (HeaderType) mData.get(position);
			viewHolder.tv_name .setText(headerType.getCateName());
			UILUtils.displayImage(headerType.getImgUrl(), viewHolder.iv_logo);
			return view;
		}
		
	}
	
	class ViewHolder
	{
		ImageView iv_logo;
		TextView tv_name;
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("BannerItemFragment"); // 统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
//		MobclickAgent.onResume(this); // 统计时长
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("BannerItemFragment"); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证
													// onPageEnd 在onPause
													// 之前调用,因为 onPause 中会保存信息
//		MobclickAgent.onPause(this);
	}
	
}
