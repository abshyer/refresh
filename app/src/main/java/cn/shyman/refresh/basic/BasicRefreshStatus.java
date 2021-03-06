package cn.shyman.refresh.basic;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import cn.shyman.library.refresh.RefreshLayout;
import cn.shyman.library.refresh.RefreshStatus;
import cn.shyman.refresh.R;
import cn.shyman.refresh.bean.StatusInfo;

public class BasicRefreshStatus extends FrameLayout implements RefreshStatus<StatusInfo> {
	private TextView mTVStatus;
	
	private RefreshLayout.OnRefreshListener onRefreshListener;
	
	public BasicRefreshStatus(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mTVStatus = (TextView) findViewById(R.id.tvStatus);
		mTVStatus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onRefreshListener != null) {
					onRefreshListener.notifyRefresh();
				}
			}
		});
	}
	
	@Override
	public void initOnRefreshListener(RefreshLayout.OnRefreshListener listener) {
		
	}
	
	@Override
	public void onRefreshScale(float scale) {
		
	}
	
	@Override
	public void onRefreshReady() {
		mTVStatus.setText("ready");
	}
	
	@Override
	public void onRefresh() {
		mTVStatus.setText("refreshing");
		// System.out.println("refreshing");
	}
	
	@Override
	public boolean onRefreshComplete(StatusInfo statusInfo) {
		if (statusInfo == null) {
			mTVStatus.setText("refreshError");
			return true;
		} else if (TextUtils.isEmpty(statusInfo.statusContent)) {
			mTVStatus.setText("refreshFailure");
		} else {
			mTVStatus.setText(statusInfo.statusContent);
		}
		return !statusInfo.isSuccessful();
	}
	
}
