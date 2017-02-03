package tk.twpooi.happycake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

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

//        Button tempBtn = (Button)findViewById(R.id.tempBtn);
//        tempBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), BreadActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button tempBtn2 = (Button)findViewById(R.id.tempBtn2);
//        tempBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), CheckPayActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//
//        Button tempBtn3 = (Button)findViewById(R.id.tempBtn3);
//        tempBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), FinalInformation.class);
//                startActivity(intent);
//            }
//        });
    }
}
