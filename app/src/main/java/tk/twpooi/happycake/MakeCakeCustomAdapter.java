package tk.twpooi.happycake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by tw on 2016-08-16.
 */
public class MakeCakeCustomAdapter extends RecyclerView.Adapter<MakeCakeCustomAdapter.ViewHolder> {

    // UI
    private Activity activity;
    private Context context;

    public ArrayList<HashMap<String, Object>> list;
    private ControlCakeListener listener;


    // 생성자
    public MakeCakeCustomAdapter(Context context, Activity activity, ArrayList<HashMap<String, Object>> list, ControlCakeListener listener) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.make_cake_custom_item,null);
        return new ViewHolder(v);
    }

    private void increase(int num, int pos){
        HashMap<String, Object> map = list.get(pos);
        map.put("num", num);
        list.set(pos, map);
        notifyItemChanged(pos);
    }

    private void decrease(int num, int pos){
        HashMap<String, Object> map = list.get(pos);
        map.put("num", num);
        list.set(pos, map);
        notifyItemChanged(pos);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HashMap<String,Object> data = list.get(position);
        final int pos = position;

        final String title = (String)data.get("title");
        final int num = (int)data.get("num");

        holder.tv_title.setText(title);
        holder.tv_num.setText(num + "개");

        holder.increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (title){
                    case "딸기":
                        if(listener.increaseStraw()){
                            increase(listener.getStrawSize(), pos);
                        }
                        break;
                    case "거봉":
                        if(listener.increaseGrap()){
                            increase(listener.getGrapSize(), pos);
                        }
                        break;
                    case "블루베리":
                        if(listener.increaseBlue()){
                            increase(listener.getBlueSize(), pos);
                        }
                        break;
                }

            }
        });
        holder.reduceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (title){
                    case "딸기":
                        if(listener.reduceStraw()){
                            decrease(listener.getStrawSize(), pos);
                        }
                        break;
                    case "거봉":
                        if(listener.reduceGrap()){
                            decrease(listener.getGrapSize(), pos);
                        }
                        break;
                    case "블루베리":
                        if(listener.reduceBlue()){
                            decrease(listener.getBlueSize(), pos);
                        }
                        break;
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public final static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_num;
        ImageView reduceBtn;
        ImageView increaseBtn;

        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView)v.findViewById(R.id.tv_title);
            tv_num = (TextView)v.findViewById(R.id.tv_num);
            reduceBtn = (ImageView)v.findViewById(R.id.reduce_btn);
            increaseBtn = (ImageView)v.findViewById(R.id.increase_btn);
        }
    }

}
