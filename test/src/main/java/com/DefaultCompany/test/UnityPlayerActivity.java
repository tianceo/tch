package com.DefaultCompany.test;

import com.unity3d.player.*;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class UnityPlayerActivity extends Activity {
    protected UnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code
    private RelativeLayout root;

    // Setup activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBX_8888);
        setContentView(R.layout.activity_main2);
        root =  findViewById(R.id.root_layout);
        mUnityPlayer = new UnityPlayer(this);
        //注： 创建Activity时就调用UnityPlayer.resume()
        if (mUnityPlayer != null) {
            root.addView(mUnityPlayer, 0);
            mUnityPlayer.requestFocus();
            mUnityPlayer.resume();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        mUnityPlayer.destroy();
        super.onDestroy();
    }

//    // Pause Unity
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mUnityPlayer.pause();
//    }
//
//    // Resume Unity
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mUnityPlayer.resume();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnityPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUnityPlayer.stop();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the layout will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //注： 界面重启时先从界面中移除， 再重新add
        if (mUnityPlayer != null && root != null) {
            root.removeView(mUnityPlayer);
            mUnityPlayer.requestFocus();
        }
        if (mUnityPlayer != null) {
            root.addView(mUnityPlayer, 0);
        }
    }

}
