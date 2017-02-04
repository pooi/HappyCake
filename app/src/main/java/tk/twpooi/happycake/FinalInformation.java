package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sungmin on 2017-02-02.
 */
public class FinalInformation extends BaseActivity {

    private RelativeLayout root;

    private ImageView back;
    private ImageView goToNext;
    private ImageView img;
    private TextView showListBtn;

    private HashMap<String, Object> data;
    private ArrayList<Point> strawPointList;
    private ArrayList<Point> grapPointList;
    private ArrayList<Point> bluePointList;
    private Point cakePoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalinformation);

        Intent intent = getIntent();
        data = (HashMap<String, Object>)intent.getSerializableExtra("data");
        HashMap<String, Object> map = (HashMap<String, Object>)intent.getSerializableExtra("cake");
        cakePoint = (Point)map.get("cake");
        strawPointList = (ArrayList<Point>)map.get("straw");
        grapPointList = (ArrayList<Point>)map.get("grap");
        bluePointList = (ArrayList<Point>)map.get("blue");

        root = (RelativeLayout)findViewById(R.id.root);

        img = (ImageView)findViewById(R.id.img);
        back = (ImageView) findViewById(R.id.previous_btn);
        goToNext = (ImageView) findViewById(R.id.next_btn);
        showListBtn = (TextView)findViewById(R.id.show_list_btn);
        showListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListDialog();
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        CheckPayActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        drawCake();
    }

    public void showListDialog() {
        String shop = (String)data.get("shop");
        String cakeTitle = String.format("%s %s베이스 생크림 케이크", (String)data.get("size"), (String)data.get("spon"));
        String straw = String.format("토핑 - 딸기 %d개", strawPointList.size());
        String grap = String.format("토핑 - 거봉 %d개", grapPointList.size());
        String blue = String.format("토핑 - 블루베리 %d개", bluePointList.size());
        final String[] list = {
                cakeTitle,
                straw,
                grap,
                blue
        };
        final NormalListDialog dialog = new NormalListDialog(this, list);
        dialog.title(shop)
                .titleBgColor(ContextCompat.getColor(getApplicationContext(), R.color.yellow))
                .titleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black))
                .titleTextSize_SP(14.5f)
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
            }
        });

    }

    private void drawCake(){

        int minus = 0;

        ImageView cakeImage = new ImageView(getApplicationContext());
        cakeImage.setImageResource(R.drawable.bigcake_01);
        cakeImage.setScaleType(ImageView.ScaleType.CENTER_CROP);

        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
        param.leftMargin = cakePoint.x; //XCOORD
        param.topMargin = cakePoint.y - minus; //YCOORD
        cakeImage.setLayoutParams(param);

        final float scale =getResources().getDisplayMetrics().density;
        int dp = (int) (120 * scale * 0.5f);
        cakeImage.setPadding(0,dp,0,0);

        root.addView(cakeImage);

        for(Point p : strawPointList){
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(R.drawable.fr_01);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
            params.leftMargin = p.x; //XCOORD
            params.topMargin = p.y - minus; //YCOORD
            image.setLayoutParams(params);

            root.addView(image);
        }

        for(Point p : grapPointList){
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(R.drawable.fr_02);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
            params.leftMargin = p.x; //XCOORD
            params.topMargin = p.y - minus; //YCOORD
            image.setLayoutParams(params);

            root.addView(image);
        }

        for(Point p : bluePointList){
            ImageView image = new ImageView(getApplicationContext());
            image.setImageResource(R.drawable.fr_03);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
            params.leftMargin = p.x; //XCOORD
            params.topMargin = p.y - minus; //YCOORD
            image.setLayoutParams(params);

            root.addView(image);
        }

    }

}
