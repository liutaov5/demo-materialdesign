package com.lt.test;


import com.example.test.R;
import com.example.test.R.id;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	private TextInputLayout mTextInpute;
	private FloatingActionButton mButton;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mTextInpute=(TextInputLayout)findViewById(R.id.textInput);
    mButton=(FloatingActionButton)findViewById(R.id.floatingbtn);
    
    mTextInpute.setHint("请输入姓名：");
    EditText editText=mTextInpute.getEditText();
    editText.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			if(s.length()>4){
				mTextInpute.setErrorEnabled(true);
				mTextInpute.setError("姓名长度不能超过4个");
			}else{
				mTextInpute.setErrorEnabled(false);
			}
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			
		}
	});
    
    mButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent(MainActivity.this, ActivityTab.class);
			startActivity(intent);
		}
	});

    // App Logo
    mToolbar.setLogo(R.drawable.ic_launcher);
    // Title
    mToolbar.setTitle("My Title");
    // Sub Title
    mToolbar.setSubtitle("Sub title");

    setSupportActionBar(mToolbar);

    // Navigation Icon 要設定在 setSupoortActionBar 才有作用
    // 否則會出現 back bottom
    mToolbar.setNavigationIcon(R.drawable.ab_android);
    // Menu item click 的監聽事件一樣要設定在 setSupportActionBar 才有作用
    mToolbar.setOnMenuItemClickListener(onMenuItemClick);
  }

  private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
      String msg = "";
      switch (menuItem.getItemId()) {
        case R.id.action_edit:
          msg += "Click edit";
          break;
        case R.id.action_share:
          msg += "Click share";
          final Snackbar snackbar=Snackbar.make(mTextInpute, "Snackbar测试", Snackbar.LENGTH_SHORT);
          snackbar.show();
          snackbar.setAction("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				snackbar.dismiss();
			}
		});
          break;
        case R.id.action_settings:
          msg += "Click setting";
          break;
      }

      if(!msg.equals("")) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
      }
      return true;
    }
  };


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
}
