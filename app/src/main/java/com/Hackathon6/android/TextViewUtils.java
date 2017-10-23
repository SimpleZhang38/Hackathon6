package com.Hackathon6.android;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by simple on 23/10/2017.
 */

public class TextViewUtils {


    public static SpannableString getClickableSpan(String text,View.OnClickListener l) {
        SpannableString spanableInfo = new SpannableString(text);
        int start = spanableInfo.length() - 4;
        int end = spanableInfo.length();
        spanableInfo.setSpan(new Clickable(l), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanableInfo;
    }


    static class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(false);
            ds.bgColor = Color.WHITE;
        }

        @Override
        public void onClick(View v) {
            if (null != mListener) {
                mListener.onClick(v);
            }
        }
    }
}
