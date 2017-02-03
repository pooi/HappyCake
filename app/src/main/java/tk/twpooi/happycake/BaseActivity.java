package tk.twpooi.happycake;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tsengvn.typekit.Typekit;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by tw on 2017. 2. 3..
 */

public class BaseActivity extends AppCompatActivity {


    public static String Bareun1 = "font/BareunDotumOTFPro1.otf";
    public static String Bareun2 = "font/BareunDotumOTFPro2.otf";
    public static String Bareun3 = "font/BareunDotumOTFPro3.otf";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void setFont(TextView view, String fontName){
        view.setTypeface(Typeface.createFromAsset(getAssets(), Bareun1));
    }

}
