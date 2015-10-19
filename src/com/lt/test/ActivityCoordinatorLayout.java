package com.lt.test;

import com.example.test.R;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityCoordinatorLayout extends AppCompatActivity implements Toolbar.OnMenuItemClickListener{

	private Toolbar mToolbar;
	private RecyclerView mRecyclerVeiw;
	private MyAdapter mAdapter;
	private FloatingActionButton mButton;
	private CollapsingToolbarLayout mCollapsingToolbarLayout;
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavigationView;
	private TabLayout mTabLayout;
	private CoordinatorLayout mCoordinatorLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_navigationview);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mRecyclerVeiw = (RecyclerView) findViewById(R.id.recyclerView);
		mButton=(FloatingActionButton)findViewById(R.id.coor_floatingbtn);
		mTabLayout=(TabLayout)findViewById(R.id.tabs);
		mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
		mNavigationView=(NavigationView)findViewById(R.id.navigation_view);
		mCoordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinator);
		mTabLayout.addTab(mTabLayout.newTab().setText("TAB1"));
		mTabLayout.addTab(mTabLayout.newTab().setText("TAB2"));
//		mCollapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toobar);
//		mCollapsingToolbarLayout.setTitle("Title");
		String[] data = { "sss", "ass", "sss", "ass", "ass", "sss", "ass",
				"sss", "ass", "sss", "ass", "sss", "ass", "sss", "ass", "sss",
				"ass", "sss", "ass", "sss", "ass", "sss", "ass", "sss", "ass",
				"sss", "ass", "sss", "ass", "sss" };
		mAdapter = new MyAdapter(data);
		mRecyclerVeiw.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerVeiw.setAdapter(mAdapter);
		mToolbar.setTitle("Title");
		mToolbar.setSubtitle("SubTitle");
		setSupportActionBar(mToolbar);
		mToolbar.setNavigationIcon(R.drawable.ab_android);
		mToolbar.setOnMenuItemClickListener(this);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Snackbar snackbar=Snackbar.make(mCoordinatorLayout, "Snackbar测试", Snackbar.LENGTH_SHORT);
		          snackbar.show();
		          snackbar.setAction("取消", new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						//不用添加dismiss(),否则会出现fab下不去的现象
//						snackbar.dismiss();
					}
				});
			}
		});
		mNavigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
			
			@Override
			public boolean onNavigationItemSelected(MenuItem menuItem) {
				menuItem.setChecked(true);
				mDrawerLayout.closeDrawers();
				return true;
			}
		});
	}

	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
		private String[] mDataset;

		public class ViewHolder extends RecyclerView.ViewHolder {
			public TextView mTextView;

			public ViewHolder(View v) {
				super(v);
				mTextView = (TextView) v.findViewById(R.id.textview);
			}
		}

		public MyAdapter(String[] myDataset) {
			mDataset = myDataset;
		}

		@Override
		public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
				int viewType) {
			View v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.my_text_view, parent, false);
			ViewHolder vh = new ViewHolder(v);
			return vh;
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			holder.mTextView.setText(mDataset[position]);

		}

		@Override
		public int getItemCount() {
			return mDataset.length;
		}
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.menu_main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();

	        //noinspection SimplifiableIfStatement
	        if (id == R.id.action_settings) {
	        	Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
	            return true;
	        }
	        if (id == android.R.id.home) {
	            mDrawerLayout.openDrawer(GravityCompat.START);
	            return true;
	        }

	        return super.onOptionsItemSelected(item);
	    }


	    @Override
	    public boolean onMenuItemClick(MenuItem item) {

	        switch (item.getItemId()) {
	            case R.id.action_edit:
	                Toast.makeText(this, "查找按钮", Toast.LENGTH_SHORT).show();
	                break;
	            case R.id.action_share:
	                Toast.makeText(this, "分享按钮", Toast.LENGTH_SHORT).show();
	                break;
	        }

	        return true;
	    }
	    
	    

}
