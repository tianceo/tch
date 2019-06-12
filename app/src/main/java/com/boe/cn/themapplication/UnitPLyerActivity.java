package com.boe.cn.themapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.DefaultCompany.test.UnityPlayerActivity;


public class UnitPLyerActivity extends UnityPlayerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_plyer);
        FrameLayout scan = findViewById(R.id.fragmen_unity);
        View view =mUnityPlayer.getView();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        scan.addView(view);
    }
}
