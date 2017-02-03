package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BreadPopupActivity extends BaseActivity {

    // UI
    private RelativeLayout root;
    private ImageView img;
    private Button selectBtn;
    private TextView tv_title;
    private TextView tv_sizeText;
    private TextView tv_priceText;

    // DATA
    private int resultCode;
    private int resourceId;
    private int position;
    private String title;
    private String content;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread_popup);


        InitData();

        InitUI();


    }

    private void InitData(){


        Intent intent = getIntent();
        resultCode = intent.getIntExtra("code", -1);
        resourceId = intent.getIntExtra("resource", -1);
        position = intent.getIntExtra("position", 0);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        price = intent.getStringExtra("price");

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
        img.setImageResource(resourceId);
        selectBtn = (Button)findViewById(R.id.select_btn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(resultCode, intent);
                finish();
            }
        });
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_sizeText = (TextView)findViewById(R.id.tv_size_text);
        tv_sizeText.setText(content);
        tv_priceText = (TextView)findViewById(R.id.tv_price_text);
        if(price != null){
            tv_priceText.setText("가격 : " + price);
        }else{
            tv_priceText.setVisibility(View.GONE);
        }


    }

}
