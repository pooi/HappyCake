package tk.twpooi.happycake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by tw on 2016-08-16.
 */
public class SelectShopListCustomAdapter extends RecyclerView.Adapter<SelectShopListCustomAdapter.ViewHolder> {

    // UI
    private Activity activity;
    private Context context;

    public ArrayList<HashMap<String, String>> list;


    // 생성자
    public SelectShopListCustomAdapter(Context context, Activity activity, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //recycler view에 반복될 아이템 레이아웃 연결
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_shop_list_custom_item,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final HashMap<String,String> data = list.get(position);
        final int pos = position;

        final String title = data.get("title");
        final String address = data.get("address");

        holder.tv_title.setText(title);
        holder.tv_address.setText(address);

        holder.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("title", title);
                activity.setResult(CheckPayActivity.SELECT_SHOP, intent);
                activity.finish();
            }
        });


    }


    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public final static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_address;
        TextView selectBtn;

        public ViewHolder(View v) {
            super(v);
            tv_title = (TextView)v.findViewById(R.id.title);
            tv_address = (TextView)v.findViewById(R.id.address);
            selectBtn = (TextView)v.findViewById(R.id.select_btn);
        }
    }

}
