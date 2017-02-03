package tk.twpooi.happycake;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Sungmin on 2017-02-02.
 */
public class FinalInformation extends BaseActivity {
    private ImageView cake;

    private TextView finalSize;
    private TextView finalSizeText;
    private TextView finalSponge;
    private TextView finalSpongeText;
    private TextView finalTopping;
    private TextView finalToppingText;

    private ImageView back;
    private ImageView goToNext;

    private HashMap<String, Object> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finalinformation);

        data = (HashMap<String, Object>)getIntent().getSerializableExtra("data");

        cake = (ImageView) findViewById(R.id.cake);
        cake.setImageResource(R.drawable.ic_launcher);

        finalSize = (TextView) findViewById(R.id.finalSize);
        finalSizeText = (TextView) findViewById(R.id.finalSizeText);
        finalSponge = (TextView) findViewById(R.id.finalSponge);
        finalSpongeText = (TextView) findViewById(R.id.finalSpongeText);
        finalTopping = (TextView) findViewById(R.id.finalTopping);
        finalToppingText = (TextView) findViewById(R.id.finalToppingText);

        back = (ImageView) findViewById(R.id.previous_btn);
        goToNext = (ImageView) findViewById(R.id.next_btn);



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
    }

}
