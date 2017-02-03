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

    // UI
    private ImageView imageView;
    private TextView tv_title;
    private TextView tv_content;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        title = (String)getArguments().getSerializable("title");
        resultCode = getArguments().getInt("code");
        position = getArguments().getInt("position");
        title = getArguments().getString("title");
        content = getArguments().getString("content");
//        isOverlay = getArguments().getBoolean("visiable", false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // UI
        view = inflater.inflate(R.layout.fragment_bread, container, false);
        context = container.getContext();

        initData();
        initUI();

        return view;

    }

    private void initData(){


    }

    private void initUI(){

        imageView = (ImageView)view.findViewById(R.id.img);
        imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BreadPopupActivity.class);
                intent.putExtra("code", resultCode);
                getActivity().startActivityForResult(intent, resultCode);
            }
        });
        tv_title = (TextView)view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        tv_content = (TextView)view.findViewById(R.id.tv_content);
        tv_content.setText(content);

    }

}
