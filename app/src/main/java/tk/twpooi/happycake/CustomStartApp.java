package tk.twpooi.happycake;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by tw on 2017. 2. 3..
 */

public class CustomStartApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "font/BareunDotumOTFPro1.otf"))
                .addBold(Typekit.createFromAsset(this, "font/BareunDotumOTFPro2.otf"));
    }

}
