package tk.twpooi.happycake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;

public class BreadActivity extends AppCompatActivity {

    // UI
    private CustomViewPager sizeViewPager;
    private SizeNavigationAdapter mSizePagerAdapter;
    private DotIndicator sizeDotIndicator;
    private CustomViewPager sponViewPager;
    private SponNavigationAdapter mSponPagerAdapter;
    private DotIndicator sponDotIndicator;
    private Button previousBtn;
    private Button nextBtn;

    // Data
    private ImageView[] sizeImageList;
    private ImageView[] sponImageList;

    private int SIZE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread);


        InitUI();

        InitData();


    }

    private void InitData(){

        sizeImageList = new ImageView[3];
        sponImageList = new ImageView[3];

    }

    private void InitUI(){

        previousBtn = (Button)findViewById(R.id.previous_btn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextBtn = (Button)findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        SetViewPager();

    }

    private void SetViewPager(){

        sizeDotIndicator = (DotIndicator)findViewById(R.id.size_indicator_ad);
        InitIndicator(sizeDotIndicator, SIZE);
        sizeViewPager = (CustomViewPager)findViewById(R.id.size_view_pager);
        sizeViewPager.setOffscreenPageLimit(SIZE);
        mSizePagerAdapter = new SizeNavigationAdapter(getSupportFragmentManager(), SIZE);
        sizeViewPager.setAdapter(mSizePagerAdapter);

        sizeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sizeDotIndicator.setSelectedItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        sponDotIndicator = (DotIndicator)findViewById(R.id.spon_indicator_ad);
        InitIndicator(sponDotIndicator, SIZE);
        sponViewPager = (CustomViewPager)findViewById(R.id.spon_view_pager);
        sponViewPager.setOffscreenPageLimit(SIZE);
        mSponPagerAdapter = new SponNavigationAdapter(getSupportFragmentManager(), SIZE);
        sponViewPager.setAdapter(mSponPagerAdapter);

        sponViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sponDotIndicator.setSelectedItem(position, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void InitIndicator(DotIndicator dotIndicator, int size){
        dotIndicator.setSelectedDotColor(Color.parseColor("#FF4081"));
        dotIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));
        dotIndicator.setNumberOfItems(size);
        dotIndicator.setSelectedItem(0, true);
    }


    private static class SizeNavigationAdapter extends CacheFragmentStatePagerAdapter {

        private int size;

        public SizeNavigationAdapter(FragmentManager fm, int size){
            super(fm);
            this.size = size;
        }

        @Override
        protected Fragment createItem(int position){
            Fragment f;
            final int pattern = position %size;

            f = new BreadFragment();
            Bundle bdl = new Bundle(1);
            bdl.putInt("position", pattern);
            f.setArguments(bdl);

            return f;
        }

        @Override
        public int getCount(){
            return size;
        }

    }

    private static class SponNavigationAdapter extends CacheFragmentStatePagerAdapter {

        private int size;

        public SponNavigationAdapter(FragmentManager fm, int size){
            super(fm);
            this.size = size;
        }

        @Override
        protected Fragment createItem(int position){
            Fragment f;
            final int pattern = position %size;

            f = new BreadFragment();
            Bundle bdl = new Bundle(1);
            bdl.putInt("position", pattern);
            f.setArguments(bdl);

            return f;
        }

        @Override
        public int getCount(){
            return size;
        }

    }
}
