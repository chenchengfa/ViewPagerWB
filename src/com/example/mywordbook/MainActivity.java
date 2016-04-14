package com.example.mywordbook;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private LayoutInflater mInflater;
	private View v_top, v_bottom;
	private Fragment fg_wordbook, fg_study, fg_test, fg_ec, fg_more;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	private FragmentPagerAdapter mFrPagerAdapter;
	private RadioButton rb_wordbook, rb_study, rb_test, rb_ec, rb_more;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mInflater = LayoutInflater.from(this);
		initUI();

	}

	private OnCheckedChangeListener mChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked) {
				switch (buttonView.getId()) {
				case R.id.id_rb_wordbook:
					setSelect(0);
					break;
				case R.id.id_rb_study:
					setSelect(1);
					break;
				case R.id.id_rb_test:
					setSelect(2);
					break;
				case R.id.id_rb_ec:
					setSelect(3);
					break;
				case R.id.id_rb_more:
					setSelect(4);
					break;
				default:
					break;
				}
			}

		}
	};

	private void initUI() {
		mViewPager = (ViewPager) findViewById(R.id.mviewpager);
		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		v_bottom = findViewById(R.id.item_bottom);
		v_top = findViewById(R.id.item_top);

		rb_wordbook = ((RadioButton) v_bottom.findViewById(R.id.id_rb_wordbook));
		rb_study = ((RadioButton) v_bottom.findViewById(R.id.id_rb_study));
		rb_test = ((RadioButton) v_bottom.findViewById(R.id.id_rb_test));
		rb_ec = ((RadioButton) v_bottom.findViewById(R.id.id_rb_ec));
		rb_more = ((RadioButton) v_bottom.findViewById(R.id.id_rb_more));

		rb_wordbook.setOnCheckedChangeListener(mChangeListener);
		rb_study.setOnCheckedChangeListener(mChangeListener);
		rb_test.setOnCheckedChangeListener(mChangeListener);
		rb_ec.setOnCheckedChangeListener(mChangeListener);
		rb_more.setOnCheckedChangeListener(mChangeListener);

		setSelect(0);
		rb_wordbook.setChecked(true);

		fg_wordbook = new WbFragment();
		fg_study = new LwFragment();
		fg_test = new TestFragment();
		fg_ec = new SearchFragment();
		fg_more = new MoreFragment();
		mFragments.add(fg_wordbook);
		mFragments.add(fg_study);
		mFragments.add(fg_test);
		mFragments.add(fg_ec);
		mFragments.add(fg_more);

		mFrPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFragments.get(arg0);
			}
		};
		mViewPager.setAdapter(mFrPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				setSelect(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setSelect(int i) {
		initRadioButton();
		mViewPager.setCurrentItem(i);
		switch (i) {
		case 0:
			rb_wordbook.setChecked(true);
			break;
		case 1:
			rb_study.setChecked(true);
			break;
		case 2:
			rb_test.setChecked(true);
			break;
		case 3:
			rb_ec.setChecked(true);
			break;
		case 4:
			rb_more.setChecked(true);
			break;
		default:
			break;
		}
	}
	
	private void initRadioButton(){
		rb_ec.setChecked(false);
		rb_more.setChecked(false);
		rb_study.setChecked(false);
		rb_test.setChecked(false);
		rb_wordbook.setChecked(false);
	}

}
