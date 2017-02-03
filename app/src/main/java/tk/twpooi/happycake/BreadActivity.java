package tk.twpooi.happycake;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.ArrayList;
import java.util.HashMap;

public class BreadActivity extends BaseActivity {

    public static final int SELECT_SIZE = 1000;
    public static final int SELECT_SPON = 1001;

    // UI
    private ImageView sizeBackground;
    private ImageView sponBackground;
    private FrameLayout sizeSelectField;
    private FrameLayout sponSelectField;
    private PagerContainer sizeViewPagerContainer;
    private CustomViewPager sizeViewPager;
    private SizeNavigationAdapter mSizePagerAdapter;
    private DotIndicator sizeDotIndicator;
    private PagerContainer sponViewPagerContainer;
    private CustomViewPager sponViewPager;
    private SponNavigationAdapter mSponPagerAdapter;
    private DotIndicator sponDotIndicator;
    private ImageView previousBtn;
    private ImageView nextBtn;

    // Data
    private HashMap<String, Object> data;
    private String size;
    private String spon;
    private String price;
    public static String[] priceList = {
            "18,000원",
            "26,000원",
            "32,000원"
    };
    private String[] sizeTitleList = {
            "1호",
            "2호",
            "3호"
    };
    private String[] sizeContentList = {
            "지름 15cm X 높이 6cm",
            "지름 17cm X 높이 5cm",
            "지름 20.5cm X 높이 5cm"
    };
    private String[] sponTitleList = {
            "일반",
            "딸기",
            "초코"
    };
    private String[] sponContentList = {
            "기본적인 빵으로 만들어진 베이스입니다.",
            "기본적인 빵으로 만들어진 베이스입니다.",
            "기본적인 빵으로 만들어진 베이스입니다."
    };

    public static boolean isSelectFinish;
    private int[] sizeResourceList = {
            R.drawable.cake_size_01,
            R.drawable.cake_size_02,
            R.drawable.cake_size_03
    };
    private int[] sponResourceList = {
            R.drawable.spon_default,
            R.drawable.spon_straw,
            R.drawable.spon_choco
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread);

        Intent intent = getIntent();
        data = (HashMap<String, Object>)intent.getSerializableExtra("data");

        InitData();

        InitUI();



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

        isSelectFinish = false;

    }

    private void InitUI(){

        previousBtn = (ImageView)findViewById(R.id.previous_btn);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nextBtn = (ImageView)findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("size", size);
                data.put("spon", spon);
                data.put("price", price);
                Intent intent = new Intent(
                        getApplicationContext(),
                        FinalInformation.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        nextBtn.setVisibility(View.GONE);

        SetViewPager();

    }

    private void SetViewPager(){

        sizeBackground = (ImageView)findViewById(R.id.size_bg);
        sizeViewPagerContainer = (PagerContainer)findViewById(R.id.size_view_pager_container);
        sizeSelectField = (FrameLayout)findViewById(R.id.size_select_field);
        sizeDotIndicator = (DotIndicator)findViewById(R.id.size_indicator_ad);
        InitIndicator(sizeDotIndicator, sizeResourceList.length);
        sizeViewPager = (CustomViewPager)sizeViewPagerContainer.getViewPager();//findViewById(R.id.size_view_pager);
        sizeViewPager.setOffscreenPageLimit(sizeResourceList.length);
        mSizePagerAdapter = new SizeNavigationAdapter(getSupportFragmentManager(), sizeTitleList, sizeContentList, sizeResourceList);
        sizeViewPager.setAdapter(mSizePagerAdapter);
//        sizeViewPager.setPageMargin(15);
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

        sponBackground = (ImageView)findViewById(R.id.spon_bg);
        sponViewPagerContainer = (PagerContainer)findViewById(R.id.spon_view_pager_container);
        sponSelectField = (FrameLayout)findViewById(R.id.spon_select_field);
        sponSelectField.setVisibility(View.GONE);
        sponDotIndicator = (DotIndicator)findViewById(R.id.spon_indicator_ad);
        InitIndicator(sponDotIndicator, sponResourceList.length);
        sponViewPager = (CustomViewPager)sponViewPagerContainer.getViewPager();//findViewById(R.id.spon_view_pager);
        sponViewPager.setOffscreenPageLimit(sponResourceList.length);
        mSponPagerAdapter = new SponNavigationAdapter(getSupportFragmentManager(), sponTitleList, sponContentList, sponResourceList);
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


        sizeBackground.setVisibility(View.VISIBLE);
        sponBackground.setVisibility(View.VISIBLE);

    }

    private void InitIndicator(DotIndicator dotIndicator, int size){
        dotIndicator.setSelectedDotColor(Color.parseColor("#FF4081"));
        dotIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));
        dotIndicator.setNumberOfItems(size);
        dotIndicator.setSelectedItem(0, true);
    }

    private void ScrollDisable(boolean check){

        sizeViewPager.setPagingEnabled(!check);
        sponViewPager.setPagingEnabled(!check);
        sizeDotIndicator.setVisibility(!check);
        sponDotIndicator.setVisibility(!check);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SELECT_SIZE:
                size = sizeTitleList[data.getIntExtra("position", 0)];
                price = priceList[data.getIntExtra("position", 0)];
                sizeSelectField.setVisibility(View.GONE);
                SetFadeOutAnimation(sizeSelectField);
                sponSelectField.setVisibility(View.VISIBLE);
                SetFadeInAnimation(sponSelectField);
                break;
            case SELECT_SPON:
                spon = sponTitleList[data.getIntExtra("position", 0)];
                sizeBackground.setVisibility(View.GONE);
                sponBackground.setVisibility(View.GONE);
                sizeSelectField.setVisibility(View.VISIBLE);
                SetFadeInAnimation(sizeSelectField);
                nextBtn.setVisibility(View.VISIBLE);
                SetFadeInAnimation(nextBtn);
                isSelectFinish = true;
                ScrollDisable(isSelectFinish);
                break;
            default:
                break;
        }
    }


    private static class SizeNavigationAdapter extends CacheFragmentStatePagerAdapter {

        private String[] list;
        private String[] contentList;
        private int[] resourceList;

        public SizeNavigationAdapter(FragmentManager fm, String[] list, String[] contentList, int[] resourceList){
            super(fm);
            this.list = list;
            this.contentList = contentList;
            this.resourceList = resourceList;
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
            bdl.putInt("resource", resourceList[pattern]);
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
        private int[] resourceList;

        public SponNavigationAdapter(FragmentManager fm, String[] list, String[] contentList, int[] resourceList){
            super(fm);
            this.list = list;
            this.contentList = contentList;
            this.resourceList = resourceList;
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
            bdl.putInt("resource", resourceList[pattern]);
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
