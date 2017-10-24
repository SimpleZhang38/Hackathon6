package com.Hackathon6.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Hackathon6.android.ImageLargeActivity;
import com.Hackathon6.android.R;
import com.Hackathon6.android.adapter.ImageAdapter;
import com.Hackathon6.android.bean.ChatMessage;

import java.util.List;

public class ChatMessageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;
    private Context mContext;

    public ChatMessageAdapter(Context context, List<ChatMessage> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage msg = mDatas.get(position);
        if (msg.getType() == ChatMessage.Type.INPUT) {
            return 1;
        } else if (msg.getType() == ChatMessage.Type.OUTPUT) {
            return 0;
        } else if (msg.getType() == ChatMessage.Type.IMAGE) {
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ChatMessage chatMessage = mDatas.get(position);

        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (chatMessage.getType() == ChatMessage.Type.INPUT) {
                convertView = mInflater.inflate(R.layout.main_chat_from_msg,
                        parent, false);
                viewHolder.createDate = convertView
                        .findViewById(R.id.chat_from_createDate);
                viewHolder.content = convertView
                        .findViewById(R.id.chat_from_content);
                viewHolder.chat_from_icon = convertView
                        .findViewById(R.id.chat_from_icon);
                convertView.setTag(viewHolder);
            } else if (chatMessage.getType() == ChatMessage.Type.OUTPUT) {
                convertView = mInflater.inflate(R.layout.main_chat_send_msg,
                        null);

                viewHolder.createDate = convertView
                        .findViewById(R.id.chat_send_createDate);
                viewHolder.content = convertView
                        .findViewById(R.id.chat_send_content);
                convertView.setTag(viewHolder);
            } else {
                convertView = mInflater.inflate(R.layout.main_chat_image_msg,
                        null);

                viewHolder.recyclerview_image = convertView
                        .findViewById(R.id.recyclerview_image);


                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                viewHolder.recyclerview_image.setLayoutManager(layoutManager);
                viewHolder.recyclerview_image.setOverScrollMode(View.OVER_SCROLL_NEVER);
                viewHolder.recyclerview_image.setAdapter(new ImageAdapter(mContext,chatMessage.getImageUrlList()));

                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (chatMessage.getType() != ChatMessage.Type.IMAGE) {
            viewHolder.content.setText(Html.fromHtml(chatMessage.getMsg()));
            viewHolder.createDate.setText(chatMessage.getDateStr());
            if (chatMessage.isQuestion()){
                viewHolder.content.setTextColor(mContext.getResources().getColor(R.color.blue_drak));
            }else{
                viewHolder.content.setTextColor(mContext.getResources().getColor(R.color.black));
            }
        }


        if (viewHolder.chat_from_icon != null) {
            if (chatMessage.isHideHead()) {
                viewHolder.chat_from_icon.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.chat_from_icon.setVisibility(View.VISIBLE);
            }
        }


        return convertView;
    }

    private class ViewHolder {
        public TextView createDate;
        public TextView name;
        public TextView content;
        public View chat_from_icon;
        public RecyclerView recyclerview_image;
    }

}
