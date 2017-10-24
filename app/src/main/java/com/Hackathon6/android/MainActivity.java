package com.Hackathon6.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.Hackathon6.android.adapter.ChatMessageAdapter;
import com.Hackathon6.android.bean.ChatMessage;
import com.Hackathon6.android.bean.MayWhatBean;
import com.Hackathon6.android.request.KnowledgeRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends Activity {


    private ListView mChatView;

    private EditText mMsg;

    private List<ChatMessage> mDatas = new ArrayList<>();
    private ChatMessageAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_main);

        initView();

        mAdapter = new ChatMessageAdapter(this, mDatas);
        mChatView.setAdapter(mAdapter);

        mChatView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ChatMessage chatMessage = mDatas.get(i);
                if (chatMessage.getType() == ChatMessage.Type.INPUT && !TextUtils.isEmpty(chatMessage.getAnswer())) {

                    if (chatMessage.getContentType().equals("TEXT")) {
                        ChatMessage message = new ChatMessage();
                        message.setType(ChatMessage.Type.INPUT);
                        message.setMsg(chatMessage.getAnswer());
                        mDatas.add(message);
                        mAdapter.notifyDataSetChanged();
                        mChatView.setSelection(mDatas.size() - 1);

                    } else if (chatMessage.getContentType().equals("PIC")) {

                        ChatMessage message = new ChatMessage();


                        String[] imageArray = chatMessage.getAnswer().split(",");

                        List<String> imageUrlList = new ArrayList<>();
                        for (String image : imageArray) {
                            imageUrlList.add(image);
                        }
//                        imageUrlList.add("http://img2.cache.netease.com/auto/2016/7/28/201607282215432cd8a.jpg");
                        message.setImageUrlList(imageUrlList);

                        message.setType(ChatMessage.Type.IMAGE);
                        mDatas.add(message);
                        mAdapter.notifyDataSetChanged();
                        mChatView.setSelection(mDatas.size() - 1);

                    }
                }

//                    new KnowledgeRequest().requestAsk(chatMessage.getAskId(), new KnowledgeRequest.ResultCallBack() {
//                        @Override
//                        public void successBack(Object object) {
//
//                    });
            }
        });

    }

    private void initView() {
        mChatView = findViewById(R.id.id_chat_listView);
        mMsg = findViewById(R.id.id_chat_msg);

//        String keyWords = getIntent().getStringExtra("keyWords");

        new KnowledgeRequest().requestMayWhat("", new KnowledgeRequest.RequestCallBack() {
            @Override
            public void successBack(Object object) {
                List<MayWhatBean.ValueBean> valueBeanList = (List<MayWhatBean.ValueBean>) object;


                int index = 1;
                for (MayWhatBean.ValueBean valueBean : valueBeanList) {
                    ChatMessage message = new ChatMessage();
                    if (index == 1) {
                        message.setHideHead(false);
                    } else {
                        message.setHideHead(true);
                    }
                    message.setQuestion(true);
                    message.setAnswer(valueBean.getAnswer());
                    message.setContentType(valueBean.getType());
                    message.setAskId(valueBean.getId() + "");
                    message.setMsg(valueBean.getTitle());
                    message.setType(ChatMessage.Type.INPUT);
                    message.setDate(new Date());
                    mDatas.add(message);
                    index++;
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    public void sendMessage(View view) {

        final String msg = mMsg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(this, "Empty words!", Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage to = new ChatMessage(ChatMessage.Type.OUTPUT, msg);
        to.setDate(new Date());
        mDatas.add(to);

        mAdapter.notifyDataSetChanged();
        mChatView.setSelection(mDatas.size() - 1);

        mMsg.setText("");

        new KnowledgeRequest().requestMayWhat(msg, new KnowledgeRequest.RequestCallBack() {
            @Override
            public void successBack(Object object) {
                List<MayWhatBean.ValueBean> valueBeanList = (List<MayWhatBean.ValueBean>) object;

                if (valueBeanList == null || valueBeanList.size() == 0) {
                    ChatMessage message = new ChatMessage();
                    message.setMsg("Sorry, not found");
                    message.setType(ChatMessage.Type.INPUT);
                    mDatas.add(message);
                    mAdapter.notifyDataSetChanged();
                    mChatView.setSelection(mDatas.size() - 1);
                    return;
                }


                int index = 1;
                for (MayWhatBean.ValueBean valueBean : valueBeanList) {
                    ChatMessage message = new ChatMessage();
                    if (index == 1) {
                        message.setHideHead(false);
                    } else {
                        message.setHideHead(true);
                    }
                    message.setQuestion(true);
                    message.setAnswer(valueBean.getAnswer());
                    message.setContentType(valueBean.getType());
                    message.setAskId(valueBean.getId() + "");
                    message.setMsg(valueBean.getTitle());
                    message.setType(ChatMessage.Type.INPUT);
                    mDatas.add(message);
                    index++;
                    mAdapter.notifyDataSetChanged();
                    mChatView.setSelection(mDatas.size() - 1);
                }

            }
        });

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }

    }


    public void backView(View view) {
        finish();
    }
}
