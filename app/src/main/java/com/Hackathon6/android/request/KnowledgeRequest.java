package com.Hackathon6.android.request;

import com.Hackathon6.android.bean.MayWhatBean;
import com.Hackathon6.android.bean.ResultBean;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

/**
 * Created by simple on 23/10/2017.
 */
public class KnowledgeRequest {

    public void requestAsk(String askId, final ResultCallBack callBack) {

        String apiStr = "https://epc-content-hackathon6.us-west-2.test.expedia.com/service/v1/know/ask/" + askId + "/";

        OkHttpUtils
                .get()
                .url(apiStr)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            ResultBean resultBean = new Gson().fromJson(response.toString(), ResultBean.class);
                            if (resultBean.isSuccessful()) {
                                if (callBack != null) {
                                    callBack.successBack(resultBean.getValue());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


    public void requestMayWhat(String keywords, final RequestCallBack requestCallBack) {


        String apiStr = "https://epc-content-hackathon6.us-west-2.test.expedia.com/service/v1/know/mayWant/";

        OkHttpUtils
                .get()
                .url(apiStr)
                .addParams("keywords", keywords)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            MayWhatBean mayWhatBean = new Gson().fromJson(response.toString(), MayWhatBean.class);
                            if (mayWhatBean.isSuccessful()) {
                                if (requestCallBack != null) {
                                    requestCallBack.successBack(mayWhatBean.getValue());
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

    }

    public interface RequestCallBack {
        public void successBack(Object object);
    }

    public interface ResultCallBack {
        public void successBack(Object object);
    }
}
