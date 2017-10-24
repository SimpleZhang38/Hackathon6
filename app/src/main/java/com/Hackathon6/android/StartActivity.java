package com.Hackathon6.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by simple on 23/10/2017.
 */

public class StartActivity extends Activity {

    private ImageView startImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        startImage = findViewById(R.id.startImage);

        AnimationDrawable frameAnim = (AnimationDrawable) getResources().getDrawable(R.drawable.start_main_bg);
        startImage.setBackgroundDrawable(frameAnim);

        if (frameAnim != null && !frameAnim.isRunning()) {
            frameAnim.start();
        }
    }

    public void startMainClick(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}
