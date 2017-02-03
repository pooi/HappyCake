package tk.twpooi.happycake;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by tw on 2016-08-16.
 */
public class BreadFragment extends Fragment {

    // UI
    private View view;
    private Context context;

    private int position;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        title = (String)getArguments().getSerializable("title");
        position = getArguments().getInt("position");
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
        imageView.setImageResource(R.mipmap.ic_launcher);

    }

}
