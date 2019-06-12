package com.boe.cn.themapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.DefaultCompany.test.UnityPlayerActivity;


public class MainActivity extends UnityPlayerActivity {
    private static final int REQUEST_CODE_PERMISSION_CALL = 102;
    private Handler mHandler = null;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        checkAndRequestCallPermission();
        Myapplication.getInstance().setUnityPlayer(mUnityPlayer);
        mHandler = new Handler();
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
//        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清 //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            // 这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        View view = mUnityPlayer.getView();
        Myapplication.getInstance().setUnityPlayer(mUnityPlayer);
        FrameLayout scan = findViewById(R.id.fr_container);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        scan.addView(view);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
//                finish();

            }
        });


    }

    private void checkAndRequestCallPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(MainActivity.this)) {

            } else {
                //若没有权限，提示获取.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(MainActivity.this, "需要取得权限以使用悬浮窗", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

        } else {

        }
    }

}
