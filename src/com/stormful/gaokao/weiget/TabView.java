package com.stormful.gaokao.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stormful.gaokao.R;
/**
 * @author Hugo
 *
 */
public class TabView extends LinearLayout implements OnClickListener {

	private OnTabChangeListener mOnTabChangedListener;

	private int mState = -1;

	private final TextView mStateButton1;
	private final TextView mStateButton2;
	public TabView(Context context) {
		this(context, null);
	}

	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setGravity(Gravity.BOTTOM);
		inflate(context, R.layout.guide_view_tab, this);
		mStateButton1 = (TextView) findViewById(R.id.button_state1);
		mStateButton2 = (TextView) findViewById(R.id.button_state2);
		mStateButton1.setOnClickListener(this);
		mStateButton2.setOnClickListener(this);
	}

	public void setOnTabChangeListener(OnTabChangeListener listener) {
		mOnTabChangedListener = listener;
	}

	public int getCurrentIndex(){
		if(mState <0) return 0;
		return mState;
	}
	public void setCurrentTab(int index) {
		switchState(index);
	}

	private void switchState(int state) {
		if (mState == state) {
			return;
		}
		mState = state;
		mStateButton1.setSelected(false);
		mStateButton2.setSelected(false);


		switch (mState) {
			case 0:
				mStateButton1.setSelected(true);
				break;

			case 1:
				mStateButton2.setSelected(true);
				break;
		}

		if (mOnTabChangedListener != null) {
			mOnTabChangedListener.onTabChange(state);
		} // else ignored
	}
	public void changeButton(int state) {
		if (mState == state) {
			return;
		}
		mStateButton1.setSelected(false);
		mStateButton2.setSelected(false);
		switch (state) {
			case 0:
				mStateButton1.setSelected(true);
				break;
			case 1:
				mStateButton2.setSelected(true);
				break;
		}
		mState = state;
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button_state1:
				switchState(0);
				break;

			case R.id.button_state2:
				switchState(1);
				break;
			default:
				break;
		}
	}

	public static interface OnTabChangeListener {
		public void onTabChange(int index);
	}
}
