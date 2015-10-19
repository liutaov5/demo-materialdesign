package com.lt.test;

import java.util.ArrayList;
import java.util.List;

import com.example.test.R;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ActivityTab extends AppCompatActivity{

	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private List<Fragment> mContents;
	private List<String> mTabs;
	private FragmentPagerAdapter mAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		initView();
		initData();
	}
	
	private void initData() {
		for(int i=1;i<6;++i){
			mTabs.add("Tab"+i);
			Fragment fragment=new Fragment1();
			Bundle bundle=new Bundle();
			bundle.putString("content", "Tab"+i);
			fragment.setArguments(bundle);
			mContents.add(fragment);
		}
		mViewPager.setAdapter(mAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.setTabsFromPagerAdapter(mAdapter);
	}
	
	private void initView() {
		mTabLayout=(TabLayout)findViewById(R.id.tab);
		mTabLayout.setTabMode(TabLayout.MODE_FIXED);
		mViewPager=(ViewPager)findViewById(R.id.viewpager);
		mTabs=new ArrayList<String>();
		mContents=new ArrayList<Fragment>();
		mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return mContents.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				return mContents.get(arg0);
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return mTabs.get(position);
			}
		};
	}

	
}
