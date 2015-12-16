package com.lashou.www.utils;

import com.sono.www.R;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class CustomDialog extends DialogFragment {
	private String content;

	/**
	 * @param tv_title
	 */
	public CustomDialog(String content)
	{
		super();
		this.content = content;
		 
		 
	}

	private onConfirmListener listener;
	private TextView tv_title;
	private TextView tv_content;
	
	public interface onConfirmListener{
		void onConfirm();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//设置dialog没有标题栏
		getDialog().requestWindowFeature(STYLE_NO_TITLE);
		View view=inflater.inflate(R.layout.layout_dialog, null);
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_content.setText(content);
		Button btn_confirm=(Button) view.findViewById(R.id.btn_confirm);
		
		btn_confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (listener!=null) {
					listener.onConfirm();
				}else{
//					Toast.makeText(getActivity(), "请先设置点击监听", Toast.LENGTH_SHORT).show();
				}
				//设置dialog消失
				dismiss();
//				getActivity().finish();
			}
		});
		
		view.findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
 			dismiss();
// 			getActivity().finish();
				
			}
		});
		return view;
	}
	
	public void setOnConfirmListener(onConfirmListener l){
		listener=l;
	}

}
