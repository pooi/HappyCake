package tk.twpooi.happycake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tw on 2016-08-16.
 */
public class BreadFragment extends Fragment {

    // BASIC UI
    private View view;
    private Context context;

    // DATA
    private int resultCode;
    private int position;
    private String title;
    private String content;
    private int resourceId;

    // UI
    private ImageView imageView;
    private TextView tv_title;
    private TextView tv_content;

    // RESOURCES
    private int[] sizeDrawable = {
            R.drawable.sp_01,
            R.drawable.sp_01,
            R.drawable.sp_01
    };
    private int[] sponDrawable = {
            R.drawable.spon_default_popup,
            R.drawable.spon_straw_popup,
            R.drawable.spon_choco_popup
    };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        resultCode = getArguments().getInt("code");
        position = getArguments().getInt("position");
        title = getArguments().getString("title");
        content = getArguments().getString("content");
        resourceId = getArguments().getInt("resource");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // UI
        if(resultCode == BreadActivity.SELECT_SIZE) {
            view = inflater.inflate(R.layout.fragment_bread, container, false);
        }else{
            view = inflater.inflate(R.layout.fragment_bread2, container, false);
        }
        context = container.getContext();

        initData();
        initUI();

        return view;

    }

    private void initData(){


    }

    private void initUI(){

        imageView = (ImageView)view.findViewById(R.id.img);
        imageView.setImageResource(resourceId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!BreadActivity.isSelectFinish) {
                    Intent intent = new Intent(context, BreadPopupActivity.class);
                    intent.putExtra("code", resultCode);
                    intent.putExtra("position", position);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    if(resultCode == BreadActivity.SELECT_SIZE){
                        intent.putExtra("resource", sizeDrawable[position]);
                        intent.putExtra("price", BreadActivity.priceList[position]);
                    }else if(resultCode == BreadActivity.SELECT_SPON){
                        intent.putExtra("resource", sponDrawable[position]);
                    }
                    getActivity().startActivityForResult(intent, resultCode);
                }
            }
        });
        tv_title = (TextView)view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_content = (TextView)view.findViewById(R.id.tv_content);
        tv_content.setText(content);

    }

}
