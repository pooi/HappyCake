package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;

public class BreadActivity extends AppCompatActivity {

    public static final int SELECT_SIZE = 1000;
    public static final int SELECT_SPON = 1001;

    // UI
    private RelativeLayout sizeSelectField;
    private RelativeLayout sponSelectField;
    private PagerContainer sizeViewPagerContainer;
    private CustomViewPager sizeViewPager;
    private SizeNavigationAdapter mSizePagerAdapter;
    private DotIndicator sizeDotIndicator;
    private PagerContainer sponViewPagerContainer;
    private CustomViewPager sponViewPager;
    private SponNavigationAdapter mSponPagerAdapter;
    private DotIndicator sponDotIndicator;
    private Button previousBtn;
    private Button nextBtn;

    // Data
    private String[] sizeTitleList = {
            "1호",
            "2호",
            "3호"
    };
    private String[] sizeContentList = {
            "1호 사이즈",
            "2호 사이즈",
            "3호 사이즈"
    };
    private String[] sponTitleList = {
            "일반",
            "딸기",
            "초코"
    };
    private String[] sponContentList = {
            "일반 설명",
            "딸기 설명",
            "초코 설명"
    };

    private int SIZE = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread);


        InitUI();

        InitData();


    }

    private void SetFadeInAnimation(View view){
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(1000);
        view.setAnimation(animation);
    }

    private void SetFadeOutAnimation(View view){
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        view.setAnimation(animation);
    }

    private void InitData(){



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
                Intent intent = new Intent(
                        getApplicationContext(),
                        FinalInformation.class);
                startActivity(intent);
            }
        });
        nextBtn.setVisibility(View.GONE);

        SetViewPager();

    }

    private void SetViewPager(){

        sizeViewPagerContainer = (PagerContainer)findViewById(R.id.size_view_pager_container);
        sizeSelectField = (RelativeLayout)findViewById(R.id.size_select_field);
        sizeDotIndicator = (DotIndicator)findViewById(R.id.size_indicator_ad);
        InitIndicator(sizeDotIndicator, SIZE);
        sizeViewPager = (CustomViewPager)sizeViewPagerContainer.getViewPager();//findViewById(R.id.size_view_pager);
        sizeViewPager.setOffscreenPageLimit(SIZE);
        mSizePagerAdapter = new SizeNavigationAdapter(getSupportFragmentManager(), sizeTitleList, sizeContentList);
        sizeViewPager.setAdapter(mSizePagerAdapter);
        sizeViewPager.setPageMargin(15);
        sizeViewPager.setClipChildren(false);

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

        sponViewPagerContainer = (PagerContainer)findViewById(R.id.spon_view_pager_container);
        sponSelectField = (RelativeLayout)findViewById(R.id.spon_select_field);
        sponSelectField.setVisibility(View.GONE);
        sponDotIndicator = (DotIndicator)findViewById(R.id.spon_indicator_ad);
        InitIndicator(sponDotIndicator, SIZE);
        sponViewPager = (CustomViewPager)sponViewPagerContainer.getViewPager();//findViewById(R.id.spon_view_pager);
        sponViewPager.setOffscreenPageLimit(SIZE);
        mSponPagerAdapter = new SponNavigationAdapter(getSupportFragmentManager(), sponTitleList, sponContentList);
        sponViewPager.setAdapter(mSponPagerAdapter);
        sponViewPager.setPageMargin(15);
        sponViewPager.setClipChildren(false);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SELECT_SIZE:
                sizeSelectField.setVisibility(View.GONE);
                SetFadeOutAnimation(sizeSelectField);
                sponSelectField.setVisibility(View.VISIBLE);
                SetFadeInAnimation(sponSelectField);
                break;
            case SELECT_SPON:
                sizeSelectField.setVisibility(View.VISIBLE);
                SetFadeInAnimation(sizeSelectField);
                nextBtn.setVisibility(View.VISIBLE);
                SetFadeInAnimation(nextBtn);
                break;
            default:
                break;
        }
    }


    private static class SizeNavigationAdapter extends CacheFragmentStatePagerAdapter {

        private String[] list;
        private String[] contentList;

        public SizeNavigationAdapter(FragmentManager fm, String[] list, String[] contentList){
            super(fm);
            this.list = list;
            this.contentList = contentList;
        }

        @Override
        protected Fragment createItem(int position){
            Fragment f;
            final int pattern = position %list.length;

            f = new BreadFragment();
            Bundle bdl = new Bundle(1);
            bdl.putInt("position", pattern);
            bdl.putString("title", list[pattern]);
            bdl.putString("content", contentList[pattern]);
            bdl.putInt("code", SELECT_SIZE);
            f.setArguments(bdl);

            return f;
        }

        @Override
        public int getCount(){
            return list.length;
        }

    }

    private static class SponNavigationAdapter extends CacheFragmentStatePagerAdapter {

        private String[] list;
        private String[] contentList;

        public SponNavigationAdapter(FragmentManager fm, String[] list, String[] contentList){
            super(fm);
            this.list = list;
            this.contentList = contentList;
        }

        @Override
        protected Fragment createItem(int position){
            Fragment f;
            final int pattern = position %list.length;

            f = new BreadFragment();
            Bundle bdl = new Bundle(1);
            bdl.putInt("position", pattern);
            bdl.putString("title", list[pattern]);
            bdl.putString("content", contentList[pattern]);
            bdl.putInt("code", SELECT_SPON);
            f.setArguments(bdl);

            return f;
        }

        @Override
        public int getCount(){
            return list.length;
        }


    }
}
