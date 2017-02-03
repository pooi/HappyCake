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

public class StartActivity extends BaseActivity {

    private ImageView loadingImg;

    private boolean isNoLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        isNoLoading = intent.getBooleanExtra("isNoLoading", false);

        Button parisBaguette = (Button)findViewById(R.id.parisBaguette);
        parisBaguette.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        BreadActivity.class);
                startActivity(intent);
            }
        });

        loadingImg = (ImageView)findViewById(R.id.loading_img);
        loadingImg.setImageResource(R.drawable.loading_img);

        if(!isNoLoading){
            ShowLoading();
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
