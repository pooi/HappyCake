package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.ksoichiro.android.observablescrollview.CacheFragmentStatePagerAdapter;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

public class BreadPopupActivity extends AppCompatActivity {

    // UI
    private RelativeLayout root;
    private ImageView img;
    private Button selectBtn;

    // DATA
    private int resultCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread_popup);


        InitUI();

        InitData();


    }

    private void InitData(){


        Intent intent = getIntent();
        resultCode = intent.getIntExtra("code", -1);

    }

    private void InitUI(){

        root = (RelativeLayout)findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        img = (ImageView)findViewById(R.id.img);
        img.setImageResource(R.mipmap.ic_launcher);
        selectBtn = (Button)findViewById(R.id.select_btn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(resultCode);
                finish();
            }
        });

    }

}
