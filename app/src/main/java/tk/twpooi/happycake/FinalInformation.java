package tk.twpooi.happycake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sungmin on 2017-02-02.
 */
public class FinalInformation extends AppCompatActivity {
    private ImageView cake;

    private TextView finalSize;
    private TextView finalSizeText;
    private TextView finalSponge;
    private TextView finalSpongeText;
    private TextView finalTopping;
    private TextView finalToppingText;

    private Button back;
    private Button goToNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalinformation);
        cake = (ImageView) findViewById(R.id.cake);
        cake.setImageResource(R.mipmap.ic_launcher);

        finalSize = (TextView) findViewById(R.id.finalSize);
        finalSizeText = (TextView) findViewById(R.id.finalSizeText);
        finalSponge = (TextView) findViewById(R.id.finalSponge);
        finalSpongeText = (TextView) findViewById(R.id.finalSpongeText);
        finalTopping = (TextView) findViewById(R.id.finalTopping);
        finalToppingText = (TextView) findViewById(R.id.finalToppingText);

        back = (Button) findViewById(R.id.back);
        goToNext = (Button) findViewById(R.id.goToNext);

        //이전 단계에서 인텐트로 받아온 값들
//        Intent intent = getIntent();
//        finalSizeText.setText(intent.getExtras().getString("finalSize"));
//        finalToppingText.setText(intent.getExtras().getString("finalSponge"));
//        finalToppingText.setText(intent.getExtras().getString("finalTopping"));

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
                startActivity(intent);
            }
        });
    }
}
