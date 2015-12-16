package com.lashou.www.baidumap;

import java.util.ArrayList;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.InfoWindow.OnInfoWindowClickListener;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

 

import com.sono.www.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CoveringActivity extends Activity
{private InfoWindow mInfoWindow;
// 初始化全局 bitmap 信息，不用时及时 recycle
BitmapDescriptor bdA = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
BitmapDescriptor bdB = BitmapDescriptorFactory.fromResource(R.drawable.icon_markb);
BitmapDescriptor bdC = BitmapDescriptorFactory.fromResource(R.drawable.icon_markc);
BitmapDescriptor bdD = BitmapDescriptorFactory.fromResource(R.drawable.icon_markd);
BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
private Marker mMarkerA;
private Marker mMarkerB;
private Marker mMarkerC;
private Marker mMarkerD;
boolean isFirstLoc = true;// 是否首次定位
public MyLocationListenner myListener = new MyLocationListenner();
private MapView mMapView;
private BaiduMap mBaiduMap;
private LocationClient mLocClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_covering);
		startLocation();
		// 设置地图比例尺
		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
		mBaiduMap.setMapStatus(msu);

		// initOverlay();
	/*	addOverlay(39.963175, 116.400244, R.drawable.icon_marka);
		addOverlay(39.942821, 116.369199, R.drawable.icon_markb);
		addOverlay(39.939723, 116.425541, R.drawable.icon_markc);
		addOverlay(39.906965, 116.401394, R.drawable.icon_markd);*/
		
		addOverlay(24.476337,118.12102, R.drawable.icon_marka);
		addOverlay(24.476559,118.12077, R.drawable.icon_markb);
		addOverlay(24.475904,118.121456, R.drawable.icon_markc);
		addOverlay(24.476068,118.120805, R.drawable.icon_markd);

		initOverlayListener();
	}
	
	private void initOverlayListener() {
		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(final Marker marker) {
				// TODO 参考这种实现方法
				Button button = new Button(getApplicationContext());
				button.setBackgroundResource(R.drawable.popup);
				button.setText("更改图标");
				button.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						marker.setIcon(bd);
						mBaiduMap.hideInfoWindow();
					}
				});
				LatLng ll = marker.getPosition();
				mInfoWindow = new InfoWindow(button, ll, -47);
				mBaiduMap.showInfoWindow(mInfoWindow);
				return true;
			}
		});
	}

	private void addOverlay(double lat, double lng, int iconMarka) {
		LatLng ll = new LatLng(lat, lng);
		BitmapDescriptor bmpDes = 
			BitmapDescriptorFactory.fromResource(iconMarka);
		MarkerOptions oo = new MarkerOptions()
			.position(ll).icon(bmpDes);
		mBaiduMap.addOverlay(oo);
	}

	private void initOverlay() {

		// add marker overlay
		LatLng llA = new LatLng(39.963175, 116.400244);
		LatLng llB = new LatLng(39.942821, 116.369199);
		LatLng llC = new LatLng(39.939723, 116.425541);
		LatLng llD = new LatLng(39.906965, 116.401394);

		MarkerOptions ooA = new MarkerOptions().position(llA).icon(bdA).zIndex(9).draggable(true);
		mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
		MarkerOptions ooB = new MarkerOptions().position(llB).icon(bdB).zIndex(5);
		mMarkerB = (Marker) (mBaiduMap.addOverlay(ooB));
		MarkerOptions ooC = new MarkerOptions().position(llC).icon(bdC).perspective(false).anchor(0.5f, 0.5f).rotate(30)
				.zIndex(7);
		mMarkerC = (Marker) (mBaiduMap.addOverlay(ooC));
		ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
		giflist.add(bdA);
		giflist.add(bdB);
		giflist.add(bdC);
		MarkerOptions ooD = new MarkerOptions().position(llD).icons(giflist).zIndex(0).period(10);
		mMarkerD = (Marker) (mBaiduMap.addOverlay(ooD));

		// add ground overlay
		LatLng southwest = new LatLng(39.92235, 116.380338);
		LatLng northeast = new LatLng(39.947246, 116.414977);
		LatLngBounds bounds = new LatLngBounds.Builder().include(northeast).include(southwest).build();

		MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(bounds.getCenter());
		mBaiduMap.setMapStatus(u);

		mBaiduMap.setOnMarkerDragListener(new OnMarkerDragListener() {
			public void onMarkerDrag(Marker marker) {
			}

			public void onMarkerDragEnd(Marker marker) {
				Toast.makeText(CoveringActivity.this,
						"拖拽结束，新位置：" + marker.getPosition().latitude + ", " + marker.getPosition().longitude,
						Toast.LENGTH_LONG).show();
			}

			public void onMarkerDragStart(Marker marker) {
			}
		});
	}

	private void startLocation() {
		mMapView = (MapView) findViewById(R.id.bmapView);
		// 地图初始化
		mBaiduMap = mMapView.getMap();
		// 开启定位图层
		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		  mLocClient.start();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null) {
				return;
			}
			MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}

	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
		mMapView = null;
		// 回收 bitmap 资源
		bdA.recycle();
		bdB.recycle();
		bdC.recycle();
		bdD.recycle();
		bd.recycle();
	}
}
