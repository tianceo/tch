package com.boe.cn.themapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.unity3d.player.UnityPlayer;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       FrameLayout FrameLayout= findViewById(R.id.fr_containersp);
       UnityPlayer mUnityPlayer=Myapplication.getInstance().getPlayer();
        View view = mUnityPlayer.getView();
        Myapplication.getInstance().setUnityPlayer(mUnityPlayer);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        FrameLayout.addView(view);
    }
}
