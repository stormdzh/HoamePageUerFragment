package com.stormful.gaokao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;

import com.stormful.gaokao.fragment.FragmentMain01;
import com.stormful.gaokao.fragment.FragmentMain02;
import com.stormful.gaokao.uitls.FragmentUtils;
import com.stormful.gaokao.weiget.TabView;
import com.stormful.gaokao.weiget.TabView.OnTabChangeListener;

public class MainActivity extends FragmentActivity implements
		OnTabChangeListener {

	private FrameLayout frame_content;
	public Fragment currentFragment;
	private Class datas[];
	private int index = 0;
	private TabView mTabView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		frame_content = (FrameLayout) findViewById(R.id.frame_content);
		mTabView = (TabView) findViewById(R.id.mTabView);
		mTabView.setOnTabChangeListener(this);
		datas = new Class[] { FragmentMain01.class, FragmentMain02.class };
		mTabView.setCurrentTab(0);
		currentFragment = FragmentUtils.switchFragment(
				getSupportFragmentManager(), R.id.frame_content,
				currentFragment, datas[index], false);
	}

	@Override
	public void onTabChange(int index) {
		currentFragment = FragmentUtils.switchFragment(
				getSupportFragmentManager(), R.id.frame_content,
				currentFragment, datas[index], false);
	}
}
