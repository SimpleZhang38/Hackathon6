package com.Hackathon6.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Leo on 23/10/2017.
 */

public class TitleGridView extends RelativeLayout {
    private String title;
    private List<Map<String,Object>> models;
    private TextView gridTitleTV;
    private RecyclerView gridView;
    private Context context;

    public void setParams(String title, List<Map<String,Object>> models) {
        this.title = title;
        this.models = models;
        gridTitleTV.setText(title);
        RobotItemCollectionAdapter adapter =new RobotItemCollectionAdapter(models);
        gridView.setAdapter(adapter);
    }

    public TitleGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.robot_faq_menu, this,true);
        gridTitleTV = findViewById(R.id.id_grid_title);
        gridView = findViewById(R.id.id_grid_view);
        gridView.setLayoutManager(new GridLayoutManager(context, 4));
    }

    class RobotItemCollectionAdapter extends RecyclerView.Adapter<RobotItemCollectionAdapter.MyViewHolder> {

        private List<Map<String,Object>> models;

        public RobotItemCollectionAdapter(List<Map<String,Object>> models) {
            this.models = models;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.robot_faq_menu_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(TitleGridView.RobotItemCollectionAdapter.MyViewHolder holder, final int position) {
            holder.itemTitleTV.setText((String) models.get(position).get("title"));
            holder.itemImageView.setBackgroundResource((int) models.get(position).get("image"));
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,MainActivity.class);
                    intent.putExtra("keyWords",(String) models.get(position).get("title"));
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return models.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView itemImageView;
            TextView itemTitleTV;
            View itemView;

            public MyViewHolder(View view) {
                super(view);
                itemTitleTV = (TextView) view.findViewById(R.id.id_item_title);
                itemImageView = (ImageView) view.findViewById(R.id.id_item_image);
                itemView = view.findViewById(R.id.itemView);
            }
        }
    }
}