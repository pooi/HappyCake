package tk.twpooi.happycake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectShopActivity extends BaseActivity {

    // UI
    private ImageView closeBtn;

    // Recycle View
    private RecyclerView rv;
    private LinearLayoutManager mLinearLayoutManager;
    private SelectShopListCustomAdapter adapter;

    // DATA
    private ArrayList<HashMap<String, String>> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shop);

        InitData();

        InitUI();



    }

    private void InitData(){

        list = MakeData.getShopInformation();

    }

    private void InitUI(){

        closeBtn = (ImageView)findViewById(R.id.close_btn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(mLinearLayoutManager);

        MakeList();

    }

    public void MakeList(){

        adapter = new SelectShopListCustomAdapter(getApplicationContext(), this, list);

        rv.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

}
