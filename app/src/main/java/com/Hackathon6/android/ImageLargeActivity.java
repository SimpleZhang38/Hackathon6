package com.Hackathon6.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by simple on 24/10/2017.
 */

public class ImageLargeActivity extends Activity {

    private ImageView largeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_imagelarge);

        largeImageView = findViewById(R.id.largeImageView);

        String imageUrl = getIntent().getStringExtra("imageUrl");
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_photo_faile)
                .into(largeImageView);
    }

    public void backView(View view) {
        finish();
    }
}
