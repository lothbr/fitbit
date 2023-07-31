package com.e.fitbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class loadingView extends AppCompatActivity {

private static int SPLASH = 7000;

    Animation top, down ;
    ImageView logo;
    ProgressBar pro;
    TextView loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading_view);
                hideNavigationBar();

        // animation
        top = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        down = AnimationUtils.loadAnimation(this, R.anim.down_anim);

        //hooks
        logo = findViewById(R.id.logo);
        pro = findViewById(R.id.progress);
        loader = findViewById(R.id.load);

        logo.setAnimation(top);
        pro.setAnimation(down);
        loader.setAnimation(down);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent in = new Intent(loadingView.this,register.class);
                startActivity(in);

            }
        }, SPLASH);


    }

    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
}
