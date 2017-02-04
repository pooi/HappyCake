package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.animation.FadeEnter.FadeEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class StartActivity extends BaseActivity {

    private ImageView loadingImg;
    private ImageView parisBaguette;
    private ImageView tousBtn;
    private ImageView croBtn;

    private boolean isNoLoading = false;

    private HashMap<String, Object> data;

    private FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        data = new HashMap<>();

        Intent intent = getIntent();
        isNoLoading = intent.getBooleanExtra("isNoLoading", false);

        parisBaguette = (ImageView)findViewById(R.id.paris_btn);
        parisBaguette.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("shop", "파리바게트");
                Intent intent = new Intent(
                        getApplicationContext(),
                        BreadActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        Picasso.with(getApplicationContext())
                .load("http://i.imgur.com/ic0m9Qj.png")
                .into(parisBaguette);

        tousBtn = (ImageView)findViewById(R.id.tous_btn);
        tousBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("shop", "뚜레쥬르");
                Intent intent = new Intent(
                        getApplicationContext(),
                        BreadActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        Picasso.with(getApplicationContext())
                .load("http://i.imgur.com/bnJmmY4.png")
                .into(tousBtn);

        croBtn = (ImageView)findViewById(R.id.cro_btn);
        croBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("shop", "파리 크로아상");
                Intent intent = new Intent(
                        getApplicationContext(),
                        BreadActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        Picasso.with(getApplicationContext())
                .load("http://i.imgur.com/PphxVbZ.png")
                .into(croBtn);

        loadingImg = (ImageView)findViewById(R.id.loading_img);
        loadingImg.setImageResource(R.drawable.roading_01);

        fileManager = new FileManager(getApplicationContext());

        if(!isNoLoading){
            ShowLoading();

            final HashMap<String, Object> map = fileManager.readSaveData();
            if(map != null){

                final MaterialDialog dialog = new MaterialDialog(StartActivity.this);
                dialog.content("이전에 저장한 데이터가 있습니다.\n이어가겠습니까?")
                        .title("확인")
                        .btnText("아니요", "예")
                        .showAnim(new FadeEnter())
                        .show();
                OnBtnClickL left = new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        fileManager.writeSaveData(null);
                        dialog.dismiss();
                    }
                };
                OnBtnClickL right = new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        dialog.dismiss();
                        fileManager.writeSaveData(null);
                        Intent intent = new Intent(getApplicationContext(), MakeCakeActivity.class);
                        intent.putExtra("isSaved", true);
                        intent.putExtra("saveData", map);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                };
                dialog.setOnBtnClickL(left, right);

            }

        }else{
            loadingImg.setVisibility(View.GONE);
        }

    }

    private void ShowLoading(){

        loadingImg.setVisibility(View.INVISIBLE);
        SetFadeOutAnimation(loadingImg);

    }


    private void SetFadeOutAnimation(View view){
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(3000);
        view.setAnimation(animation);
    }

}
