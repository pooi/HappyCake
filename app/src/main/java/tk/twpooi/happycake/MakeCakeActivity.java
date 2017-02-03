package tk.twpooi.happycake;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tw on 2017. 2. 3..
 */

public class MakeCakeActivity extends AppCompatActivity implements View.OnTouchListener, ControlCakeListener  {


    private static final int MAX_STRAW = 5;
    private static final int MAX_GRAP = 5;
    private static final int MAX_BLUE = 5;

    private MyHandler handler = new MyHandler();
    private final int MSG_MESSAGE_MAKE_UI = 500;

    private  RelativeLayout root;
    private FrameLayout defaultStrawImage;
    private FrameLayout defaultGrapImage;
    private FrameLayout defaultBlueImage;

    private Point strawPoint;
    private int strawCount = 3;
    private Point grapPoint;
    private int grapCount = 4;
    private Point bluePoint;
    private int blueCount = 2;

    private int ICON_COUNT = 3;

    private ArrayList<ImageView> straw;
    private ArrayList<ImageView> grap;
    private ArrayList<ImageView> blue;

    private ArrayList<ImageView> strawCompleteList;
    private ArrayList<ImageView> grapCompleteList;
    private ArrayList<ImageView> blueCompleteList;

    private TextView tv_straw;
    private TextView tv_grap;
    private TextView tv_blue;
//    private TextView tv_complete;

    float oldXvalue;
    float oldYvalue;
    private ImageView cake;

    private ProgressDialog progressDialog;


    // Recycle View
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;
    private MakeCakeCustomAdapter adapter;
    private ArrayList<HashMap<String, Object>> list;
    private LinearLayout li_optionField;
    private TextView showOptionBtn;

    // DATA
    private HashMap<String, Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_cake);

        Intent intent = getIntent();
        data = (HashMap<String, Object>)intent.getSerializableExtra("data");

        root = (RelativeLayout) findViewById(R.id.root);
        defaultStrawImage = (FrameLayout)findViewById(R.id.fm_straw);
        defaultGrapImage = (FrameLayout)findViewById(R.id.fm_grap);
        defaultBlueImage = (FrameLayout)findViewById(R.id.fm_blue);

        tv_straw = (TextView)findViewById(R.id.tv_straw);
        tv_grap = (TextView)findViewById(R.id.tv_grap);
        tv_blue = (TextView)findViewById(R.id.tv_blue);

        straw = new ArrayList<>();
        grap = new ArrayList<>();
        blue = new ArrayList<>();
        strawCompleteList = new ArrayList<>();
        grapCompleteList = new ArrayList<>();
        blueCompleteList = new ArrayList<>();

        cake = (ImageView) findViewById(R.id.cake);

        findViewById(R.id.previous_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(straw.size() <= 0 && grap.size() <= 0 && blue.size() <= 0){

                    Intent intent = new Intent(
                            getApplicationContext(),
                            FinalInformation.class);
                    intent.putExtra("data", data);
                    intent.putExtra("cake", saveLayout());
                    startActivity(intent);
                }
            }
        });

        li_optionField = (LinearLayout)findViewById(R.id.li_select_option);
        li_optionField.setVisibility(View.GONE);
        showOptionBtn = (TextView)findViewById(R.id.show_option_btn);
        showOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(li_optionField.getVisibility() == View.VISIBLE){
                    li_optionField.setVisibility(View.GONE);
                }else{
                    li_optionField.setVisibility(View.VISIBLE);
                }
            }
        });

        initRV();

        progressDialog = new ProgressDialog(this);

        progressDialog.show();
        new Thread(){
            @Override
            public void run(){
                try{
                    Thread.sleep(1000);
                    handler.sendMessage(handler.obtainMessage(MSG_MESSAGE_MAKE_UI));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private HashMap<String, Object> saveLayout(){

        HashMap<String, Object> map = new HashMap<>();

        ArrayList<Point> strawPointList = new ArrayList<>();
        for(int i=0; i<strawCompleteList.size(); i++){
            ImageView v = strawCompleteList.get(i);
            strawPointList.add(new Point((int)v.getX(), (int)v.getY()));
        }
        ArrayList<Point> grapPointList = new ArrayList<>();
        for(int i=0; i<grapCompleteList.size(); i++){
            ImageView v = grapCompleteList.get(i);
            grapPointList.add(new Point((int)v.getX(), (int)v.getY()));
        }
        ArrayList<Point> bluePointList = new ArrayList<>();
        for(int i=0; i<blueCompleteList.size(); i++){
            ImageView v = blueCompleteList.get(i);
            bluePointList.add(new Point((int)v.getX(), (int)v.getY()));
        }

        Point point = new Point((int)cake.getX(), (int)cake.getY());

        map.put("straw", strawPointList);
        map.put("grap", grapPointList);
        map.put("blue", bluePointList);
        map.put("cake", point);

        return map;

    }

    private void initRV(){

        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL_LIST));
        rv.setLayoutManager(mLinearLayoutManager);

        MakeList();

    }


    public void MakeList(){

        adapter = new MakeCakeCustomAdapter(getApplicationContext(), this, MakeData(), this);

        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    private ArrayList<HashMap<String, Object>> MakeData(){

        list = new ArrayList<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "딸기");
        map.put("num", strawCount);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "거봉");
        map.put("num", grapCount);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "블루베리");
        map.put("num", blueCount);
        list.add(map);

        return list;

    }


    private void setTextViewCount(){

        TextView temp = tv_straw;
        root.removeView(tv_straw);
        root.addView(temp);

        temp = tv_grap;
        root.removeView(tv_grap);
        root.addView(temp);

        temp = tv_blue;
        root.removeView(tv_blue);
        root.addView(temp);


        tv_straw.setText(straw.size() + "");
        tv_grap.setText(grap.size() + "");
        tv_blue.setText(blue.size() + "");
//        tv_complete.setText(completeList.size() + "");
    }

    private void addImageView(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        System.out.println(width + ", " + height);

        for(int i=0; i<ICON_COUNT; i++){

            switch (i){
                case 0:{
                    int count = strawCount - straw.size();
                    for(int j=0; j<count; j++){

                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setImageResource(R.drawable.to_01);
                        imageView.setOnTouchListener(MakeCakeActivity.this);
                        imageView.setTag("straw");

                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
                        params.leftMargin = strawPoint.x; //XCOORD
                        params.topMargin = strawPoint.y; //YCOORD
                        imageView.setLayoutParams(params);

                        root.addView(imageView);
                        straw.add(imageView);

                    }
                }
                break;
                case 1:{
                    int count = grapCount - grap.size();
                    for(int j=0; j<count; j++){

                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setImageResource(R.drawable.to_02);
                        imageView.setOnTouchListener(MakeCakeActivity.this);
                        imageView.setTag("grap");

                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
                        params.leftMargin = grapPoint.x; //XCOORD
                        params.topMargin = grapPoint.y; //YCOORD
                        imageView.setLayoutParams(params);

                        root.addView(imageView);
                        grap.add(imageView);

                    }
                }
                break;
                case 2:{
                    int count = blueCount - blue.size();
                    for(int j=0; j<count; j++){

                        ImageView imageView = new ImageView(getApplicationContext());
                        imageView.setImageResource(R.drawable.to_03);
                        imageView.setOnTouchListener(MakeCakeActivity.this);
                        imageView.setTag("blue");

                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
                        params.leftMargin = bluePoint.x; //XCOORD
                        params.topMargin = bluePoint.y; //YCOORD
                        imageView.setLayoutParams(params);

                        root.addView(imageView);
                        blue.add(imageView);

                    }
                }
                break;
            }



        }

    }

    @Override
    public boolean reduceStraw() {
        if(straw.size() + strawCompleteList.size() <= 0){
            return false;
        }

        for(ImageView imageView : straw){
            String tag = (String)imageView.getTag();
            if("straw".equals(tag)){
                straw.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        for(ImageView imageView : strawCompleteList){
            String tag = (String)imageView.getTag();
            if("straw".equals(tag)){
                strawCompleteList.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        setTextViewCount();
        return false;

    }

    @Override
    public boolean increaseStraw() {
        if(straw.size() + strawCompleteList.size() < MAX_STRAW){
            addStrawView();
            setTextViewCount();
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceGrap() {
        if(grap.size() + grapCompleteList.size() <= 0){
            return false;
        }

        for(ImageView imageView : grap){
            String tag = (String)imageView.getTag();
            if("grap".equals(tag)){
                grap.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        for(ImageView imageView : grapCompleteList){
            String tag = (String)imageView.getTag();
            if("grap".equals(tag)){
                grapCompleteList.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        setTextViewCount();
        return false;

    }

    @Override
    public boolean increaseGrap() {

        if(grap.size() + grapCompleteList.size() < MAX_GRAP){
            addGrapView();
            setTextViewCount();
            return true;
        }
        return false;
    }

    @Override
    public boolean reduceBlue() {
        if(blue.size() + blueCompleteList.size() <= 0){
            return false;
        }

        for(ImageView imageView : blue){
            String tag = (String)imageView.getTag();
            if("blue".equals(tag)){
                blue.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        for(ImageView imageView : blueCompleteList){
            String tag = (String)imageView.getTag();
            if("blue".equals(tag)){
                blueCompleteList.remove(imageView);
                root.removeView(imageView);
                setTextViewCount();
                return true;
            }
        }

        setTextViewCount();
        return false;

    }

    @Override
    public boolean increaseBlue() {

        if(blue.size() + blueCompleteList.size() < MAX_BLUE){
            addBlueView();
            setTextViewCount();
            return true;
        }
        return false;
    }

    @Override
    public int getStrawSize() {
        return straw.size() + strawCompleteList.size();
    }

    @Override
    public int getGrapSize() {
        return grap.size() + grapCompleteList.size();
    }

    @Override
    public int getBlueSize() {
        return blue.size() + blueCompleteList.size();
    }


    private class MyHandler extends Handler {

        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MSG_MESSAGE_MAKE_UI:
                    getXYLocation();
                    addImageView();
                    setTextViewCount();
                    progressDialog.hide();
                    break;
                default:
                    break;
            }
        }
    }

    private void getXYLocation(){

        strawPoint = new Point((int)defaultStrawImage.getX(), (int)defaultStrawImage.getY());
        grapPoint = new Point((int)defaultGrapImage.getX(), (int)defaultGrapImage.getY());
        bluePoint = new Point((int)defaultBlueImage.getX(), (int)defaultBlueImage.getY());

    }

    private void addStrawView(){
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.to_01);
        imageView.setOnTouchListener(MakeCakeActivity.this);
        imageView.setTag("straw");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
        params.leftMargin = strawPoint.x; //XCOORD
        params.topMargin = strawPoint.y; //YCOORD
        imageView.setLayoutParams(params);

        root.addView(imageView);
        straw.add(imageView);
    }

    private void addGrapView(){
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.to_02);
        imageView.setOnTouchListener(MakeCakeActivity.this);
        imageView.setTag("grap");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
        params.leftMargin = grapPoint.x; //XCOORD
        params.topMargin = grapPoint.y; //YCOORD
        imageView.setLayoutParams(params);

        root.addView(imageView);
        grap.add(imageView);
    }

    private void addBlueView(){
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.to_03);
        imageView.setOnTouchListener(MakeCakeActivity.this);
        imageView.setTag("blue");

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT); //WRAP_CONTENT param can be FILL_PARENT
        params.leftMargin = bluePoint.x; //XCOORD
        params.topMargin = bluePoint.y; //YCOORD
        imageView.setLayoutParams(params);

        root.addView(imageView);
        blue.add(imageView);
    }

    private boolean detectConflict(View view, float dx, float dy){

        float x1 = view.getX();
        float x2 = x1 + view.getWidth();
        float y1 = view.getY();
        float y2 = y1 + view.getHeight();

        if(x1 <= dx && dx <= x2 && y1 <= dy && dy <= y2){
            return true;
        }else{
            return false;
        }


    }

    private boolean detectConflictWithCake(float dx, float dy){

        if(detectConflict(cake, dx, dy)){
            return true;
        }
        return false;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();



        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            oldXvalue = event.getX();
            oldYvalue = event.getY();

            String tag = (String)v.getTag();
            ImageView img = (ImageView)v;
            switch (tag){
                case "straw":
                    img.setImageResource(R.drawable.fr_01);
                    break;
                case "grap":
                    img.setImageResource(R.drawable.fr_02);
                    break;
                case "blue":
                    img.setImageResource(R.drawable.fr_03);
                    break;
            }


        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            v.setX(event.getRawX() - oldXvalue);
            v.setY(event.getRawY() - (oldYvalue + v.getHeight()));

        } else if (event.getAction() == MotionEvent.ACTION_UP) {

            if(detectConflictWithCake(v.getX(), v.getY())){ // 케익 안쪽 부분

                String tag = (String)v.getTag();
                switch (tag){
                    case "straw":
                        straw.remove(v);
                        strawCompleteList.add((ImageView)v);
//                        completeList.add((ImageView)v);
                        break;
                    case "grap":
                        grap.remove(v);
                        grapCompleteList.add((ImageView)v);
//                        completeList.add((ImageView)v);
                        break;
                    case "blue":
                        blue.remove(v);
                        blueCompleteList.add((ImageView)v);
//                        completeList.add((ImageView)v);
                        break;
                }


            }else{ // 케익 바깥 부분

                String tag = (String)v.getTag();
                switch (tag){
                    case "straw":
                        straw.remove(v);
                        addStrawView();
                        if(strawCompleteList.contains(v)){
                            strawCompleteList.remove(v);
                        }
                        break;
                    case "grap":
                        grap.remove(v);
                        addGrapView();
                        if(grapCompleteList.contains(v)){
                            grapCompleteList.remove(v);
                        }
                        break;
                    case "blue":
                        blue.remove(v);
                        addBlueView();
                        if(blueCompleteList.contains(v)){
                            blueCompleteList.remove(v);
                        }
                        break;
                    default:
                        break;
                }
                root.removeView(v);

//                if(completeList.contains(v)){
//                    completeList.remove(v);
//                }

            }

        }
        setTextViewCount();
        return true;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

}
